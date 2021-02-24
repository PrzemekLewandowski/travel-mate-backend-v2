package com.travelmate.utils;

import org.assertj.core.api.WithAssertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.MockedStatic;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.web.multipart.MultipartFile;

import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mockStatic;
import static org.mockito.Mockito.when;

@SpringBootTest
@ExtendWith({SpringExtension.class})
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class PostPhotoStorageTest implements WithAssertions {

    @Mock
    MultipartFile multipartFile;

    @BeforeAll
    public void init() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void shouldStorePhoto() {
        try (MockedStatic<Files> mocked = mockStatic(Files.class)) {
            // given
            when(multipartFile.getOriginalFilename()).thenReturn("originalfilename.jpg");
            PostPhotoStorage photoStorage = new PostPhotoStorage();
            mocked.when(() -> Files.copy(any(InputStream.class), any(Path.class))).thenReturn(0L);

            // when
            String fileName = photoStorage.storePhoto(multipartFile, "Post Title");

            // then
            assertThat(fileName).isNotNull();
            assertThat(fileName).startsWith("Post Title");
            assertThat(fileName).endsWith(".jpg");
        }
    }
}
