package com.travelmate.viewmodel;

import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;

@Getter
@Setter
@Builder
@EqualsAndHashCode
public class CountryViewModel {

    @NotBlank
    private String name;

    @NotBlank
    private String code;
}
