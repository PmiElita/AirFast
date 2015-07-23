package com.pylypchak.airfast.searcher;

import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.List;

import com.pylypchak.airfast.model.Flight;

public abstract class FlightSearcher {
	protected Integer id;

	protected Timestamp date;

	protected FlightSearcher(Integer id, Timestamp date) {
		this.id = id;
		this.date = date;
	}

	abstract List<Flight> search() throws ReflectiveOperationException, SQLException;
}
