package com.woloxgram.album.util.restclient;

import java.util.List;

import com.woloxgram.album.model.Photo;

public interface IPhotoRestClient {

	public List<Photo> getAllPhotos();
	public List<Photo> getPhotosByAlbum(long albumId);
}
