/**
 * 
 */
package co.com.luterfilth.mutant;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.net.URI;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;

import co.com.luterfilth.mutant.dtos.StatsRepresentation;

/**
 * @author cbuitrago
 *
 */
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
public class MutantTest {

	@Autowired
	private TestRestTemplate restTemplate;

	@LocalServerPort
	int randomServerPort;

	@Test
	public void mutandOk() throws Exception {
		final String baseUrl = "http://localhost:" + randomServerPort + "/mutant";
		URI uri = new URI(baseUrl);

		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);

		String jsonDNA = "{ \"dna\":[\"ATGCGA\",\"CAGTGC\",\"TTATGT\",\"AGAAGG\",\"CCCCTA\",\"TCACTG\"] }";

		HttpEntity<String> request = new HttpEntity<>(jsonDNA, headers);

		ResponseEntity<String> result = this.restTemplate.postForEntity(uri, request, String.class);

		assertEquals(HttpStatus.OK.value(), result.getStatusCodeValue());
	}
	
	@Test
	public void mutandForbidden() throws Exception {
		final String baseUrl = "http://localhost:" + randomServerPort + "/mutant";
		URI uri = new URI(baseUrl);

		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);

		String jsonDNA = "{ \"dna\":[\"AAACCC\",\"CCCGGG\",\"GGGTTT\",\"TTTAAA\",\"AAACCC\",\"CCCGGG\"] }";

		HttpEntity<String> request = new HttpEntity<>(jsonDNA, headers);

		ResponseEntity<String> result = this.restTemplate.postForEntity(uri, request, String.class);

		assertEquals(HttpStatus.FORBIDDEN.value(), result.getStatusCodeValue());
	}
	
	@Test
	public void mutandConflict() throws Exception {
		final String baseUrl = "http://localhost:" + randomServerPort + "/mutant";
		URI uri = new URI(baseUrl);

		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);

		String jsonDNA = "{ \"dna\":[\"ATGCGA\",\"CAGTGC\",\"TTATGT\",\"AGAAGG\",\"CCCCTA\"] }";

		HttpEntity<String> request = new HttpEntity<>(jsonDNA, headers);

		ResponseEntity<String> result = this.restTemplate.postForEntity(uri, request, String.class);

		assertEquals(HttpStatus.CONFLICT.value(), result.getStatusCodeValue());
	}
	
	@Test
	public void statsOk() throws Exception {
		final String baseUrl = "http://localhost:" + randomServerPort + "/stats";
		URI uri = new URI(baseUrl);

		ResponseEntity<StatsRepresentation> result = this.restTemplate.getForEntity(uri, StatsRepresentation.class);

		assertEquals(HttpStatus.OK.value(), result.getStatusCodeValue());
	}

}
