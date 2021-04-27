package com.woloxgram.album.service;

import java.util.List;

import com.woloxgram.album.model.Photo;

public interface IPhotoService {

	public List<Photo> getAllPhotos();
	public List<Photo> getPhotosByUser(long userId);
}
