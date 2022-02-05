package com.byrc.user.managment.exceptions;

/**
 * 
 * @ClassName: ConflictException
 * @author Breymer Robles
 * @since: 2022-02-05
 * @version 1.0
 */
public class ConflictException extends BusinessException {
	private static final long serialVersionUID = -2249736283502631807L;

	public ConflictException(final String message, final Throwable cause) {
		super(message, cause);
	}

	public ConflictException(final String message) {
		super(message);
	}

	public ConflictException(final Throwable cause) {
		super(cause);
	}
}
