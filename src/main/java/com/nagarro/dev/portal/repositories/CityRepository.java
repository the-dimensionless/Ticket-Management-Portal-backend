package com.nagarro.dev.portal.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.nagarro.dev.portal.models.City;

@Repository
public interface CityRepository extends JpaRepository<City, Integer> {
	
	List<City> findByStateId(Integer stateId);

}
