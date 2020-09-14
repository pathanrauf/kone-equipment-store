package com.kone.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class RestResponseEntityExceptionHandler extends ResponseEntityExceptionHandler
{
	@ExceptionHandler(value = { Exception.class })
	protected ResponseEntity<Object> handleAllException(Exception ex, WebRequest request) {
		String bodyOfResponse = ex.getMessage();
		return new ResponseEntity<Object>(bodyOfResponse, HttpStatus.BAD_REQUEST);
	}
}
