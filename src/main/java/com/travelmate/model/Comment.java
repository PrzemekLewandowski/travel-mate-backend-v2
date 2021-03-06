package com.travelmate.model;

import lombok.*;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import java.time.LocalDate;

@Builder(toBuilder = true)
@AllArgsConstructor(access = AccessLevel.PACKAGE)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Setter(value = AccessLevel.PACKAGE)
@Getter
@Entity
@Table(name = "comments")
public class Comment extends AbstractEntity {

    private String username;

    private LocalDate postDate;

    private String commentText;

    @ManyToOne
    @JoinColumn(name = "post_id", nullable = false)
    private Post post;
}
