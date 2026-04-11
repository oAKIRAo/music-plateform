package com.musicplatform.playlistservice.exception;

public class SongNotFoundInPlaylistException extends RuntimeException {

    public SongNotFoundInPlaylistException(Long playlistId,Long songId) {
      super("Song " + songId + " not found in playlist " + playlistId);
    }

    public SongNotFoundInPlaylistException(String message) {
        super(message);
    }
}
