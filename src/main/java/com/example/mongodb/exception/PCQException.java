package com.example.mongodb.exception;

import java.text.MessageFormat;

import com.example.mongodb.enums.ErrorMessage;

public class PCQException extends RuntimeException {

	private static final long serialVersionUID = 1659791625928834215L;

	/** The error code. */
	public final int errorCode;

	/** The user message. */
	public final String developerMessage;

	public PCQException(ErrorMessage error) {
		super(error.getMsg());
		this.errorCode = error.getErrCode();
		this.developerMessage = error.getMsg();
	}

	public PCQException(ErrorMessage error, Object... param) {
		super(MessageFormat.format(error.getMsg(), param));
		this.errorCode = error.getErrCode();
		this.developerMessage = MessageFormat.format(error.getMsg(), param);
	}

	public PCQException(ErrorMessage error, Throwable throwable) {
		super(error.getMsg(), throwable);
		this.errorCode = error.getErrCode();
		this.developerMessage = throwable.getMessage();
	}

	public PCQException(ErrorMessage error, Throwable throwable, Object... param) {
		super(MessageFormat.format(error.getMsg(), param), throwable);
		this.errorCode = error.getErrCode();
		this.developerMessage = throwable.getMessage();
	}
}
