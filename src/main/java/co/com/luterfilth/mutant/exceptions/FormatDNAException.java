/**
 * 
 */
package co.com.luterfilth.mutant.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * @author cbuitrago
 *
 */
@ResponseStatus(value = HttpStatus.CONFLICT)
public class FormatDNAException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public FormatDNAException() {
		super();
	}

	public FormatDNAException(String message, Throwable cause) {
		super(message, cause);
	}

	public FormatDNAException(String message) {
		super(message);
	}

	public FormatDNAException(Throwable cause) {
		super(cause);
	}

}
