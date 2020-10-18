package com.nasim.exception;

import com.nasim.model.User;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
public class MessageResponse {
	private String message;
	 private Object content;
	 
	public MessageResponse(String message) {
		super();
		this.message = message;
	}

	public MessageResponse(String message, Object content) {
		super();
		this.message = message;
		this.content = content;
	}
	 
	 
}
