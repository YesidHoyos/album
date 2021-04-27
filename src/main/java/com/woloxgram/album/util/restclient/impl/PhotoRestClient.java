package com.woloxgram.album.util.restclient.impl;

import java.util.List;

import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import com.woloxgram.album.model.Photo;
import com.woloxgram.album.util.exception.PhotoRestClientException;
import com.woloxgram.album.util.restclient.IPhotoRestClient;

@Component
public class PhotoRestClient implements IPhotoRestClient {
	
	private static final String FIND_ALL_URL = "https://jsonplaceholder.typicode.com/photos";
	private static final String FIND_PHOTOS_ERROR = "Ocurrió un error al momento de consumir los datos de todas las fotos";
	private static final String FIND_BY_ALBUM_URL = "https://jsonplaceholder.typicode.com/photos?albumId=%s";
	private static final String FIND_BY_ALBUM_ERROR = "Ocurrió un error al momento de consumir los datos de las fotos del album con id %s";
	
	private RestTemplate restTemplate;

	public PhotoRestClient(RestTemplate restTemplate) {
		this.restTemplate = restTemplate;
	}

	@Override
	public List<Photo> getAllPhotos() {
		ResponseEntity<List<Photo>> response = null;
		try {
			response = restTemplate.
					exchange(FIND_ALL_URL, HttpMethod.GET, null, new ParameterizedTypeReference<List<Photo>>(){});
		} catch (RestClientException e) {
			throw new PhotoRestClientException(FIND_PHOTOS_ERROR, e);
		}
		
		if(response.getStatusCode() != HttpStatus.OK) {
			throw new PhotoRestClientException(FIND_PHOTOS_ERROR);
		}
		
		return response.getBody();
	}

	@Override
	public List<Photo> getPhotosByAlbum(long albumId) {
		ResponseEntity<List<Photo>> response = null;
		try {
			response = restTemplate.
					exchange(String.format(FIND_BY_ALBUM_URL, albumId), HttpMethod.GET, null, new ParameterizedTypeReference<List<Photo>>(){});
		} catch (RestClientException e) {
			throw new PhotoRestClientException(FIND_PHOTOS_ERROR, e);
		}
		
		if(response.getStatusCode() != HttpStatus.OK) {
			throw new PhotoRestClientException(String.format(FIND_BY_ALBUM_ERROR, albumId));
		}
		
		return response.getBody();
	}

}
