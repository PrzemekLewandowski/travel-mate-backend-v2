package com.travelmate.viewmodel;

import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@Builder
@EqualsAndHashCode
public class CommentViewModel {

    private String username;

    private LocalDate postDate;

    private String commentText;
}
