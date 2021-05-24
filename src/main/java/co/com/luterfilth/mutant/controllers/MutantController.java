/**
* 
*/
package co.com.luterfilth.mutant.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import co.com.luterfilth.mutant.dtos.DNARepresentation;
import co.com.luterfilth.mutant.dtos.StatsRepresentation;
import co.com.luterfilth.mutant.exceptions.FormatDNAException;
import co.com.luterfilth.mutant.services.IMutantService;

/**
 * @author cbuitrago
 *
 */
@RestController
public class MutantController {

	@Autowired
	private IMutantService mutantService;

	@PostMapping("mutant")
	public ResponseEntity<String> mutant(@RequestBody DNARepresentation dnaRepresentation) {
		try {
			if (mutantService.isMutant(dnaRepresentation.getDna())) {
				return new ResponseEntity<String>("Bienvenido", HttpStatus.OK);
			} else {
				return new ResponseEntity<String>("Magneto te ha rechazado", HttpStatus.FORBIDDEN);
			}
		} catch (FormatDNAException e) {
			throw new ResponseStatusException(HttpStatus.CONFLICT, "Verificar la Matriz o los datos ingresados");
		}
	}

	@GetMapping("stats")
	public @ResponseBody StatsRepresentation stats() {
		return mutantService.getStats();
	}
}
