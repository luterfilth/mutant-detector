/**
 * 
 */
package co.com.luterfilth.mutant.services.impl;

import java.util.Arrays;
import java.util.List;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import co.com.luterfilth.mutant.daos.IRequestDNADao;
import co.com.luterfilth.mutant.dtos.StatsRepresentation;
import co.com.luterfilth.mutant.exceptions.FormatDNAException;
import co.com.luterfilth.mutant.models.RequestDNA;
import co.com.luterfilth.mutant.services.IMutantService;

/**
 * @author cbuitrago
 *
 */
@Service
public class MutantService implements IMutantService {

	@Autowired
	private IRequestDNADao dnaDao;

	@Override
	public Boolean isMutant(String[] dna) {

		RequestDNA requestDNA = new RequestDNA();
		StringBuilder builder = new StringBuilder();
		Integer index = 0;
		Integer rows = 0;
		Integer cols = 0;
		Boolean state = false;

		try {

			// Se llena la información base para almacenar
			requestDNA.setDna(dna.toString());

			// Se combierte el arreglo en una lista para trabajar Stream
			List<String> dnaList = Arrays.asList(dna);
			cols = dnaList.get(0).length();
			rows = dnaList.size();

			if (!structure(dnaList, cols, rows)) {
				throw new FormatDNAException();
			}

			// Verificamos por columnas y filas
			for (int i = 0; i < cols; i++) {
				builder.delete(0, builder.length());
				for (int j = 0; j < rows; j++) {
					state = verificate(dnaList.get(j));
					if (state) {
						break;
					}
					builder.append(dnaList.get(j).charAt(i));
				}
				state = verificate(builder.toString());
				if (state) {
					builder.delete(0, builder.length());
					break;
				}
			}

			// Verificamos de forma oblicua
			if (!state) {
				for (int i = 0; i < cols; i++) {
					builder.delete(0, builder.length());
					index = i;
					// Validacion de forma ascendente
					for (int j = 0; j < (rows - i); j++) {
						builder.append(dnaList.get(j).charAt(index));
						index++;
					}
					state = verificate(builder.toString());
					if (state) {
						builder.delete(0, builder.length());
						break;
					}
					// Reset info
					builder.delete(0, builder.length());
					index = i;
					// Validacion de forma descendente
					for (int j = 0; j < (rows - i); j++) {
						builder.append(dnaList.get(index).charAt(j));
						index++;
					}
					state = verificate(builder.toString());
					if (state) {
						builder.delete(0, builder.length());
						break;
					}
				}
			}

			// Se almacenar la infromación que es valida
			// Junto con el resultado
			requestDNA.setIsMutant(state);
			dnaDao.save(requestDNA);

		} catch (Exception e) {
			e.printStackTrace();
			throw new FormatDNAException();
		}

		return state;
	}

	public StatsRepresentation getStats() {
		// Se consulta la información
		StatsRepresentation statsRepresentation = new StatsRepresentation();
		try {
			List<RequestDNA> requestDNA = (List<RequestDNA>) dnaDao.findAll();
			statsRepresentation.setCountMutantDNA(
					requestDNA.stream().filter(dna -> dna.getIsMutant()).collect(Collectors.toList()).size());
			statsRepresentation.setCountHumanDNA(
					requestDNA.stream().filter(dna -> !dna.getIsMutant()).collect(Collectors.toList()).size());
			if (statsRepresentation.getCountHumanDNA() > 0 && statsRepresentation.getCountMutantDNA() > 0) {
				statsRepresentation.setRatio(Double
						.valueOf((statsRepresentation.getCountMutantDNA() / statsRepresentation.getCountHumanDNA())));
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		return statsRepresentation;
	}

	/**
	 * 
	 * @param dnaList
	 * @return
	 */
	private Boolean structure(List<String> dnaList, Integer cols, Integer rows) {
		// Se verifica que los elementos del ADN, cumplan con los valores aceptados
		// Usando una expresion regular
		Pattern pattern = Pattern.compile("[BD-FH-SU-Z]");
		List<String> filterDNA = dnaList.stream().filter(d -> pattern.matcher(d).find()).collect(Collectors.toList());

		if (!filterDNA.isEmpty()) {
			return false;
		}

		if (cols != rows) {
			return false;
		}

		return true;
	}

	/**
	 * 
	 * @return
	 */
	private Boolean verificate(String dna) {
		Pattern pattern = Pattern.compile("(.)\\1{3}");
		return pattern.matcher(dna).find();
	}

}
