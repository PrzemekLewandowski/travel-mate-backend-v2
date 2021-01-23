package com.travelmate.viewmodel;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.NaturalId;

import javax.validation.constraints.Size;

@Getter
@Setter
public class CountryViewModel {

    @NaturalId
    @Size(max = 2)
    private String name;

    @NaturalId
    @Size(max = 2)
    private String code;
}
