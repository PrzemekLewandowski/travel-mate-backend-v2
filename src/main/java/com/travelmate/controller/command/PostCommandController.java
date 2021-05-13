package com.travelmate.controller.command;

import com.travelmate.service.command.PostCommandService;
import com.travelmate.viewmodel.PostViewModel;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/api/post")
public class PostCommandController {

    private final PostCommandService postCommandService;

    @PostMapping("/add")
    public ResponseEntity<PostViewModel> add(@RequestPart(value = "post") PostViewModel postViewModel, @RequestPart(value = "file") final MultipartFile image) {
        return postCommandService.add(postViewModel, image);
    }
}
