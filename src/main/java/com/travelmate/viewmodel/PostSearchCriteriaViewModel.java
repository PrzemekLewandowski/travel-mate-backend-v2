package com.travelmate.viewmodel;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.travelmate.model.Country;
import lombok.Data;
import org.springframework.stereotype.Component;

import java.time.LocalDate;

@Component
@Data
public class PostSearchCriteriaViewModel {

    private Country country;

    private int budgetValueFrom;

    private int budgetValueTo;

    @JsonFormat(pattern = "dd/MM/yyyy")
    private LocalDate dateFrom;

    @JsonFormat(pattern = "dd/MM/yyyy")
    private LocalDate dateTo;
}
