package com.travelmate.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.time.LocalDate;
import java.util.Set;

@Data
@NoArgsConstructor
@Entity
@Table(name = "posts")
public class Post extends AbstractEntity {

    @NotBlank
    @Size(min = 3, max = 30)
    private String title;

    @NotNull
    @JsonFormat(pattern = "dd/MM/yyyy")
    private LocalDate travelDateFrom;

    @NotNull
    @JsonFormat(pattern = "dd/MM/yyyy")
    private LocalDate travelDateTo;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "post_countries",
            joinColumns = @JoinColumn(name = "post_id"),
            inverseJoinColumns = @JoinColumn(name = "country_id"))
    private Set<Country> countries;

    @NotBlank
    private int budgetValueFrom;

    @NotBlank
    private int budgetValueTo;

    @NotBlank
    private int maxNumberOfParticipants;

    @ManyToMany(mappedBy = "enrolledPosts")
    private Set<User> enrolledParticipants;

    @NotBlank
    @Size(max = 500)
    private String infoAboutTravel;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User postedByUsername;

    private String advFileName;

    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private Set<Comment> comments;

    private Boolean isActual = true;

}