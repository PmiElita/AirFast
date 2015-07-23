package com.pylypchak.airfast.comparator;

import java.util.Comparator;

import com.pylypchak.airfast.model.Country;

public class ByNameCountryComparator implements Comparator<Country> {

	@Override
	public int compare(Country c1, Country c2) {
		
		return c1.getName().compareToIgnoreCase(c2.getName());
	}

}
