package com.travelmate.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.*;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.Set;

@Builder(toBuilder = true)
@AllArgsConstructor(access = AccessLevel.PACKAGE)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Setter(value = AccessLevel.PACKAGE)
@Getter
@Entity
@Table(name = "posts")
public class Post extends AbstractEntity {

    private String title;

    @JsonFormat(pattern = "dd/MM/yyyy")
    private LocalDate dateFrom;

    @JsonFormat(pattern = "dd/MM/yyyy")
    private LocalDate dateTo;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "post_countries",
            joinColumns = @JoinColumn(name = "post_id"),
            inverseJoinColumns = @JoinColumn(name = "country_id"))
    private Set<Country> countries;

    private int budgetValueFrom;

    private int budgetValueTo;

    private int maxNumberOfParticipants;

    @ManyToMany(mappedBy = "enrolledPosts")
    private Set<User> enrolledParticipants;

    private String infoAboutTravel;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User author;

    private String imageFileName;

    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private Set<Comment> comments;

    private boolean active = true;

    public void setPostToInactive(){
        this.active = false;
    }
}
