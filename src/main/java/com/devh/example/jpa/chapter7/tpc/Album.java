package com.devh.example.jpa.chapter7.tpc;

//@Entity
public class Album extends Item {
	private String artist;

	public String getArtist() {
		return artist;
	}

	public void setArtist(String artist) {
		this.artist = artist;
	}
}
