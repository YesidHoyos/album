package com.woloxgram.album.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;

import com.woloxgram.album.model.Album;
import com.woloxgram.album.service.IAlbumService;
import com.woloxgram.album.util.restclient.IAlbumRestClient;

@Service
public class AlbumService implements IAlbumService {

	private IAlbumRestClient albumRestClient;

	public AlbumService(IAlbumRestClient albumRestClient) {
		this.albumRestClient = albumRestClient;
	}

	@Override
	public List<Album> getAllAlbums() {
		return albumRestClient.getAllAlbums();
	}

	@Override
	public List<Album> getAlbumsByUser(long userId) {
		return albumRestClient.getAlbumsByUser(userId);
	}
}
