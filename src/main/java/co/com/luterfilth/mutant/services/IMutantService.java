/**
 * 
 */
package co.com.luterfilth.mutant.services;

import co.com.luterfilth.mutant.dtos.StatsRepresentation;

/**
 * @author cbuitrago
 *
 */
public interface IMutantService {

	public Boolean isMutant(String[] dna);

	public StatsRepresentation getStats();

}
