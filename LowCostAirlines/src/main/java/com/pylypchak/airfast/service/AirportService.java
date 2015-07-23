package com.pylypchak.airfast.service;

import java.sql.SQLException;
import java.util.List;

import com.pylypchak.airfast.dao.AirportDAO;
import com.pylypchak.airfast.model.Airport;

public class AirportService {
	private AirportDAO airportDAO;

	public AirportService() {
		airportDAO = new AirportDAO();
	}

	public void saveAirport(Airport airport) throws SQLException,
			ReflectiveOperationException {
		airportDAO.saveAirport(airport);
	}
	
	public void updateAirport(Airport airport) throws SQLException,
	ReflectiveOperationException {
		airportDAO.updateAirport(airport);
	}

	public List<Airport> getAirportsByCityId(int cityId) throws SQLException,
			ReflectiveOperationException {
		return airportDAO.getAirportsByCityId(cityId);
	}

	public Airport getAirportByCountryCityAirportNames(String countryName,
			String cityName, String airportName) throws SQLException,
			ReflectiveOperationException {
		return airportDAO.getAirportByCountryCityAirportNames(countryName,
				cityName, airportName);
	}

	public Airport getAirportById(Integer id) throws SQLException,
			ReflectiveOperationException {
		return airportDAO.getAirportById(id);
	}

	public String getCountryCityAirportNamesByAirportId(Integer id)
			throws SQLException, ReflectiveOperationException {
		return airportDAO.getCountryCityAirportNamesByAirportId(id);
	}

	public List<Airport> getAllAirports() throws SQLException,
			ReflectiveOperationException {
		return airportDAO.getAllAirports();
	}
}
