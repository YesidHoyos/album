package com.woloxgram.album.util.restclient;

import java.util.List;

import com.woloxgram.album.model.Album;

public interface IAlbumRestClient {

	public List<Album> getAllAlbums();
	public List<Album> getAlbumsByUser(long userId);
}
