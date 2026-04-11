package com.musicplatform.music_service.exception;

public class SongFileNotFoundException extends RuntimeException {
  public SongFileNotFoundException(String filePath) {
    super("File not found at path: " + filePath);
  }
}
