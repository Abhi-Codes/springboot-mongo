package com.example.mongodb.exception;

import static org.springframework.http.HttpStatus.BAD_REQUEST;
import static org.springframework.http.HttpStatus.INTERNAL_SERVER_ERROR;

import java.util.HashMap;
import java.util.Map;

import javax.validation.ConstraintViolationException;
import javax.validation.constraints.NotNull;

import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.ServletWebRequest;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.multipart.MaxUploadSizeExceededException;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.example.mongodb.domain.ErrorDetails;

import lombok.extern.slf4j.Slf4j;

/**
 * The Class CommonExceptionHandler.
 */
@Order(Ordered.HIGHEST_PRECEDENCE)
@RestControllerAdvice
@Slf4j
public class CommonExceptionHandler extends ResponseEntityExceptionHandler {

	/**
	 * Handle http message not readable.
	 *
	 * @param ex      the ex
	 * @param headers the headers
	 * @param status  the status
	 * @param request the request
	 * @return the response entity
	 */
	@Override
	protected ResponseEntity<Object> handleHttpMessageNotReadable(HttpMessageNotReadableException ex,
			HttpHeaders headers, HttpStatus status, WebRequest request) {
		log.info("HttpMessageNotReadableException occured", ex);
		ErrorDetails errorDetails = new ErrorDetails(status.value(), "Malformed JSON request", ex,
				getRequestURI(request));
		return ResponseEntity.status(status).body(formatError(errorDetails));
	}

	/**
	 * Handle method argument not valid.
	 *
	 * @param ex      the ex
	 * @param headers the headers
	 * @param status  the status
	 * @param request the request
	 * @return the response entity
	 */
	@Override
	protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
			HttpHeaders headers, HttpStatus status, WebRequest request) {
		log.info("MethodArgumentNotValidException occured", ex);
		ErrorDetails errorDetails = new ErrorDetails(status.value(), "Validation Failed", ex,
				getRequestURI(request));
		return ResponseEntity.status(status).body(formatError(errorDetails));
	}

	/**
	 * Handle constraint violation exception.
	 *
	 * @param ex      the ex
	 * @param request the request
	 * @return the response entity
	 */
	@ExceptionHandler(ConstraintViolationException.class)
	@ResponseStatus(BAD_REQUEST)
	public final ResponseEntity<Object> handleConstraintViolationException(ConstraintViolationException ex,
			WebRequest request) {
		log.info("ConstraintViolationException occured", ex);
		ErrorDetails errorDetails = new ErrorDetails(BAD_REQUEST.value(), "Validation info", ex,
				getRequestURI(request));
		return ResponseEntity.status(BAD_REQUEST).body(formatError(errorDetails));
	}

	@ExceptionHandler(MaxUploadSizeExceededException.class)
	@ResponseStatus(BAD_REQUEST)
	public final ResponseEntity<Object> handleMaxUploadSizeExceededException(MaxUploadSizeExceededException ex,
			WebRequest request) {
		log.info("MaxUploadSizeExceededException occured", ex);
		ErrorDetails errorDetails = new ErrorDetails(BAD_REQUEST.value(),
				"The file size has exceeded the max allowed limit", ex,
				getRequestURI(request));
		return ResponseEntity.status(BAD_REQUEST).body(formatError(errorDetails));
	}

	@ExceptionHandler(PCQException.class)
	public final ResponseEntity<Object> handlePCQException(PCQException ex, WebRequest request) {
		log.info("PCQ Exception occured", ex);
		ErrorDetails errorDetails = new ErrorDetails(ex.errorCode, ex.getMessage(), ex, getRequestURI(request));
		return ResponseEntity.status(HttpStatus.resolve(ex.errorCode)).body(formatError(errorDetails));
	}


	/**
	 * Handle exceptions.
	 *
	 * @param ex      the ex
	 * @param request the request
	 * @return the response entity
	 */
	@ResponseStatus(INTERNAL_SERVER_ERROR)
	@ExceptionHandler(Exception.class)
	public final ResponseEntity<Object> handleExceptions(Exception ex, WebRequest request) {
		log.info("Exception occured", ex);
		ErrorDetails errorDetails = new ErrorDetails(INTERNAL_SERVER_ERROR.value(),
				"Something went wrong, please try again after sometime", ex, getRequestURI(request));
		return ResponseEntity.status(INTERNAL_SERVER_ERROR).body(formatError(errorDetails));
	}

	/**
	 * Gets the request URI.
	 *
	 * @param request the request
	 * @return the request URI
	 */
	private String getRequestURI(@NotNull WebRequest request) {
		return ((ServletWebRequest) request).getRequest().getRequestURI();
	}

	/**
	 * Format error.
	 *
	 * @param errorDetails the error details
	 * @return the map
	 */
	private Map<String, Object> formatError(ErrorDetails errorDetails) {
		Map<String, Object> error = new HashMap<>();
		error.put("error", errorDetails);
		return error;
	}
}
