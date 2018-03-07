package com.puxintech.tywl.model.exception;

/**
 * @author yanhai
 */
public class TywlException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public static final int DEFAULT_STATUS_CODE = 500;

	public static final String DEFAULT_MESSAGE = "unknow";

	private int statusCode = DEFAULT_STATUS_CODE;

	private String message = DEFAULT_MESSAGE;

	public TywlException() {
	}

	public TywlException(int statusCode, String message, Throwable cause) {
		super(message, cause);
		this.statusCode = statusCode;
		this.message = message;
	}

	public TywlException(int statusCode, String message) {
		this(statusCode, message, null);
	}

	public TywlException(String message) {
		this(DEFAULT_STATUS_CODE, message, null);
	}

	public TywlException(String message, Throwable cause) {
		this(DEFAULT_STATUS_CODE, message, cause);
	}

	public TywlException(Throwable cause) {
		this(DEFAULT_STATUS_CODE, DEFAULT_MESSAGE, cause);
	}

	public TywlException(int statusCode, Throwable cause) {
		this(statusCode, DEFAULT_MESSAGE, cause);
	}

	public int getStatusCode() {
		return statusCode;
	}

	public String getMessage() {
		return message;
	}

}
