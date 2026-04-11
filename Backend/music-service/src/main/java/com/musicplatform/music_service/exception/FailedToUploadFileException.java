package com.musicplatform.music_service.exception;

public class FailedToUploadFileException extends RuntimeException {
    public FailedToUploadFileException(String message) {
        super("Failed to upload file: " + message);
    }
}
