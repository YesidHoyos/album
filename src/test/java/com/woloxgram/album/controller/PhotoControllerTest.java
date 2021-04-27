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

import com.woloxgram.album.databuilder.PhotoTestDataBuilder;
import com.woloxgram.album.model.Photo;
import com.woloxgram.album.service.impl.PhotoService;

@AutoConfigureMockMvc
@WebMvcTest
@ContextConfiguration(classes = PhotoController.class)
class PhotoControllerTest {

	@Autowired
	private MockMvc mockMvc;

	@MockBean
	private PhotoService photoService;

	@Test
	void getAllPhotosTest() throws Exception {
		//arrange
		List<Photo> photos = new ArrayList<>();
		Photo photo = new PhotoTestDataBuilder().withAlbum(null).build();
		photos.add(photo);
		String bodyExpected = "[{\"id\":1,\"albumId\":1,\"title\":\"accusamus beatae ad facilis cum similique qui sunt\",\"url\":\"https://via.placeholder.com/600/92c952\",\"thumbnailUrl\":\"https://via.placeholder.com/150/92c952\"}]";
		when(photoService.getAllPhotos()).thenReturn(photos);

		//act
		MvcResult result = mockMvc.perform(MockMvcRequestBuilders.get("/photos")
				.accept(MediaType.APPLICATION_JSON))
				.andReturn();

		//assert
		Assertions.assertEquals(bodyExpected, result.getResponse().getContentAsString());
	}

	@Test
	void getPhotosByUserTest() throws Exception {
		//arrange
		List<Photo> photos = new ArrayList<>();
		Photo photo = new PhotoTestDataBuilder().build();
		photos.add(photo);
		String bodyExpected = "[{\"id\":1,\"albumId\":1,\"album\":{\"userId\":1,\"id\":1,\"title\":\"quidem molestiae enim\"},\"title\":\"accusamus beatae ad facilis cum similique qui sunt\",\"url\":\"https://via.placeholder.com/600/92c952\",\"thumbnailUrl\":\"https://via.placeholder.com/150/92c952\"}]";
		when(photoService.getPhotosByUser(ArgumentMatchers.anyLong())).thenReturn(photos);

		//act
		MvcResult result = mockMvc.perform(MockMvcRequestBuilders.get("/photos?userId=1")
				.accept(MediaType.APPLICATION_JSON))
				.andReturn();

		//assert
		Assertions.assertEquals(bodyExpected, result.getResponse().getContentAsString());
	}
}
