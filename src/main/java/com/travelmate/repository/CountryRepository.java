package com.travelmate.repository;

import com.travelmate.model.Country;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CountryRepository extends JpaRepository<Country, Long> {
    Country findByCode(String code);

    Boolean existsByCode(String code);

    Country findByName(String countryName);
}
