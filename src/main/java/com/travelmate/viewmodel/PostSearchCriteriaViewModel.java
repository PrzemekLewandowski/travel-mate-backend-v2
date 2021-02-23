package com.travelmate.viewmodel;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.travelmate.model.Country;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@Builder
@EqualsAndHashCode
public class PostSearchCriteriaViewModel {

    private Country country;

    private int budgetValueFrom;

    private int budgetValueTo;

    @JsonFormat(pattern = "dd/MM/yyyy")
    private LocalDate dateFrom;

    @JsonFormat(pattern = "dd/MM/yyyy")
    private LocalDate dateTo;
}
