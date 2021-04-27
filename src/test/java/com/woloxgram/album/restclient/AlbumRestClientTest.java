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

import com.woloxgram.album.databuilder.AlbumTestDataBuilder;
import com.woloxgram.album.model.Album;
import com.woloxgram.album.util.restclient.impl.AlbumRestClient;

@SpringBootTest
class AlbumRestClientTest {
	
	@Mock
	private RestTemplate restTemplate;
	
	@InjectMocks
	private AlbumRestClient albumRestClient;
	
	private static final String FIND_ALBUMS_ERROR = "Ocurrió un error al momento de consumir los datos de todos los albumes";
	private static final String FIND_BY_USER_ERROR = "Ocurrió un error al momento de consumir los datos de los albumes del usuario con id %s";
	
	@Test
	void getAllAlbumsSuccessfully() {
		//arrange
		List<Album> albumsExpected = new ArrayList<>();
		Album album = new AlbumTestDataBuilder().build();
		albumsExpected.add(album);
		ResponseEntity<List<Album>> response = new ResponseEntity<List<Album>>(albumsExpected, HttpStatus.OK);
		when(restTemplate.exchange(ArgumentMatchers.anyString(),
		        ArgumentMatchers.any(HttpMethod.class),
		        ArgumentMatchers.any(),
		        ArgumentMatchers.<ParameterizedTypeReference<List<Album>>>any()))
		.thenReturn(response);
		
		//act
		List<Album> albums = albumRestClient.getAllAlbums();
		
		//assert
		Assertions.assertTrue(albums.contains(album));
	}
	
	@Test
	void getAllAlbumsWithEstatus404() {
		//arrange
		ResponseEntity<List<Album>> response = new ResponseEntity<List<Album>>(HttpStatus.NOT_FOUND);
		when(restTemplate.exchange(ArgumentMatchers.anyString(),
		        ArgumentMatchers.any(HttpMethod.class),
		        ArgumentMatchers.any(),
		        ArgumentMatchers.<ParameterizedTypeReference<List<Album>>>any()))
		.thenReturn(response);
		
		//act
		try {
			albumRestClient.getAllAlbums();
			fail();
		} catch (Exception e) {
			//assert
			Assertions.assertEquals(FIND_ALBUMS_ERROR, e.getMessage());
		}		
	}
	
	@Test
	void getAlbumsByUserSuccessfully() {
		//arrange
		List<Album> albumsExpected = new ArrayList<>();
		Album album = new AlbumTestDataBuilder().build();
		albumsExpected.add(album);
		ResponseEntity<List<Album>> response = new ResponseEntity<List<Album>>(albumsExpected, HttpStatus.OK);
		when(restTemplate.exchange(ArgumentMatchers.anyString(),
		        ArgumentMatchers.any(HttpMethod.class),
		        ArgumentMatchers.any(),
		        ArgumentMatchers.<ParameterizedTypeReference<List<Album>>>any()))
		.thenReturn(response);
		
		//act
		List<Album> albums = albumRestClient.getAlbumsByUser(1L);
		
		//assert
		Assertions.assertTrue(albums.contains(album));
	}
	
	@Test
	void getAlbumsByUserWithEstatus404() {
		//arrange
		ResponseEntity<List<Album>> response = new ResponseEntity<List<Album>>(HttpStatus.NOT_FOUND);
		when(restTemplate.exchange(ArgumentMatchers.anyString(),
		        ArgumentMatchers.any(HttpMethod.class),
		        ArgumentMatchers.any(),
		        ArgumentMatchers.<ParameterizedTypeReference<List<Album>>>any()))
		.thenReturn(response);
		
		//act
		try {
			albumRestClient.getAlbumsByUser(1L);
			fail();
		} catch (Exception e) {
			//assert
			Assertions.assertEquals(String.format(FIND_BY_USER_ERROR, 1), e.getMessage());
		}		
	}
}