package com.org.app.exceptions;

import java.time.LocalDateTime;

import org.springframework.http.HttpStatus;

import com.fasterxml.jackson.annotation.JsonFormat;

/**
 * @author Shivraj 
 * This pojo class holds the API error details.
 */
public class ApiError {

	/**
	 * http status code
	 */
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy hh:mm:ss")
	private HttpStatus status;

	/**
	 * current timestamp
	 */
	private String timestamp;

	/**
	 * scenario message
	 */
	private String message;

	/**
	 * default constructor
	 */
	private ApiError() {
		timestamp = LocalDateTime.now().toString();
	}

	/**
	 * @param status
	 *            Parameterized constructor
	 */
	public ApiError(HttpStatus status) {
		this();
		this.status = status;
	}

	/**
	 * @param status
	 * @param message
	 *            Parameterized constructor
	 */
	public ApiError(HttpStatus status, String message) {
		this();
		this.status = status;
		this.message = message;
	}

	// setters and getters

	/**
	 * @return String timestamp
	 */
	public String getTimestamp() {
		return timestamp;
	}

	/**
	 * @param timestamp
	 */
	public void setTimestamp(String timestamp) {
		this.timestamp = timestamp;
	}

	/**
	 * @return String message
	 */
	public String getMessage() {
		return message;
	}

	/**
	 * @param message
	 */
	public void setMessage(String message) {
		this.message = message;
	}

}
