package com.travelmate.service;

import com.travelmate.exception.PhotoStorageFetchException;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.web.multipart.MultipartFile;

import java.net.MalformedURLException;
import java.nio.file.Path;
import java.nio.file.Paths;

public interface PhotoStorage {
    String IMAGES = "src/main/resources/images";
    Path photoRootLocation = Paths.get(IMAGES);

    String storePhoto(MultipartFile file, String id);

    default Resource loadPhoto(String filename, Path rootLocation) {
        try {
            Path file = rootLocation.resolve(filename);
            Resource resource = new UrlResource(file.toUri());
            if (resource.exists() || resource.isReadable()) {
                return resource;
            } else {
                throw new PhotoStorageFetchException("Can't fetch photo.");
            }
        } catch (MalformedURLException e) {
            throw new PhotoStorageFetchException(e);
        }
    }
}
