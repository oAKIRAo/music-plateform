package com.musicplatform.playlistservice.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(PlaylistNotFoundException.class)
  public ResponseEntity<String> handlePlaylistNotFoundException(PlaylistNotFoundException e) {
      return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
    }
    @ExceptionHandler(SongNotFoundInPlaylistException.class)
  public ResponseEntity<String> handleSongNotFoundInPlaylistException(SongNotFoundInPlaylistException e) {
      return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
    }
}

