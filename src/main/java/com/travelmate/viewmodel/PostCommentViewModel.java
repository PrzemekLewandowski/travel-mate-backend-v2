package com.travelmate.viewmodel;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
public class PostCommentViewModel {
    private String username;
    private LocalDate postDate;
    private String commentText;
}
