package com.woloxgram.album.service.impl;

import java.util.LinkedList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.woloxgram.album.model.Album;
import com.woloxgram.album.model.Photo;
import com.woloxgram.album.service.IPhotoService;
import com.woloxgram.album.util.restclient.IAlbumRestClient;
import com.woloxgram.album.util.restclient.IPhotoRestClient;

@Service
public class PhotoService implements IPhotoService {
	
	private IPhotoRestClient photoRestClient;
	private IAlbumRestClient albumRestClient;

	public PhotoService(IPhotoRestClient photoRestClient, IAlbumRestClient albumRestClient) {
		this.photoRestClient = photoRestClient;
		this.albumRestClient = albumRestClient;
	}

	@Override
	public List<Photo> getAllPhotos() {
		return photoRestClient.getAllPhotos();
	}

	@Override
	public List<Photo> getPhotosByUser(long userId) {
		List<Album> albumsByUser = albumRestClient.getAlbumsByUser(userId);
		List<Photo> photosByUser = new LinkedList<>();
		albumsByUser.stream().forEach(album -> {
			List<Photo> photosByAlbum = photoRestClient.getPhotosByAlbum(album.getId());
			photosByAlbum.forEach(photo -> photo.setAlbum(album));
			photosByUser.addAll(photosByAlbum);
		});
		return photosByUser;
	}

}
