package com.puxintech.tywl.model.exception;

public class ServiceException extends TywlException {

	private static final long serialVersionUID = 1L;

	public ServiceException(String message, Throwable cause) {
		super(400, message, cause);
	}

	public ServiceException(String message) {
		super(400, message);
	}

	public ServiceException(Throwable cause) {
		super(400, cause);
	}

}
