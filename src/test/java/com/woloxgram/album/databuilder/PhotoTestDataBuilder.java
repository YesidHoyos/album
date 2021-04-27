package com.woloxgram.album.databuilder;

import com.woloxgram.album.model.Album;
import com.woloxgram.album.model.Photo;

public class PhotoTestDataBuilder {

	private static final Long ID = 1L;
	private static final Long ALBUM_ID = 1L;
	private static final Album ALBUM = new Album(1L, 1L, "quidem molestiae enim");
	private static final String TITLE = "accusamus beatae ad facilis cum similique qui sunt";
	private static final String URL = "https://via.placeholder.com/600/92c952";
	private static final String THUMBNAIL_URL = "https://via.placeholder.com/150/92c952";
	
	private Long id;
	private Long albumId;
	private Album album;
	private String title;
	private String url;
	private String thumbnailUrl;
	
	public PhotoTestDataBuilder() {
		this.id = ID;
		this.albumId = ALBUM_ID;
		this.album = ALBUM;
		this.title = TITLE;
		this.url = URL;
		this.thumbnailUrl = THUMBNAIL_URL;
	}
	
	public Photo build() {
		return new Photo(this.id, this.albumId, this.album, this.title, this.url, this.thumbnailUrl);
	}
	
	public PhotoTestDataBuilder withId(Long id) {
		this.id = id;
		return this;
	}
	public PhotoTestDataBuilder withAlbumId(long albumId) {
		this.albumId = albumId;
		return this;
	}
	public PhotoTestDataBuilder withAlbum(Album album) {
		this.album = album;
		return this;
	}
	public PhotoTestDataBuilder withTitle(String title) {
		this.title = title;
		return this;
	}
	public PhotoTestDataBuilder withUrl(String url) {
		this.url = url;
		return this;
	}
	public PhotoTestDataBuilder withThumbnailUrl(String thumbnailUrl) {
		this.thumbnailUrl = thumbnailUrl;
		return this;
	}
}
