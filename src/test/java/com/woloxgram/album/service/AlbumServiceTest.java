package com.woloxgram.album.service;

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

import com.woloxgram.album.databuilder.AlbumTestDataBuilder;
import com.woloxgram.album.model.Album;
import com.woloxgram.album.service.impl.AlbumService;
import com.woloxgram.album.util.exception.AlbumRestClientException;
import com.woloxgram.album.util.restclient.impl.AlbumRestClient;

@SpringBootTest
class AlbumServiceTest {

	@Mock
	private AlbumRestClient albumRestClient;
	
	@InjectMocks
	private AlbumService albumService;
	
	private static final String FIND_ALBUMS_ERROR = "Ocurrió un error al momento de consumir los datos de todos los albumes";
	private static final String FIND_BY_USER_ERROR = "Ocurrió un error al momento de consumir los datos de los albumes del usuario con id %s";
	
	@Test
	void getAllAlbumsSuccessfully() {
		//arrange
		List<Album> albumsExpected = new ArrayList<>();
		Album album = new AlbumTestDataBuilder().build();
		albumsExpected.add(album);
		when(albumRestClient.getAllAlbums()).thenReturn(albumsExpected);
		
		//act
		List<Album> albums = albumService.getAllAlbums();
		
		//assert
		Assertions.assertTrue(albums.contains(album));
	}
	
	@Test
	void getAllAlbumsWithError() {
		//arrange
		when(albumRestClient.getAllAlbums()).thenThrow(new AlbumRestClientException(FIND_ALBUMS_ERROR));
		
		//act
		try {
			albumService.getAllAlbums();
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
		when(albumRestClient.getAlbumsByUser(ArgumentMatchers.anyLong())).thenReturn(albumsExpected);
		
		//act
		List<Album> albums = albumService.getAlbumsByUser(1L);
		
		//assert
		Assertions.assertTrue(albums.contains(album));
	}
	
	@Test
	void getAlbumsByUserWithError() {
		//arrange
		when(albumRestClient.getAlbumsByUser(ArgumentMatchers.anyLong())).thenThrow(new AlbumRestClientException(FIND_BY_USER_ERROR));
		
		//act
		try {
			albumService.getAlbumsByUser(1L);
			fail();
		} catch (Exception e) {
			//assert
			Assertions.assertEquals(FIND_BY_USER_ERROR, e.getMessage());
		}		
	}
	
}
