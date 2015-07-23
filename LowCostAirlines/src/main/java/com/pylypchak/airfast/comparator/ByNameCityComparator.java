package com.pylypchak.airfast.comparator;

import java.util.Comparator;

import com.pylypchak.airfast.model.City;
import com.pylypchak.airfast.model.Country;
import com.pylypchak.airfast.service.CountryService;

public class ByNameCityComparator implements Comparator<City>{

	@Override
	public int compare(City c1, City c2) {

         return c1.getName().compareToIgnoreCase(c2.getName());
	}

}
