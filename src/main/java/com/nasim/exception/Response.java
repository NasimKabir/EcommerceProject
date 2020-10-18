package com.nasim.exception;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonInclude;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Response {
	 @JsonInclude(JsonInclude.Include.ALWAYS)
	    private long timestamp;
	    @JsonInclude(JsonInclude.Include.ALWAYS)
	    private int statusCode;
	    @JsonInclude(JsonInclude.Include.ALWAYS)
	    private String status;
	    @JsonInclude(JsonInclude.Include.ALWAYS)
	    private String message;
	    @JsonInclude(JsonInclude.Include.NON_NULL)
	    private Object content;
	    @JsonInclude(JsonInclude.Include.NON_NULL)
	    private List<ResponseError> errors;
}
