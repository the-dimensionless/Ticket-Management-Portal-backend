package com.nagarro.dev.portal.services;

import java.util.List;

import org.springframework.web.bind.annotation.PathVariable;

import com.nagarro.dev.portal.models.City;
import com.nagarro.dev.portal.models.Country;
import com.nagarro.dev.portal.models.State;

public interface RegionService {
	
	public List<Country> getCountryList();
	
	public List<State> getStateList(@PathVariable("id") Integer id);
	
	public List<City> getCityList(@PathVariable("id") Integer id);

}
