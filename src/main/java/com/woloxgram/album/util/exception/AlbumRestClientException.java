package com.woloxgram.album.util.exception;

public class AlbumRestClientException extends RuntimeException {

	private static final long serialVersionUID = 7424253696674981843L;

	public AlbumRestClientException(String message, Throwable cause) {
		super(message, cause);
	}
	
	public AlbumRestClientException(String message) {
		super(message);
	}
}
