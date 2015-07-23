package com.pylypchak.airfast.service;

import java.sql.SQLException;
import java.util.List;

import com.pylypchak.airfast.dao.CountryDAO;
import com.pylypchak.airfast.model.Country;

public class CountryService {
	private CountryDAO countryDAO;

	public CountryService() {
		countryDAO = new CountryDAO();
	}

	public void saveCountry(Country country) throws SQLException,
			ReflectiveOperationException {
		countryDAO.saveCountry(country);
	}

	public void updateCountry(Country country) throws SQLException,
			ReflectiveOperationException {
		countryDAO.updateCountry(country);
	}

	public List<Country> getAllCountries() throws SQLException,
			ReflectiveOperationException {
		return countryDAO.getAllCountries();
	}

	public Country getCountryById(Integer id) throws SQLException,
			ReflectiveOperationException {
		return countryDAO.getCountryById(id);
	}
}
