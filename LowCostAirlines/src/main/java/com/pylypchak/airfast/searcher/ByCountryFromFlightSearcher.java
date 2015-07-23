package com.pylypchak.airfast.searcher;

import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.List;

import com.pylypchak.airfast.model.Flight;
import com.pylypchak.airfast.service.FlightService;

public class ByCountryFromFlightSearcher extends FlightSearcher {

	public ByCountryFromFlightSearcher(Integer id, Timestamp date) {
		super(id, date);
		// TODO Auto-generated constructor stub
	}

	@Override
	List<Flight> search() throws ReflectiveOperationException, SQLException {
		return new FlightService().getFlightsByFromCountryIdAndDateFrom(id, date);
	}

}
