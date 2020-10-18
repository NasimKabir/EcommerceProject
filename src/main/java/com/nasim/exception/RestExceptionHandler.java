package com.nasim.exception;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
@RestControllerAdvice
public class RestExceptionHandler extends ResponseEntityExceptionHandler {
     // internal exception
	@ExceptionHandler(Exception.class)
	public final ResponseEntity<Object> handleAllExceptions(Exception ex, WebRequest request) {
		Response exceptionResponse = Response.builder()
	                .status(HttpStatus.BAD_REQUEST.getReasonPhrase())
	                .statusCode(HttpStatus.BAD_REQUEST.value())
	                .timestamp(new Date().getTime()).build();
		return new ResponseEntity<Object>(exceptionResponse, HttpStatus.INTERNAL_SERVER_ERROR);
	}
	
	
	// form field validation
	@Override
	protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
			HttpHeaders headers, HttpStatus status, WebRequest request) {
		Response exceptionResponse = Response.builder()
				.errors(getCustomError(ex))
                .status(HttpStatus.BAD_REQUEST.getReasonPhrase())
                .statusCode(HttpStatus.BAD_REQUEST.value())
                .timestamp(new Date().getTime()).build();
		return new ResponseEntity<Object>(exceptionResponse, HttpStatus.BAD_REQUEST);
	}
	
	
		
	    private static List<ResponseError> getCustomError(Exception ex){
	    	List<ResponseError> errorList = new ArrayList<>();
	    	((MethodArgumentNotValidException) ex).getBindingResult().getFieldErrors().forEach(fieldError -> {
	        	ResponseError dto = ResponseError.builder()
	                    .field(fieldError.getField())
	                    .message(fieldError.getDefaultMessage()).build();
	        	errorList.add(dto);
	        });
	        return errorList;
	    }
	
}
