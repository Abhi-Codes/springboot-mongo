package com.example.mongodb.domain;

import static com.fasterxml.jackson.annotation.JsonFormat.Shape.STRING;

import java.io.Serializable;
import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * The Class ErrorDetailsDto.
 */
@Data
@AllArgsConstructor
@JsonInclude(Include.NON_NULL)
public class ErrorDetails implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 8098236020611802489L;
	private static final String DATE_FORMAT = "yyyy-MM-dd'T'HH:mm:ss.SSS'Z'";

	private int status;
	private String message;
	private String type;
	private String path;

	@JsonFormat(shape = STRING, pattern = DATE_FORMAT)
	@DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
	private Date timestamp;

	private ErrorDetails() {
		timestamp = new Date();
	}

	public ErrorDetails(Throwable ex) {
		this();
		this.status = HttpStatus.INTERNAL_SERVER_ERROR.value();
		this.message = "Unexpected error";
		this.type = ex.getClass().getSimpleName();
	}

	public ErrorDetails(int status, String message, Throwable ex) {
		this();
		this.status = status;
		this.message = message;
		this.type = ex.getClass().getSimpleName();
	}

	public ErrorDetails(int status, String message, Throwable ex, String path) {
		this();
		this.status = status;
		this.message = message;
		this.type = ex.getClass().getSimpleName();
		this.path = path;
	}
}
