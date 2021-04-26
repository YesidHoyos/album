package com.woloxgram.album.util.restclient.impl;

import java.util.List;

import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import com.woloxgram.album.model.Album;
import com.woloxgram.album.util.exception.AlbumRestClientException;
import com.woloxgram.album.util.restclient.IAlbumRestClient;

@Component
public class AlbumRestClient implements IAlbumRestClient {

	private static final String FIND_ALL_URL = "https://jsonplaceholder.typicode.com/albums";
	private static final String FIND_ALBUMS_ERROR = "Ocurrió un error al momento de consumir los datos de todos los albumes";
	private static final String FIND_BY_USER_URL = "https://jsonplaceholder.typicode.com/users/%s/albums";
	private static final String FIND_BY_USER_ERROR = "Ocurrió un error al momento de consumir los datos de los albumes del usuario con id %s";
	
	private RestTemplate restTemplate;

	public AlbumRestClient(RestTemplate restTemplate) {
		this.restTemplate = restTemplate;
	}
	
	@Override
	public List<Album> getAllAlbums() {
		ResponseEntity<List<Album>> response = null;
		try {
			response = restTemplate.
					exchange(FIND_ALL_URL, HttpMethod.GET, null, new ParameterizedTypeReference<List<Album>>(){});
		} catch (RestClientException e) {
			throw new AlbumRestClientException(FIND_ALBUMS_ERROR, e);
		}
		
		if(response.getStatusCode() != HttpStatus.OK) {
			throw new AlbumRestClientException(FIND_ALBUMS_ERROR);
		}
		
		return response.getBody();
	}
	
	@Override
	public List<Album> getAlbumsByUser(long userId) {
		ResponseEntity<List<Album>> response = null;
		try {
			response = restTemplate.
					exchange(String.format(FIND_BY_USER_URL, userId), HttpMethod.GET, null, new ParameterizedTypeReference<List<Album>>(){});
		} catch (RestClientException e) {
			throw new AlbumRestClientException(FIND_BY_USER_ERROR, e);
		}
		
		if(response.getStatusCode() != HttpStatus.OK) {
			throw new AlbumRestClientException(FIND_BY_USER_ERROR);
		}
		
		return response.getBody();
	}
}
