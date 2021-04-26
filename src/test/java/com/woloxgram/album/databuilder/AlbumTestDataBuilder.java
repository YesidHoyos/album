package com.woloxgram.album.databuilder;

import com.woloxgram.album.model.Album;

public class AlbumTestDataBuilder {

	private static final Long ID = 1L;
	private static final Long USER_ID = 1L;
	private static final String TITLE = "quidem molestiae enim";
	
	private Long id;
	private Long userId;
	private String title;
	
	public AlbumTestDataBuilder() {
		this.id = ID;
		this.userId = USER_ID;
		this.title = TITLE;
	}
	
	public Album build() {
		return new Album(this.userId, this.id, this.title);
	}
	
	public AlbumTestDataBuilder withId(Long id) {
		this.id = id;
		return this;
	}
	public AlbumTestDataBuilder withUserId(Long userId) {
		this.userId = userId;
		return this;
	}
	public AlbumTestDataBuilder withTitle(String title) {
		this.title = title;
		return this;
	}
}
