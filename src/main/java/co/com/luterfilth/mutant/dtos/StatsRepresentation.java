/**
 * 
 */
package co.com.luterfilth.mutant.dtos;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * @author cbuitrago
 *
 */
public class StatsRepresentation {

	@JsonProperty("count_mutant_dna")
	private Integer countMutantDNA;
	@JsonProperty("count_human_dna")
	private Integer countHumanDNA;
	private Double ratio;

	public StatsRepresentation() {
		countMutantDNA = 0;
		countHumanDNA = 0;
		ratio = 0.0;
	}

	/**
	 * @return the countMutantDNA
	 */
	public Integer getCountMutantDNA() {
		return countMutantDNA;
	}

	/**
	 * @param countMutantDNA the countMutantDNA to set
	 */
	public void setCountMutantDNA(Integer countMutantDNA) {
		this.countMutantDNA = countMutantDNA;
	}

	/**
	 * @return the countHumanDNA
	 */
	public Integer getCountHumanDNA() {
		return countHumanDNA;
	}

	/**
	 * @param countHumanDNA the countHumanDNA to set
	 */
	public void setCountHumanDNA(Integer countHumanDNA) {
		this.countHumanDNA = countHumanDNA;
	}

	/**
	 * @return the ratio
	 */
	public Double getRatio() {
		return ratio;
	}

	/**
	 * @param ratio the ratio to set
	 */
	public void setRatio(Double ratio) {
		this.ratio = ratio;
	}

}
