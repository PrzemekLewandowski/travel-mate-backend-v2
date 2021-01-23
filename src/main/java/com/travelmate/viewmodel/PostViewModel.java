package com.travelmate.viewmodel;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.travelmate.model.Comment;
import com.travelmate.model.Country;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Id;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.time.LocalDate;
import java.util.Set;

@Getter
@Setter
public class PostViewModel {
    @Id
    private Long id;

    @NotBlank
    @Size(min = 3, max = 50)
    private String title;

    @NotBlank
    @JsonFormat(pattern = "dd/MM/yyyy")
    private LocalDate dateFrom;

    @NotBlank
    @JsonFormat(pattern = "dd/MM/yyyy")
    private LocalDate dateTo;

    private Set<Country> countryList;

    private Set<UserViewModel> enrolledParticipants;

    @NotNull
    private int maxNumberOfParticipants;

    @NotNull
    private int budgetValueFrom;

    @NotNull
    private int budgetValueTo;

    @NotBlank
    @Size(max = 500)
    private String infoAboutTravel;

    private UserViewModel postedByUsername;

    private String username;

    private String fileName;

    private Set<Comment> comments;

    private Boolean isActual;
}
