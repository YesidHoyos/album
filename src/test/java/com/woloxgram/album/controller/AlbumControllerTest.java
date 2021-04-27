package com.woloxgram.album.controller;

import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentMatchers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import com.woloxgram.album.databuilder.AlbumTestDataBuilder;
import com.woloxgram.album.model.Album;
import com.woloxgram.album.service.impl.AlbumService;

@AutoConfigureMockMvc
@WebMvcTest
@ContextConfiguration(classes = AlbumController.class)
class AlbumControllerTest {

	@Autowired
	private MockMvc mockMvc;
	
	@MockBean
	private AlbumService albumService;
	
	@Test
	void getAllAlbumsTest() throws Exception {
		//arrange
		List<Album> albums = new ArrayList<>();
		Album album = new AlbumTestDataBuilder().build();
		albums.add(album);
		String bodyExpected = "[{\"userId\":1,\"id\":1,\"title\":\"quidem molestiae enim\"}]";
		when(albumService.getAllAlbums()).thenReturn(albums);
		
		//act
		MvcResult result = mockMvc.perform(MockMvcRequestBuilders.get("/albums")
				.accept(MediaType.APPLICATION_JSON))
				.andReturn();
		
		//assert
		Assertions.assertEquals(bodyExpected, result.getResponse().getContentAsString());
	}
	
	@Test
	void getAlbumsByUserTest() throws Exception {
		//arrange
		List<Album> albums = new ArrayList<>();
		Album album = new AlbumTestDataBuilder().build();
		albums.add(album);
		String bodyExpected = "[{\"userId\":1,\"id\":1,\"title\":\"quidem molestiae enim\"}]";
		when(albumService.getAlbumsByUser(ArgumentMatchers.anyLong())).thenReturn(albums);
		
		//act
		MvcResult result = mockMvc.perform(MockMvcRequestBuilders.get("/albums?userId=1")
				.accept(MediaType.APPLICATION_JSON))
				.andReturn();
		
		//assert
		Assertions.assertEquals(bodyExpected, result.getResponse().getContentAsString());
	}
}
