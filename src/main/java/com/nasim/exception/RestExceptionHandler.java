package com.nasim.exception;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
@RestController
public class RestExceptionHandler extends ResponseEntityExceptionHandler {
     // internal exception
	@ExceptionHandler(Exception.class)
	public final ResponseEntity<Object> handleAllExceptions(Exception ex, WebRequest request) {
		ErrorDetails exceptionResponse = new ErrorDetails();
		exceptionResponse.setTimeStamp(new Date());
		exceptionResponse.setStatus(HttpStatus.INTERNAL_SERVER_ERROR.value());
		exceptionResponse.setTitle("Internal Server Exception");
		exceptionResponse.setDetail(ex.getMessage());
		return new ResponseEntity<Object>(exceptionResponse, HttpStatus.INTERNAL_SERVER_ERROR);
	}

	// user not found exception
	@ExceptionHandler(ResourceNotFoundException.class)
	public final ResponseEntity<Object> handleUserNotFoundException(ResourceNotFoundException ex, WebRequest request) {
		ErrorDetails exceptionResponse = new ErrorDetails();
		exceptionResponse.setTimeStamp(new Date());
		exceptionResponse.setStatus(HttpStatus.NOT_FOUND.value());
		exceptionResponse.setTitle("Resource Not Found Exception");
		exceptionResponse.setDetail(ex.getMessage());
		return new ResponseEntity<Object>(exceptionResponse, HttpStatus.NOT_FOUND);
	}

	// form field validation
	@Override
	protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
			HttpHeaders headers, HttpStatus status, WebRequest request) {

		ErrorDetails exceptionResponse = new ErrorDetails();
		exceptionResponse.setTimeStamp(new Date());
		exceptionResponse.setStatus(HttpStatus.BAD_REQUEST.value());
		exceptionResponse.setTitle("Bad Request 1");
		exceptionResponse.setDetail(ex.getMessage());

		List<FieldError> fieldError = ex.getBindingResult().getFieldErrors();
		for (FieldError error : fieldError) {
			List<FieldValidationError> fErrorList = exceptionResponse.getErrors().get(error.getField());
			if (fErrorList == null) {
				fErrorList = new ArrayList<FieldValidationError>();
				exceptionResponse.getErrors().put(error.getField(), fErrorList);
			}

			FieldValidationError vError = new FieldValidationError();
			vError.setCode(error.getCode());
			vError.setMessage(error.getDefaultMessage());
			fErrorList.add(vError);
		}

		return new ResponseEntity<Object>(exceptionResponse, HttpStatus.BAD_REQUEST);
	}

}
