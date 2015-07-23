package com.pylypchak.airfast.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import com.pylypchak.airfast.model.City;
import com.pylypchak.airfast.statements.CitySQLStatements;
import com.pylypchak.airfast.util.ConnectionManager;
import com.pylypchak.airfast.util.Transformer;

public class CityDAO {
	public void saveCity(City city) throws SQLException,
			ReflectiveOperationException {
		try (Connection connection = ConnectionManager.getConnection()) {
		PreparedStatement statement = connection
				.prepareStatement(CitySQLStatements.INSERT_CITY_SQL);
		Transformer.valueIntoPreparedStatement(statement, city,
				CitySQLStatements.INSERT_CITY_SQL_ORDER);
		statement.executeUpdate();
		}
	}

	public void updateCity(City city) throws SQLException,
			ReflectiveOperationException {
		
		try (Connection connection = ConnectionManager.getConnection()) {
		PreparedStatement statement = connection
				.prepareStatement(CitySQLStatements.UPDATE_CITY_SQL);
		Transformer.valueIntoPreparedStatement(statement, city,
				CitySQLStatements.UPDATE_CITY_SQL_ORDER);
		statement.executeUpdate();
		}
	}

	public List<City> getCitiesByCountryId(Integer countryId)
			throws SQLException, ReflectiveOperationException {
		try (Connection connection = ConnectionManager.getConnection()) {
		List<City> result = null;
		PreparedStatement statement = connection
				.prepareStatement(
						CitySQLStatements.SELECT_CITIES_BY_COUNTRY_ID_SQL);
		City city = new City();
		city.setCountryId(countryId);
		Transformer.valueIntoPreparedStatement(statement, city,
				CitySQLStatements.SELECT_CITIES_BY_COUNTRY_ID_SQL_ORDER);
		ResultSet resultSet = statement.executeQuery();
		result = Transformer.transformResultSetIntoList(resultSet, City.class);
		return result;
		}
	}

	public City getCityById(Integer id) throws SQLException,
			ReflectiveOperationException {
		try (Connection connection = ConnectionManager.getConnection()) {
		PreparedStatement statement = connection
				.prepareStatement(CitySQLStatements.SELECT_CITY_BY_ID_SQL);
		City city = new City();
		city.setId(id);
		Transformer.valueIntoPreparedStatement(statement, city,
				CitySQLStatements.SELECT_CITY_BY_ID_SQL_ORDER);
		ResultSet resultSet = statement.executeQuery();
		return Transformer.transformResultSetIntoObject(resultSet, City.class);
		}
	}

	public List<City> getAllCities() throws SQLException,
			ReflectiveOperationException {
		try (Connection connection = ConnectionManager.getConnection()) {
		PreparedStatement statement = connection
				.prepareStatement(CitySQLStatements.SELECT_ALL_CITIES_SQL);
		ResultSet resultSet = statement.executeQuery();
		return Transformer.transformResultSetIntoList(resultSet, City.class);
		}
	}
}
