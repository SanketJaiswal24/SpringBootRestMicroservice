package com.example.rentalHome.Domains;

import java.util.Arrays;
import java.util.List;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;
import org.springframework.http.HttpStatus;

@ApiModel(description = "This Resource Model Delegate To HandleExcpetion Reponse")
public class ExceptionResponse {

    @ApiModelProperty(notes = "Status code")
    private HttpStatus status;

    @ApiModelProperty(notes = "Deatiled Message of Error")
    private String message;

    @ApiModelProperty(notes = "Error Type")
    private List<String> errors;

    public ExceptionResponse(final HttpStatus status, final String message, final List<String> errors) {
        super();
        this.status = status;
        this.message = message;
        this.errors = errors;
    }
    public ExceptionResponse(final HttpStatus status, final String message, final String error) {
        super();
        this.status = status;
        this.message = message;
        errors = Arrays.asList(error);
    }
	
    public ExceptionResponse() {
		super();
	}
	public HttpStatus getStatus() {
		return status;
	}
	public void setStatus(HttpStatus status) {
		this.status = status;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public List<String> getErrors() {
		return errors;
	}
	public void setErrors(List<String> errors) {
		this.errors = errors;
	}
	@Override
	public String toString() {
		return "ExceptionResponse [status=" + status + ", message=" + message + ", errors=" + errors + "]";
	}
   
    
}
