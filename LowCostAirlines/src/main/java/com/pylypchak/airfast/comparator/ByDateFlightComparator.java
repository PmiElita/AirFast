package com.pylypchak.airfast.comparator;

import java.util.Comparator;

import com.pylypchak.airfast.model.Flight;

public class ByDateFlightComparator implements Comparator<Flight> {

	@Override
	public int compare(Flight f1, Flight f2) {
		
		return f1.getDateFrom().compareTo(f2.getDateFrom());
	}

}
