package com.travelmate.mapper;

import com.travelmate.model.Country;
import com.travelmate.viewmodel.CountryViewModel;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface CountryMapper {

    CountryViewModel toCountryViewModel(Country country);
}
