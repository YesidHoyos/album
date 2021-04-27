package com.woloxgram.album.controller;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.woloxgram.album.model.Photo;
import com.woloxgram.album.service.IPhotoService;

@RestController
@RequestMapping("/photos")
public class PhotoController {

	private IPhotoService photoService;

	public PhotoController(IPhotoService photoService) {
		this.photoService = photoService;
	}
	
	@GetMapping
	public List<Photo> getPhotos(@RequestParam(required = false) String userId) {
		if(userId == null)
			return photoService.getAllPhotos();
		else
			return photoService.getPhotosByUser(Long.parseLong(userId));
	}
}
