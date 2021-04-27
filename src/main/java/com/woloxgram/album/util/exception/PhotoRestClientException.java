package com.woloxgram.album.util.exception;

public class PhotoRestClientException extends RuntimeException {

	private static final long serialVersionUID = -1043552199319671976L;

	public PhotoRestClientException(String message, Throwable cause) {
		super(message, cause);
	}

	public PhotoRestClientException(String message) {
		super(message);
	}
}
