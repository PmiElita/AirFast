package com.pylypchak.airfast.service;

import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.List;

import com.pylypchak.airfast.dao.FlightDAO;
import com.pylypchak.airfast.model.Flight;

public class FlightService {
	private FlightDAO flightDAO;

	public FlightService() {
		flightDAO = new FlightDAO();
	}

	public void saveFlight(Flight flight) throws SQLException,
			ReflectiveOperationException {
		flight.setFreeSeats(flight.getSeats());
		flightDAO.saveFlight(flight);
	}

	public List<Flight> getFlightsByAdminId(int adminId) throws SQLException,
			ReflectiveOperationException {
		return flightDAO.getFlightsByAdminId(adminId);
	}

	public List<Flight> getFlightsByFromAirportId(int fromAirportId)
			throws SQLException, ReflectiveOperationException {
		return flightDAO.getFlightsByFromAirportId(fromAirportId);
	}

	public List<Flight> getFlightsByToAirportId(int toAirportId)
			throws SQLException, ReflectiveOperationException {
		return flightDAO.getFlightsByToAirportId(toAirportId);
	}

	public List<Flight> getHotFlights() throws ReflectiveOperationException,
			SQLException {
		return flightDAO.getHotFlights();
	}

	public List<Flight> getFlightsByFromAirportIdAndDateFrom(
			Integer fromAirportId, Timestamp fromDate)
			throws ReflectiveOperationException, SQLException {
		return flightDAO.getFlightsByFromAirportIdAndDateFrom(fromAirportId,
				fromDate);
	}

	public List<Flight> getFlightsByToAirportIdAndDateFrom(Integer toAirportId,
			Timestamp fromDate) throws ReflectiveOperationException,
			SQLException {
		return flightDAO.getFlightsByToAirportIdAndDateFrom(toAirportId,
				fromDate);
	}

	public List<Flight> getFlightsByFromCityIdAndDateFrom(Integer fromCityId,
			Timestamp fromDate) throws ReflectiveOperationException,
			SQLException {
		return flightDAO
				.getFlightsByFromCityIdAndDateFrom(fromCityId, fromDate);
	}

	public List<Flight> getFlightsByToCityIdAndDateFrom(Integer toCityId,
			Timestamp fromDate) throws ReflectiveOperationException,
			SQLException {
		return flightDAO.getFlightsByToCityIdAndDateFrom(toCityId, fromDate);
	}

	public List<Flight> getFlightsByFromCountryIdAndDateFrom(
			Integer fromCountryId, Timestamp fromDate)
			throws ReflectiveOperationException, SQLException {
		return flightDAO.getFlightsByFromCountryIdAndDateFrom(fromCountryId,
				fromDate);
	}

	public List<Flight> getFlightsByToCountryIdAndDateFrom(Integer toCountryId,
			Timestamp fromDate) throws ReflectiveOperationException,
			SQLException {
		return flightDAO.getFlightsByToCountryIdAndDateFrom(toCountryId,
				fromDate);
	}
	
    public Flight getFlightById(Integer id) throws SQLException, ReflectiveOperationException{
    	return flightDAO.getFlightById(id);
    }
    
	public void updateFlight(Flight flight) throws ReflectiveOperationException, SQLException{
		flightDAO.updateFlight(flight);
	}
}
