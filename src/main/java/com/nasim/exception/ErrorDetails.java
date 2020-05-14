package com.nasim.exception;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ErrorDetails {

	private Date timeStamp;
	
	private int status;
	
	private String title;
	
	private String detail;
	
	private Map<String,List<FieldValidationError>>errors= new HashMap<String, List<FieldValidationError>>();

	
	
	
	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public String getDetail() {
		return detail;
	}

	public void setDetail(String detail) {
		this.detail = detail;
	}

	

	public Date getTimeStamp() {
		return timeStamp;
	}

	public void setTimeStamp(Date timeStamp) {
		this.timeStamp = timeStamp;
	}

	public Map<String, List<FieldValidationError>> getErrors() {
		return errors;
	}

	public void setErrors(Map<String, List<FieldValidationError>> errors) {
		this.errors = errors;
	}

	
}
