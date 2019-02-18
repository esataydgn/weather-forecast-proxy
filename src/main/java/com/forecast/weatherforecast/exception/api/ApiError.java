package com.forecast.weatherforecast.exception.api;

import org.springframework.http.HttpStatus;

public class ApiError {
	private HttpStatus status;
	private String message;
	private String developerMessage;
	
	public ApiError(final HttpStatus status, String message, String developerMessage) {
		super();
		this.status = status;
		this.message = message;
		this.developerMessage = developerMessage;
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
	public String getDeveloperMessage() {
		return developerMessage;
	}
	public void setDeveloperMessage(String developerMessage) {
		this.developerMessage = developerMessage;
	}
}