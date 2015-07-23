package com.pylypchak.airfast.service;

import java.sql.SQLException;
import java.util.List;

import com.pylypchak.airfast.dao.CityDAO;
import com.pylypchak.airfast.model.City;

public class CityService {
	private CityDAO cityDAO;

	public CityService(){
		cityDAO = new CityDAO();
	}
	
	public void saveCity(City city) throws SQLException,
			ReflectiveOperationException {
		cityDAO.saveCity(city);
	}
	
	public void updateCity(City city) throws SQLException,
	ReflectiveOperationException {
		cityDAO.updateCity(city);
	}
	
	public List<City> getCitiesByCountryId(int countryId) throws SQLException,
			ReflectiveOperationException {
		return cityDAO.getCitiesByCountryId(countryId);
	}
	
	public City getCityById(Integer id) throws SQLException, ReflectiveOperationException{
		return cityDAO.getCityById(id);
	}
	
	public List<City> getAllCities() throws SQLException, ReflectiveOperationException{
		return cityDAO.getAllCities();
	}
}
