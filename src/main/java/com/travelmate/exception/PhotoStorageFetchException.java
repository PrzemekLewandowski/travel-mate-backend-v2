package com.travelmate.exception;

public class PhotoStorageFetchException extends RuntimeException {
    public PhotoStorageFetchException(Throwable cause) {
        super(cause);
    }

    public PhotoStorageFetchException(String message) {
        super(message);
    }
}
