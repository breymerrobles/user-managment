package com.byrc.user.managment.exceptions;

/**
 * 
 * @ClassName: TechnicalException
 * @author Breymer Robles
 * @since: 2022-02-05
 * @version 1.0
 */
public class TechnicalException extends ApplicationException {

	private static final long serialVersionUID = -2249736283502631807L;

	public TechnicalException(final String message, final Throwable cause) {
		super(message, cause);
	}

	public TechnicalException(final String message) {
		super(message);
	}

	public TechnicalException(final Throwable cause) {
		super(cause);
	}

}
