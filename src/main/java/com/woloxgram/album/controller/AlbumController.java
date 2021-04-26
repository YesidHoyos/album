package com.woloxgram.album.controller;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.woloxgram.album.model.Album;
import com.woloxgram.album.service.IAlbumService;

@RestController
@RequestMapping("/albums")
public class AlbumController {

	private IAlbumService albumService;

	public AlbumController(IAlbumService albumService) {
		this.albumService = albumService;
	}
	
	@GetMapping
	public List<Album> getAlbums(@RequestParam(required = false) String userId) {
		if(userId == null)
			return albumService.getAllAlbums();
		else
			return albumService.getAlbumsByUser(Long.parseLong(userId));
	}
}
