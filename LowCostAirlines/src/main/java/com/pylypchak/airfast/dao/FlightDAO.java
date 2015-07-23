package com.pylypchak.airfast.dao;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.List;

import com.pylypchak.airfast.model.Flight;
import com.pylypchak.airfast.statements.FlightSQLStatements;
import com.pylypchak.airfast.util.ConnectionManager;
import com.pylypchak.airfast.util.Transformer;

public class FlightDAO {
	public void saveFlight(Flight flight) throws SQLException,
			ReflectiveOperationException {
		try (Connection connection = ConnectionManager.getConnection()) {
		PreparedStatement statement = connection
				.prepareStatement(FlightSQLStatements.INSERT_FLIGHT_SQL);
		Transformer.valueIntoPreparedStatement(statement, flight,
				FlightSQLStatements.INSERT_FLIGHT_SQL_ORDER);
		statement.executeUpdate();
		}
	}

	public void updateFlight(Flight flight) throws ReflectiveOperationException, SQLException{
		try (Connection connection = ConnectionManager.getConnection()) {
	
		PreparedStatement statement = connection
				.prepareStatement(FlightSQLStatements.UPDATE_FLIGHT_BY_ID_SQL);
		Transformer.valueIntoPreparedStatement(statement, flight,
				FlightSQLStatements.UPDATE_FLIGHT_BY_ID_SQL_ORDER);
		statement.executeUpdate();
		}
	}
	
	public List<Flight> getFlightsByAdminId(int adminId) throws SQLException,
			ReflectiveOperationException {
		try (Connection connection = ConnectionManager.getConnection()) {
		List<Flight> result = null;
		PreparedStatement statement = connection
				.prepareStatement(
						FlightSQLStatements.SELECT_FLIGHTS_BY_ADMIN_ID_SQL);
		Flight flight = new Flight();
		flight.setAdminId(adminId);
		Transformer.valueIntoPreparedStatement(statement, flight,
				FlightSQLStatements.SELECT_FLIGHTS_BY_ADMIN_ID_SQL_ORDER);
		ResultSet resultSet = statement.executeQuery();
		result = Transformer
				.transformResultSetIntoList(resultSet, Flight.class);
		return result;
		}
	}

	public List<Flight> getFlightsByFromAirportId(int fromAirportId)
			throws SQLException, ReflectiveOperationException {
		try (Connection connection = ConnectionManager.getConnection()) {
		List<Flight> result = null;
		PreparedStatement statement = connection
				.prepareStatement(
						FlightSQLStatements.SELECT_FLIGHTS_BY_FROM_AIRPORT_ID_SQL);
		Flight flight = new Flight();
		flight.setFromAirportId(fromAirportId);
		Transformer
				.valueIntoPreparedStatement(
						statement,
						flight,
						FlightSQLStatements.SELECT_FLIGHTS_BY_FROM_AIRPORT_ID_SQL_ORDER);
		ResultSet resultSet = statement.executeQuery();
		result = Transformer
				.transformResultSetIntoList(resultSet, Flight.class);
		return result;
		}
	}

	public List<Flight> getFlightsByToAirportId(int toAirportId)
			throws SQLException, ReflectiveOperationException {
		try (Connection connection = ConnectionManager.getConnection()) {
		List<Flight> result = null;
		PreparedStatement statement = connection
				.prepareStatement(
						FlightSQLStatements.SELECT_FLIGHTS_BY_TO_AIRPORT_ID_SQL);
		Flight flight = new Flight();
		flight.setToAirportId(toAirportId);
		Transformer.valueIntoPreparedStatement(statement, flight,
				FlightSQLStatements.SELECT_FLIGHTS_BY_TO_AIRPORT_ID_SQL_ORDER);
		ResultSet resultSet = statement.executeQuery();
		result = Transformer
				.transformResultSetIntoList(resultSet, Flight.class);
		return result;
		}
	}

	public List<Flight> getHotFlights() throws ReflectiveOperationException,
			SQLException {
		try (Connection connection = ConnectionManager.getConnection()) {
		List<Flight> result = null;
		PreparedStatement statement = connection
				.prepareStatement(FlightSQLStatements.SELECT_HOT_FLIGHTS_SQL);
		Flight flight = new Flight();
		flight.setIsHot(true);
		Transformer.valueIntoPreparedStatement(statement, flight,
				FlightSQLStatements.SELECT_HOT_FLIGHTS_SQL_ORDER);
		ResultSet resultSet = statement.executeQuery();
		result = Transformer
				.transformResultSetIntoList(resultSet, Flight.class);
		return result;
		}
	}

	public List<Flight> getFlightsByFromAirportIdAndDateFrom(
			Integer fromAirportId, Timestamp fromDate)
			throws ReflectiveOperationException, SQLException {
		try (Connection connection = ConnectionManager.getConnection()) {
		CallableStatement statement = connection
				.prepareCall(
						FlightSQLStatements.CALL_GET_FLIGHTS_BY_FROM_AIRPORT_ID_DATE_FROM_SQL);
		Flight flight = new Flight();
		flight.setDateFrom(fromDate);
		flight.setFromAirportId(fromAirportId);
		Transformer
				.valueIntoPreparedStatement(
						statement,
						flight,
						FlightSQLStatements.CALL_GET_FLIGHTS_BY_FROM_AIRPORT_ID_DATE_FROM_SQL_ORDER);
		ResultSet resultSet = statement.executeQuery();
		return Transformer.transformResultSetIntoList(resultSet, Flight.class);
		}
	}

	public List<Flight> getFlightsByToAirportIdAndDateFrom(Integer toAirportId,
			Timestamp fromDate) throws ReflectiveOperationException,
			SQLException {
		try (Connection connection = ConnectionManager.getConnection()) {
		CallableStatement statement = connection
				.prepareCall(
						FlightSQLStatements.CALL_GET_FLIGHTS_BY_TO_AIRPORT_ID_DATE_FROM_SQL);
		Flight flight = new Flight();
		flight.setDateFrom(fromDate);
		flight.setToAirportId(toAirportId);
		Transformer
				.valueIntoPreparedStatement(
						statement,
						flight,
						FlightSQLStatements.CALL_GET_FLIGHTS_BY_TO_AIRPORT_ID_DATE_FROM_SQL_ORDER);
		ResultSet resultSet = statement.executeQuery();
		return Transformer.transformResultSetIntoList(resultSet, Flight.class);
		}
	}

	public List<Flight> getFlightsByFromCityIdAndDateFrom(Integer fromCityId,
			Timestamp fromDate) throws ReflectiveOperationException,
			SQLException {
		try (Connection connection = ConnectionManager.getConnection()) {
		CallableStatement statement = connection
				.prepareCall(
						FlightSQLStatements.CALL_GET_FLIGHTS_BY_FROM_CITY_ID_DATE_FROM_SQL);
		statement.setInt(1, fromCityId);
		statement.setTimestamp(2, fromDate);
		ResultSet resultSet = statement.executeQuery();
		return Transformer.transformResultSetIntoList(resultSet, Flight.class);
		}
	}

	public List<Flight> getFlightsByToCityIdAndDateFrom(Integer toCityId,
			Timestamp fromDate) throws ReflectiveOperationException,
			SQLException {
		try (Connection connection = ConnectionManager.getConnection()) {
		CallableStatement statement = connection
				.prepareCall(
						FlightSQLStatements.CALL_GET_FLIGHTS_BY_TO_CITY_ID_DATE_FROM_SQL);
		statement.setInt(1, toCityId);
		statement.setTimestamp(2, fromDate);
		ResultSet resultSet = statement.executeQuery();
		return Transformer.transformResultSetIntoList(resultSet, Flight.class);
		}
	}

	public List<Flight> getFlightsByFromCountryIdAndDateFrom(
			Integer fromCountryId, Timestamp fromDate)
			throws ReflectiveOperationException, SQLException {
		try (Connection connection = ConnectionManager.getConnection()) {
		CallableStatement statement =connection
				.prepareCall(
						FlightSQLStatements.CALL_GET_FLIGHTS_BY_FROM_COUNTRY_ID_DATE_FROM_SQL);
		statement.setInt(1, fromCountryId);
		statement.setTimestamp(2, fromDate);
		ResultSet resultSet = statement.executeQuery();
		return Transformer.transformResultSetIntoList(resultSet, Flight.class);
		}
	}

	public List<Flight> getFlightsByToCountryIdAndDateFrom(Integer toCountryId,
			Timestamp fromDate) throws ReflectiveOperationException,
			SQLException {
		try (Connection connection = ConnectionManager.getConnection()) {
		CallableStatement statement = connection
				.prepareCall(
						FlightSQLStatements.CALL_GET_FLIGHTS_BY_TO_COUNTRY_ID_DATE_FROM_SQL);
		statement.setInt(1, toCountryId);
		statement.setTimestamp(2, fromDate);
		ResultSet resultSet = statement.executeQuery();
		return Transformer.transformResultSetIntoList(resultSet, Flight.class);
		}
	}
	
    public Flight getFlightById(Integer id) throws SQLException, ReflectiveOperationException{
    	try (Connection connection = ConnectionManager.getConnection()) {
    	PreparedStatement statement= connection.prepareStatement(FlightSQLStatements.SELECT_FLIGHT_BY_ID);
    	Flight flight = new Flight();
    	flight.setId(id);
    	Transformer.valueIntoPreparedStatement(statement, flight, FlightSQLStatements.SELECT_FLIGHT_BY_ID_ORDER);
        ResultSet resultSet=statement.executeQuery();
        return Transformer.transformResultSetIntoObject(resultSet, Flight.class);
    	}
    }
}
