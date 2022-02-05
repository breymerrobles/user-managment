package com.byrc.user.managment.exceptions;

/**
 * 
 * @ClassName: NotFoundException
 * @author Breymer Robles
 * @since: 2022-02-05
 * @version 1.0
 */
public class NotFoundException extends BusinessException {
	private static final long serialVersionUID = -2249736283502631807L;

	public NotFoundException(final String message, final Throwable cause) {
		super(message, cause);
	}

	public NotFoundException(final String message) {
		super(message);
	}

	public NotFoundException(final Throwable cause) {
		super(cause);
	}
}
