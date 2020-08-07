package com.nagarro.dev.portal.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.nagarro.dev.portal.models.State;

@Repository
public interface StateRepository  extends JpaRepository<State, Integer> {
	
	List<State> findByCountryId(Integer countryId);

}
