package com.woloxgram.album.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.woloxgram.album.util.exception.AlbumRestClientException;

@ControllerAdvice
public class ExceptionController extends ResponseEntityExceptionHandler {
	
	@ExceptionHandler(value = {AlbumRestClientException.class})
	public ResponseEntity<Object> albumRestClientException(AlbumRestClientException excepcion, WebRequest request) {
		Map<String, String> response = new HashMap<>();
		response.put("message", excepcion.getMessage());
		response.put("cause", excepcion.getCause().getMessage());
        return handleExceptionInternal(excepcion, response, new HttpHeaders(), HttpStatus.BAD_GATEWAY, request);		
	}
	
	@ExceptionHandler(value = {MethodArgumentTypeMismatchException.class})
	public ResponseEntity<Object> genericException(MethodArgumentTypeMismatchException excepcion, WebRequest request) {
		Map<String, String> response = new HashMap<>();
		response.put("message", excepcion.getMessage());
		response.put("cause", excepcion.getCause().getMessage());
        return handleExceptionInternal(excepcion, response, new HttpHeaders(), HttpStatus.BAD_REQUEST, request);		
	}

}