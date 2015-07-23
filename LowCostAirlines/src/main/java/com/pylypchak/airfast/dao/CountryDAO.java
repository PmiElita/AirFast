package com.pylypchak.airfast.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import com.pylypchak.airfast.model.Country;
import com.pylypchak.airfast.statements.CountrySQLStatements;
import com.pylypchak.airfast.util.ConnectionManager;
import com.pylypchak.airfast.util.Transformer;

public class CountryDAO {

	public void saveCountry(Country country) throws SQLException, ReflectiveOperationException{
		try (Connection connection = ConnectionManager.getConnection()) {
		PreparedStatement statement = connection
				.prepareStatement(CountrySQLStatements.INSERT_COUNTRY_SQL);
		Transformer.valueIntoPreparedStatement(statement, country, CountrySQLStatements.INSERT_COUNTRY_SQL_ORDER);
		statement.executeUpdate();
		}
	}
	
	public void updateCountry(Country country) throws SQLException, ReflectiveOperationException{
		try (Connection connection = ConnectionManager.getConnection()) {
		PreparedStatement statement = connection
				.prepareStatement(CountrySQLStatements.UPDATE_COUNTRY_BYID_SQL);
		Transformer.valueIntoPreparedStatement(statement, country, CountrySQLStatements.UPDATE_COUNTRY_BYID_SQL_ORDER);
		statement.executeUpdate();
		}
	}
	
	public List<Country> getAllCountries() throws SQLException, ReflectiveOperationException{
		try (Connection connection = ConnectionManager.getConnection()) {
		List<Country> result= null;
		PreparedStatement statement = connection
				.prepareStatement(CountrySQLStatements.SELECT_ALL_COUTRIES_SQL);
		ResultSet resultSet = statement.executeQuery();
		result=Transformer.transformResultSetIntoList(resultSet, Country.class);
		return result;
		}
	}
	
	public Country getCountryById(Integer id) throws SQLException,
			ReflectiveOperationException {
		try (Connection connection = ConnectionManager.getConnection()) {
		PreparedStatement statement = connection
				.prepareStatement(
						CountrySQLStatements.SELECT_COUNTRY_BY_ID_SQL);
		Country country = new Country();
		country.setId(id);
		Transformer.valueIntoPreparedStatement(statement, country,
				CountrySQLStatements.SELECT_COUNTRY_BY_ID_SQL_ORDER);
		ResultSet resultSet = statement.executeQuery();
		return Transformer.transformResultSetIntoObject(resultSet, Country.class);
		}
	}
}
