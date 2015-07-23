package com.pylypchak.airfast.searcher;

import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.List;

import com.pylypchak.airfast.model.Flight;
import com.pylypchak.airfast.service.FlightService;

public class ByCityFromFlightSearcher extends FlightSearcher {

	public ByCityFromFlightSearcher(Integer id, Timestamp date) {
		super(id, date);
	}

	@Override
	List<Flight> search() throws ReflectiveOperationException, SQLException {
		
		return new FlightService().getFlightsByFromCityIdAndDateFrom(id, date);
	}

}
