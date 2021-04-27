package com.woloxgram.album.restclient;

import static org.junit.jupiter.api.Assertions.fail;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentMatchers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import com.woloxgram.album.databuilder.PhotoTestDataBuilder;
import com.woloxgram.album.model.Photo;
import com.woloxgram.album.util.restclient.impl.PhotoRestClient;

@SpringBootTest
class PhotoRestClientTest {
	
	@Mock
	private RestTemplate restTemplate;
	
	@InjectMocks
	private PhotoRestClient photoRestClient;
	
	private static final String FIND_PHOTOS_ERROR = "Ocurrió un error al momento de consumir los datos de todas las fotos";
	private static final String FIND_BY_ALBUM_ERROR = "Ocurrió un error al momento de consumir los datos de las fotos del album con id %s";
	
	@Test
	void getAllPhotosSuccessfully() {
		//arrange
		List<Photo> photosExpected = new ArrayList<>();
		Photo photo = new PhotoTestDataBuilder().build();
		photosExpected.add(photo);
		ResponseEntity<List<Photo>> response = new ResponseEntity<List<Photo>>(photosExpected, HttpStatus.OK);
		when(restTemplate.exchange(ArgumentMatchers.anyString(),
		        ArgumentMatchers.any(HttpMethod.class),
		        ArgumentMatchers.any(),
		        ArgumentMatchers.<ParameterizedTypeReference<List<Photo>>>any()))
		.thenReturn(response);
		
		//act
		List<Photo> photos = photoRestClient.getAllPhotos();
		
		//assert
		Assertions.assertTrue(photos.contains(photo));
	}
	
	@Test
	void getAllPhotosWithEstatus404() {
		//arrange
		ResponseEntity<List<Photo>> response = new ResponseEntity<List<Photo>>(HttpStatus.NOT_FOUND);
		when(restTemplate.exchange(ArgumentMatchers.anyString(),
		        ArgumentMatchers.any(HttpMethod.class),
		        ArgumentMatchers.any(),
		        ArgumentMatchers.<ParameterizedTypeReference<List<Photo>>>any()))
		.thenReturn(response);
		
		//act
		try {
			photoRestClient.getAllPhotos();
			fail();
		} catch (Exception e) {
			//assert
			Assertions.assertEquals(FIND_PHOTOS_ERROR, e.getMessage());
		}		
	}
	
	@Test
	void getPhotosByAlbumSuccessfully() {
		//arrange
		List<Photo> photosExpected = new ArrayList<>();
		Photo photo = new PhotoTestDataBuilder().build();
		photosExpected.add(photo);
		ResponseEntity<List<Photo>> response = new ResponseEntity<List<Photo>>(photosExpected, HttpStatus.OK);
		when(restTemplate.exchange(ArgumentMatchers.anyString(),
		        ArgumentMatchers.any(HttpMethod.class),
		        ArgumentMatchers.any(),
		        ArgumentMatchers.<ParameterizedTypeReference<List<Photo>>>any()))
		.thenReturn(response);
		
		//act
		List<Photo> photos = photoRestClient.getPhotosByAlbum(1L);
		
		//assert
		Assertions.assertTrue(photos.contains(photo));
	}
	
	@Test
	void getPhotosByAlbumWithEstatus404() {
		//arrange
		ResponseEntity<List<Photo>> response = new ResponseEntity<List<Photo>>(HttpStatus.NOT_FOUND);
		when(restTemplate.exchange(ArgumentMatchers.anyString(),
		        ArgumentMatchers.any(HttpMethod.class),
		        ArgumentMatchers.any(),
		        ArgumentMatchers.<ParameterizedTypeReference<List<Photo>>>any()))
		.thenReturn(response);
		
		//act
		try {
			photoRestClient.getPhotosByAlbum(1L);
			fail();
		} catch (Exception e) {
			//assert
			Assertions.assertEquals(String.format(FIND_BY_ALBUM_ERROR, 1), e.getMessage());
		}		
	}
}