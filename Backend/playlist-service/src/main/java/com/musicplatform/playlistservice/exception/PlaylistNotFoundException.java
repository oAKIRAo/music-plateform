package com.musicplatform.playlistservice.exception;

public class PlaylistNotFoundException extends RuntimeException {
    public PlaylistNotFoundException(Long id) {
        super("Playlist with id " + id + " not found");
    }
    public PlaylistNotFoundException(String message) {
        super(message);
    }
}
