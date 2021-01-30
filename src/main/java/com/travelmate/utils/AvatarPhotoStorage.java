package com.travelmate.utils;

import com.travelmate.exception.PhotoStorageStoreException;
import com.travelmate.model.User;
import com.travelmate.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Optional;

@RequiredArgsConstructor
public class AvatarPhotoStorage implements PhotoStorage {
    private static final String DEFAULT_AVATAR = "default-avatar.png";
    private static final String AVATAR_PATH = "avatar";
    private final Path avatarRootLocation = photoRootLocation.resolve(AVATAR_PATH);
    private final UserRepository userRepository;

    public Path getAvatarRootLocation() {
        return avatarRootLocation;
    }

    @Override
    public String storePhoto(MultipartFile file, String name) {
        String originalFileName = Optional.ofNullable(file.getOriginalFilename())
                .orElseThrow(() -> new PhotoStorageStoreException("Can't read original file name."));
        String fileExtension = originalFileName.split("\\.")[1];
        User user = userRepository.findByUsername(name)
                .orElseThrow(() -> new UsernameNotFoundException(String.format("Can't find user with username: %s", name)));
        String newFileName = name + "." + fileExtension;
        String oldFileName = user.getAvatarFileName();
        try {
            storeAvatar(file, newFileName, oldFileName);
            return newFileName;
        } catch (Exception e) {
            throw new PhotoStorageStoreException(e);
        }
    }

    protected void storeAvatar(MultipartFile file, String newFileName, String oldFileName) throws IOException {
        if (!oldFileName.equals(DEFAULT_AVATAR)) {
            Files.delete(this.avatarRootLocation.resolve(oldFileName));
        }
        Files.copy(file.getInputStream(), this.avatarRootLocation.resolve(newFileName));
    }
}

