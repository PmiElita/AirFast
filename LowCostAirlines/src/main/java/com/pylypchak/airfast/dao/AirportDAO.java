package com.pylypchak.airfast.dao;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import com.pylypchak.airfast.model.Airport;
import com.pylypchak.airfast.model.City;
import com.pylypchak.airfast.model.Country;
import com.pylypchak.airfast.service.AirportService;
import com.pylypchak.airfast.service.CityService;
import com.pylypchak.airfast.service.CountryService;
import com.pylypchak.airfast.statements.AirportSQLStatements;
import com.pylypchak.airfast.util.ConnectionManager;
import com.pylypchak.airfast.util.Transformer;

public class AirportDAO {
	public void saveAirport(Airport airport) throws SQLException,
			ReflectiveOperationException {
		try (Connection connection = ConnectionManager.getConnection()) {
			PreparedStatement statement = connection
					.prepareStatement(AirportSQLStatements.INSERT_AIRPORT_SQL);
			Transformer.valueIntoPreparedStatement(statement, airport,
					AirportSQLStatements.INSERT_AIRPORT_SQL_ORDER);
			statement.executeUpdate();
		}
	}

	public void updateAirport(Airport airport) throws SQLException,
			ReflectiveOperationException {
		try (Connection connection = ConnectionManager.getConnection()) {
		PreparedStatement statement = connection
				.prepareStatement(AirportSQLStatements.UPDATE_AIRPORT_SQL);
		Transformer.valueIntoPreparedStatement(statement, airport,
				AirportSQLStatements.UPDATE_AIRPORT_SQL_ORDER);
		statement.executeUpdate();
		}
	}

	public List<Airport> getAirportsByCityId(Integer cityId)
			throws SQLException, ReflectiveOperationException {
		try (Connection connection = ConnectionManager.getConnection()) {
		List<Airport> result = null;
		PreparedStatement statement = connection
				.prepareStatement(
						AirportSQLStatements.SELECT_AIRPORTS_BY_CITY_ID_SQL);
		Airport airport = new Airport();
		airport.setCityId(cityId);
		Transformer.valueIntoPreparedStatement(statement, airport,
				AirportSQLStatements.SELECT_AIRPORTS_BY_CITY_ID_SQL_ORDER);
		ResultSet resultSet = statement.executeQuery();
		result = Transformer.transformResultSetIntoList(resultSet,
				Airport.class);
		return result;
		}
	}

	public Airport getAirportByCountryCityAirportNames(String countryName,
			String cityName, String airportName) throws SQLException,
			ReflectiveOperationException {
		try (Connection connection = ConnectionManager.getConnection()) {
		CallableStatement statement = connection
				.prepareCall(
						AirportSQLStatements.CALL_SELECT_AIRPORT_BY_NAMES_SQL);
		statement.setString(1, countryName);
		statement.setString(2, cityName);
		statement.setString(3, airportName);
		ResultSet resultSet = statement.executeQuery();
		return Transformer.transformResultSetIntoObject(resultSet,
				Airport.class);
		}
		}

	public Airport getAirportById(Integer id) throws SQLException,
			ReflectiveOperationException {
		try (Connection connection = ConnectionManager.getConnection()) {
		PreparedStatement statement = ConnectionManager
				.getConnection()
				.prepareStatement(AirportSQLStatements.SELECT_AIRPORT_BY_ID_SQL);
		Airport airport = new Airport();
		airport.setId(id);
		Transformer.valueIntoPreparedStatement(statement, airport,
				AirportSQLStatements.SELECT_AIRPORT_BY_ID_SQL_ORDER);
		ResultSet resultSet = statement.executeQuery();
		return Transformer.transformResultSetIntoObject(resultSet,
				Airport.class);
		}
	}

	public String getCountryCityAirportNamesByAirportId(Integer id)
			throws SQLException, ReflectiveOperationException {
	
		Airport airport = new AirportService().getAirportById(id);
		City city = new CityService().getCityById(airport.getCityId());
		Country country = new CountryService().getCountryById(city
				.getCountryId());
		return country.getName() + " " + city.getName() + " "
				+ airport.getName();
		
	}

	public List<Airport> getAllAirports() throws SQLException,
			ReflectiveOperationException {
		try (Connection connection = ConnectionManager.getConnection()) {
		PreparedStatement statement = connection
				.prepareStatement(AirportSQLStatements.SELECT_ALL_AIRPORTS_SQL);
		ResultSet resultSet = statement.executeQuery();
		return Transformer.transformResultSetIntoList(resultSet, Airport.class);
		}
	}
}
