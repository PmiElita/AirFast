package com.pylypchak.airfast.searcher;

import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.List;

import com.pylypchak.airfast.model.Flight;
import com.pylypchak.airfast.service.FlightService;

public class ByCountryToFlightSearcher extends FlightSearcher {

	public ByCountryToFlightSearcher(Integer id, Timestamp date) {
		super(id, date);
		// TODO Auto-generated constructor stub
	}

	@Override
	List<Flight> search() throws ReflectiveOperationException, SQLException {
		// TODO Auto-generated method stub
		return new FlightService().getFlightsByToCountryIdAndDateFrom(id, date);
	}

}
