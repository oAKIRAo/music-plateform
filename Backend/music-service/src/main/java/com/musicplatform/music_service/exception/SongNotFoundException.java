package com.musicplatform.music_service.exception;

public class SongNotFoundException extends RuntimeException {
    public SongNotFoundException(Long id) {
        super("Could not find song with id " + id);
    }
    public SongNotFoundException(String message) {
        super(message);
    }
}
