package com.nagarro.dev.portal.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

import com.nagarro.dev.portal.models.City;
import com.nagarro.dev.portal.models.Country;
import com.nagarro.dev.portal.models.State;
import com.nagarro.dev.portal.repositories.CityRepository;
import com.nagarro.dev.portal.repositories.CountryRepository;
import com.nagarro.dev.portal.repositories.StateRepository;

@Service
public class RegionServiceImpl implements RegionService {
	
	@Autowired
	private CountryRepository country;
	
	@Autowired
	private StateRepository state;
	
	@Autowired
	private CityRepository city;
	
	public List<Country> getCountryList() {
		return country.findAll();
	}
	
	public List<State> getStateList(@PathVariable("id") Integer id) {
		return state.findByCountryId(id);
	}
	
	public List<City> getCityList(@PathVariable("id") Integer id) {
		return city.findByStateId(id);
	}

}
