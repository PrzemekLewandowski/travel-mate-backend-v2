package com.travelmate.viewmodel;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.time.LocalDate;

@Getter
@Setter
public class PostCardViewModel {

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

    private int budgetValueFrom;

    private int budgetValueTo;

    @NotBlank
    @Size(max = 500)
    private String infoAboutTravel;

    private String fileName;
}
