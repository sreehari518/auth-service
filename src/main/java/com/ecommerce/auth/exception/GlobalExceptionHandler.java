package com.ecommerce.auth.exception;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {
	
	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity<String> handleValidationException(
	        MethodArgumentNotValidException ex) {

	    return ResponseEntity
	            .badRequest()
	            .body("Validation failed");
	}
	
	@ExceptionHandler(RuntimeException.class)
	public ResponseEntity<String> handleRuntimeException(RuntimeException ex) {

	    return ResponseEntity
	            .badRequest()
	            .body(ex.getMessage());
	}
	
	@ExceptionHandler(EmailAlreadyExistsException.class)
	public ResponseEntity<String>handleEmailExists(EmailAlreadyExistsException ex){
		return ResponseEntity
				.status(409)
				.body(ex.getMessage());
	}
	
	@ExceptionHandler(MobileNumberAlreadyExistsException.class)
	public ResponseEntity<String>handleMobileExists(MobileNumberAlreadyExistsException ex){
		return ResponseEntity
				.status(401)
				.body(ex.getMessage());
	}
	
	@ExceptionHandler(InvalidCredentialsException.class)
	public ResponseEntity<String>handleInvalidCredentials(InvalidCredentialsException ex){
		return ResponseEntity
				.status(409)
				.body(ex.getMessage());
	}
	
	@ExceptionHandler(UserNotFoundException.class)
	public ResponseEntity<String>handleUserNotFound(UserNotFoundException ex){
		return ResponseEntity
				.status(404)
				.body(ex.getMessage());
	}

}
