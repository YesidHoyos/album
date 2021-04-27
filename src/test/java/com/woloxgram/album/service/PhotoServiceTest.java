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
import com.woloxgram.album.databuilder.PhotoTestDataBuilder;
import com.woloxgram.album.model.Album;
import com.woloxgram.album.model.Photo;
import com.woloxgram.album.service.impl.PhotoService;
import com.woloxgram.album.util.exception.AlbumRestClientException;
import com.woloxgram.album.util.exception.PhotoRestClientException;
import com.woloxgram.album.util.restclient.impl.AlbumRestClient;
import com.woloxgram.album.util.restclient.impl.PhotoRestClient;

@SpringBootTest
class PhotoServiceTest {

	@Mock
	private PhotoRestClient photoRestClient;
	
	@Mock
	private AlbumRestClient albumRestClient;
	
	@InjectMocks
	private PhotoService photoService;
	
	private static final String FIND_PHOTOS_ERROR = "Ocurrió un error al momento de consumir los datos de todas las fotos";
	private static final String FIND_PHOTOS_BY_ALBUM_ERROR = "Ocurrió un error al momento de consumir los datos de las fotos del album con id %s";
	private static final String FIND_ALBUMS_BY_USER_ERROR = "Ocurrió un error al momento de consumir los datos de los albumes del usuario con id %s";
	
	@Test
	void getAllPhotosSuccessfully() {
		//arrange
		List<Photo> photosExpected = new ArrayList<>();
		Photo photo = new PhotoTestDataBuilder().build();
		photosExpected.add(photo);
		when(photoRestClient.getAllPhotos()).thenReturn(photosExpected);
		
		//act
		List<Photo> photos = photoService.getAllPhotos();
		
		//assert
		Assertions.assertTrue(photos.contains(photo));
	}
	
	@Test
	void getAllPhotosWithError() {
		//arrange
		when(photoRestClient.getAllPhotos()).thenThrow(new PhotoRestClientException(FIND_PHOTOS_ERROR));
		
		//act
		try {
			photoService.getAllPhotos();
			fail();
		} catch (Exception e) {
			//assert
			Assertions.assertEquals(FIND_PHOTOS_ERROR, e.getMessage());
		}		
	}
	
	@Test
	void getPhotosByUserSuccessfully() {
		//arrange
		List<Album> albumsExpected = new ArrayList<>();
		Album album = new AlbumTestDataBuilder().build();
		albumsExpected.add(album);
		List<Photo> photosExpected = new ArrayList<>();
		Photo photo = new PhotoTestDataBuilder().build();
		photosExpected.add(photo);
		when(albumRestClient.getAlbumsByUser(ArgumentMatchers.anyLong())).thenReturn(albumsExpected);
		when(photoRestClient.getPhotosByAlbum(ArgumentMatchers.anyLong())).thenReturn(photosExpected);
		
		//act
		List<Photo> photos = photoService.getPhotosByUser(1L);
		
		//assert
		Assertions.assertTrue(photos.contains(photo));
	}
	
	@Test
	void getPhotosByUserWithAlbumError() {
		//arrange
		List<Album> albumsExpected = new ArrayList<>();
		Album album = new AlbumTestDataBuilder().build();
		albumsExpected.add(album);
		List<Photo> photosExpected = new ArrayList<>();
		Photo photo = new PhotoTestDataBuilder().build();
		photosExpected.add(photo);
		when(albumRestClient.getAlbumsByUser(ArgumentMatchers.anyLong())).thenThrow(new AlbumRestClientException(String.format(FIND_ALBUMS_BY_USER_ERROR, 1)));
		when(photoRestClient.getPhotosByAlbum(ArgumentMatchers.anyLong())).thenReturn(photosExpected);
		
		try {
			//act
			photoService.getPhotosByUser(1L);
			fail();
		} catch (Exception e) {
			//assert
			Assertions.assertEquals(String.format(FIND_ALBUMS_BY_USER_ERROR, 1), e.getMessage());
		}
	}
	
	@Test
	void getPhotosByUserWithPhotoError() {
		//arrange
		List<Album> albumsExpected = new ArrayList<>();
		Album album = new AlbumTestDataBuilder().build();
		albumsExpected.add(album);
		List<Photo> photosExpected = new ArrayList<>();
		Photo photo = new PhotoTestDataBuilder().build();
		photosExpected.add(photo);
		when(albumRestClient.getAlbumsByUser(ArgumentMatchers.anyLong())).thenReturn(albumsExpected);
		when(photoRestClient.getPhotosByAlbum(ArgumentMatchers.anyLong())).thenThrow(new PhotoRestClientException(String.format(FIND_PHOTOS_BY_ALBUM_ERROR, 1)));
		
		try {
			//act
			photoService.getPhotosByUser(1L);
			fail();
		} catch (Exception e) {
			//assert
			Assertions.assertEquals(String.format(FIND_PHOTOS_BY_ALBUM_ERROR, 1), e.getMessage());
		}
	}
}
