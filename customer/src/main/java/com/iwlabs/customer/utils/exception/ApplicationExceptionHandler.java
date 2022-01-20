package com.iwlabs.customer.utils.exception;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.Date;

@ControllerAdvice
@Slf4j
public class ApplicationExceptionHandler extends ResponseEntityExceptionHandler {

	@ExceptionHandler(value = Exception.class)
	public final ResponseEntity<ExceptionResponse> handleAllException(Exception ex, WebRequest request) {
		log.info(ex.getClass().getName() + " Occurred::", ex);
		log.error("Message " + ex.getMessage() + ex.getStackTrace());
		ExceptionResponse exceptionResponse = new ExceptionResponse();

		exceptionResponse.setStatus(HttpStatus.BAD_REQUEST);
		exceptionResponse.setMessage(ex.getLocalizedMessage());
		exceptionResponse.setTimestamp(new Date());

		return new ResponseEntity<>(exceptionResponse, HttpStatus.BAD_REQUEST);
	}

	@ExceptionHandler(value = ResourceNotFoundException.class)
	public final ResponseEntity<ExceptionResponse> handleNotFoundException(ResourceNotFoundException nfe, WebRequest request) {

		String errorMessageDescription = nfe.getLocalizedMessage();

		if (errorMessageDescription == null)
			errorMessageDescription = nfe.getMessage();

		ExceptionResponse exceptionResponse = new ExceptionResponse();

		exceptionResponse.setStatus(HttpStatus.NOT_FOUND);
		exceptionResponse.setMessage(errorMessageDescription);
		exceptionResponse.setTimestamp(new Date());
		exceptionResponse.setErrorCode("L-001");

		return new ResponseEntity<>(exceptionResponse, HttpStatus.NOT_FOUND);
	}

}
