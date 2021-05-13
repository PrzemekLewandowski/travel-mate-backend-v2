package com.travelmate.service.command;

import com.travelmate.mapper.PostMapper;
import com.travelmate.model.Post;
import com.travelmate.repository.PostCommandRepository;
import com.travelmate.utils.PostPhotoStorage;
import com.travelmate.viewmodel.PostViewModel;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDate;

@Service
@RequiredArgsConstructor
@Transactional
public class PostCommandService {

    private final PostCommandRepository postCommandRepository;
    private final PostPhotoStorage postPhotoStorage;
    private final PostMapper postMapper;

    public ResponseEntity<PostViewModel> add(PostViewModel postViewModel, MultipartFile image) {
        validateRequestData(postViewModel);
        String storedFileName = postPhotoStorage.storePhoto(image, postViewModel.getTitle());
        postViewModel.setImageFileName(storedFileName);
        Post post = postMapper.toPost(postViewModel);
        Post savedPost = postCommandRepository.save(post);
        return new ResponseEntity<>(postMapper.toPostViewModel(savedPost), HttpStatus.ACCEPTED);
    }

    private void validateRequestData(PostViewModel postViewModel) {
        if (postViewModel.getDateTo().isBefore(postViewModel.getDateFrom())) {
            throw new IllegalArgumentException("'Data od' musi być po 'Data do'.");
        }

        if (postViewModel.getDateFrom().isBefore(LocalDate.now())) {
            throw new IllegalArgumentException("'Data od' nie może być przeszła.");
        }

        if (postViewModel.getCountries().isEmpty()) {
            throw new IllegalArgumentException("Musisz podać przynajmniej jeden kraj.");
        }
    }
}
