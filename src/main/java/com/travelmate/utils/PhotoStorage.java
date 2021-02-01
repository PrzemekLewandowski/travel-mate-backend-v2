package com.travelmate.utils;

import com.travelmate.utils.exception.PhotoStorageFetchException;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.web.multipart.MultipartFile;

import java.net.MalformedURLException;
import java.nio.file.Path;
import java.nio.file.Paths;

public interface PhotoStorage {
    String IMAGES_PATH = "images/";
    Path photoRootLocation = Paths.get(IMAGES_PATH);

    String storePhoto(MultipartFile file, String id);

    default Resource loadPhoto(String filename, Path rootLocation) throws PhotoStorageFetchException {
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
