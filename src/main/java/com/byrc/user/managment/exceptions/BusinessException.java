package com.byrc.user.managment.exceptions;

/**
 * 
 * @ClassName: BusinessException
 * @author Breymer Robles
 * @since: 2022-02-05
 * @version 1.0
 */
public class BusinessException extends ApplicationException {

	private static final long serialVersionUID = -2249736283502631807L;

	public BusinessException(final String message, final Throwable cause) {
		super(message, cause);
	}

	public BusinessException(final String message) {
		super(message);
	}

	public BusinessException(final Throwable cause) {
		super(cause);
	}

}
