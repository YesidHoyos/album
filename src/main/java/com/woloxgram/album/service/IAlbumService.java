package com.woloxgram.album.service;

import java.util.List;

import com.woloxgram.album.model.Album;

public interface IAlbumService {

	public List<Album> getAllAlbums();
	public List<Album> getAlbumsByUser(long userId);
}
