package com.travelmate.model;

import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import java.time.LocalDate;

@Data
@Entity
@Table(name = "comments")
public class Comment extends AbstractEntity {
    @NotBlank
    private String username;
    @NotBlank
    private LocalDate postDate;
    @NotBlank
    private String commentText;

    @ManyToOne
    @JoinColumn(name = "post_id", nullable = false)
    private Post post;
}
