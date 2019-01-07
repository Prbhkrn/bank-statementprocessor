package com.project.bankstatementprocessor.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
/**
 * Represents Global Exception Handler class
 * 
 * @author Prabhakaran Gurusamy
 * @version 1.0
 */
@ControllerAdvice
public class GlobalExceptionHandler {
	/**
	 * Handle Business Exception for application
	 * 
	 * @param BusinessException and WebRequest
	 * @return ResponseEntity<Object>
	 */
	@ExceptionHandler(value = { BusinessException.class })
	protected ResponseEntity<Object> handleBusinessException(BusinessException ex, WebRequest request) {
		return new ResponseEntity<>(ex.getErrorMessage(), HttpStatus.BAD_REQUEST);
	}
}
