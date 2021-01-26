package com.travelmate.repository;

import com.travelmate.model.Country;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CountryRepository extends JpaRepository<Country, Long> {

    Optional<Country> findByCode(String code);

    Optional<Country> findByName(String countryName);

    Boolean existsByCode(String code);
}
