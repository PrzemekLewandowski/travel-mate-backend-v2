package com.travelmate.mapper;

import com.travelmate.model.Country;
import com.travelmate.viewmodel.CountryViewModel;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface CountryMapper {

    CountryMapper INSTANCE = Mappers.getMapper(CountryMapper.class);

    CountryViewModel toCountryViewModel(Country country);
}
