/**
 * 
 */
package co.com.luterfilth.mutant.models;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

/**
 * @author cbuitrago
 *
 */
@Entity
@Table(name = "mt_dna")
public class RequestDNA {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@Column(nullable = false, unique = true)
	private String dna;
	@Column(nullable = false)
	private Boolean isMutant;
	@CreationTimestamp
	private LocalDateTime createdAt;
	@UpdateTimestamp
	private LocalDateTime updateAt;

	/**
	 * @return the id
	 */
	public Long getId() {
		return id;
	}

	/**
	 * @param id the id to set
	 */
	public void setId(Long id) {
		this.id = id;
	}

	/**
	 * @return the dna
	 */
	public String getDna() {
		return dna;
	}

	/**
	 * @param dna the dna to set
	 */
	public void setDna(String dna) {
		this.dna = dna;
	}

	/**
	 * @return the isMutant
	 */
	public Boolean getIsMutant() {
		return isMutant;
	}

	/**
	 * @param isMutant the isMutant to set
	 */
	public void setIsMutant(Boolean isMutant) {
		this.isMutant = isMutant;
	}

}
