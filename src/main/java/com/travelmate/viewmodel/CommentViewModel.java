package com.travelmate.viewmodel;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDate;

@Data
@Builder
public class CommentViewModel {

    private String username;

    private LocalDate postDate;

    private String commentText;
}
