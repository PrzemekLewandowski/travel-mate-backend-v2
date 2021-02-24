package com.travelmate.viewmodel;

import lombok.Builder;
import lombok.Data;

import javax.validation.constraints.NotBlank;

@Data
@Builder
public class CountryViewModel {

    @NotBlank
    private String name;

    @NotBlank
    private String code;
}
