package com.project.bankstatementprocessor.exception;

/**
 * Represents BusinessException class
 * 
 * @author Prabhakaran Gurusamy
 * @version 1.0
 */
public class BusinessException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	/** Represents the errorMessage */
	private final String errorMessage;

	/** Creates an BusinessException */
	public BusinessException(String errorMessage) {
		super();
		this.errorMessage = errorMessage;
	}

	/**
	 * Gets the error Message.
	 * 
	 * @return A String representing the reference
	 */
	public String getErrorMessage() {
		return errorMessage;
	}

}
