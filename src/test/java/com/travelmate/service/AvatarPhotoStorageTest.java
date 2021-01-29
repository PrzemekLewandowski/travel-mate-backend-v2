package com.travelmate.service;

import com.travelmate.model.User;
import com.travelmate.repository.UserRepository;
import org.assertj.core.api.WithAssertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestInstance.Lifecycle;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.web.multipart.MultipartFile;

import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;


@SpringBootTest
@ExtendWith({SpringExtension.class})
@TestInstance(Lifecycle.PER_CLASS)
class AvatarPhotoStorageTest implements WithAssertions {
    @Mock
    private User user;

    @Mock
    UserRepository userRepository;

    @Mock
    MultipartFile multipartFile;

    @BeforeAll
    public void init() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void shouldStorePhoto() throws Exception {
        // given
        when(multipartFile.getOriginalFilename()).thenReturn("originalfilename.jpg");
        when(userRepository.findByUsername("username")).thenReturn(Optional.of(user));
        when(user.getAvatarFileName()).thenReturn("default-avatar.png");
        AvatarPhotoStorage photoStorage = new AvatarPhotoStorage(userRepository);
        AvatarPhotoStorage spyPhotoStorage = Mockito.spy(photoStorage);
        Mockito.doNothing().when(spyPhotoStorage).storeAvatar(any(MultipartFile.class), anyString(), anyString());

        // when
        String fileName = spyPhotoStorage.storePhoto(multipartFile, "username");

        // then
        assertThat(fileName).isNotNull();
        assertThat(fileName).isEqualTo("username.jpg");


    }
}
