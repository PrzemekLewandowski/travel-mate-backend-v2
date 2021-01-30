package com.travelmate.utils.exception;

public class PhotoStorageStoreException extends RuntimeException {
    public PhotoStorageStoreException(String message) {
        super(message);
    }

    public PhotoStorageStoreException(Exception cause) {
        super(cause);
    }
}
