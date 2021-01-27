package com.travelmate.exception;

public class PhotoStorageStoreException extends RuntimeException {
    public PhotoStorageStoreException(String message) {
        super(message);
    }

    public PhotoStorageStoreException(Exception cause) {
        super(cause);
    }
}
