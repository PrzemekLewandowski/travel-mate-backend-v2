package com.travelmate.service;

import com.travelmate.exception.PhotoStorageStoreException;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.time.LocalDateTime;
import java.util.Optional;

public class PostPhotoStorage implements PhotoStorage {
    private static final String POST_IMAGE_PATH = "post";

    private final Path postImageRootLocation = photoRootLocation.resolve(POST_IMAGE_PATH);

    public Path getPostImageRootLocation() {
        return postImageRootLocation;
    }

    @Override
    public String storePhoto(MultipartFile file, String photoTitle) {
        String localDateTime = LocalDateTime.now().toString();
        String originalFileName = Optional.ofNullable(file.getOriginalFilename())
                .orElseThrow(() -> new PhotoStorageStoreException("Can't read original file name."));
        String fileExtension = originalFileName.split("\\.")[1];
        String storedFileName = photoTitle + localDateTime + "." + fileExtension;
        try {
            Files.copy(file.getInputStream(), this.postImageRootLocation.resolve(storedFileName));
            return storedFileName;
        } catch (IOException e) {
            throw new PhotoStorageStoreException(e);
        }
    }

}
