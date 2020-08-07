package com.nagarro.dev.portal.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.nagarro.dev.portal.models.Country;

@Repository
public interface CountryRepository extends JpaRepository<Country, Integer> {

}
