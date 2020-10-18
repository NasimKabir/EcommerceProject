package com.nasim.exception;

import com.fasterxml.jackson.annotation.JsonInclude;

import lombok.Builder;
import lombok.Data;
@Data
@Builder
public class ResponseError {

	 @JsonInclude(JsonInclude.Include.NON_NULL)
	    private String field;
	    @JsonInclude(JsonInclude.Include.NON_NULL)
	    private String message;
}
