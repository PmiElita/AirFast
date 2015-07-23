package com.pylypchak.airfast.comparator;

import java.util.Comparator;

import com.pylypchak.airfast.model.Airport;
import com.pylypchak.airfast.model.City;
import com.pylypchak.airfast.model.Country;
import com.pylypchak.airfast.service.CityService;
import com.pylypchak.airfast.service.CountryService;

public class ByNameAirportsComparator implements Comparator<Airport> {

	@Override
	public int compare(Airport a1, Airport a2) {
	return a1.getName().compareToIgnoreCase(a2.getName());
	}

}
