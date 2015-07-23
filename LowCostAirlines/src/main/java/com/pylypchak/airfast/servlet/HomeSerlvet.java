package com.pylypchak.airfast.servlet;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Collections;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.pylypchak.airfast.comparator.ByNameCityComparator;
import com.pylypchak.airfast.comparator.ByNameAirportsComparator;
import com.pylypchak.airfast.comparator.ByNameCountryComparator;
import com.pylypchak.airfast.model.Airport;
import com.pylypchak.airfast.model.City;
import com.pylypchak.airfast.model.Country;
import com.pylypchak.airfast.model.Flight;
import com.pylypchak.airfast.searcher.Searcher;
import com.pylypchak.airfast.service.AirportService;
import com.pylypchak.airfast.service.CityService;
import com.pylypchak.airfast.service.CountryService;
import com.pylypchak.airfast.service.FlightService;
import com.pylypchak.airfast.util.Messager;

/**
 * Servlet implementation class HomeSerlvet
 */
public class HomeSerlvet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public HomeSerlvet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			String locale = (String) request.getSession().getAttribute("language");
            if (locale == null){
           	 locale = "en";
            }
            request.setAttribute("complete",request.getSession().getAttribute("complete"));
            request.getSession().removeAttribute("complete");
			Country country = new Country();
			country.setName(Messager.getMessage("flight.select.country", locale));

			City city = new City();
			city.setName(Messager.getMessage("flight.select.city", locale));

			Airport airport = new Airport();
			airport.setName(Messager.getMessage("flight.select.airport", locale));

			fillRequestAtributes(request, country, city, airport,"From");
			fillRequestAtributes(request, country, city, airport,"To");

			List<Flight> flights = new FlightService().getHotFlights();
			request.setAttribute("flights", flights);
			request.getRequestDispatcher("pages/home.jsp").forward(request, response);
		} catch (Exception e) {
			e.printStackTrace();
			response.sendError(500);
		}
	
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			Integer airportFromId = null;
			Integer cityFromId = null;
			Integer countryFromId = null;
			Integer airportToId = null;
			Integer cityToId = null;
			Integer countryToId = null;
			Timestamp date = null;
			if (!request.getParameter("airportFrom").equals("")) {
				airportFromId = Integer.parseInt(request
						.getParameter("airportFrom"));

			} else if (!request.getParameter("cityFrom").equals("")) {
				cityFromId = Integer.parseInt(request.getParameter("cityFrom"));
			} else if (!request.getParameter("countryFrom").equals("")) {
				countryFromId = Integer.parseInt(request
						.getParameter("countryFrom"));
			}
			if (!request.getParameter("airportTo").equals("")) {
				airportToId = Integer.parseInt(request
						.getParameter("airportTo"));

			} else if (!request.getParameter("cityTo").equals("")) {
				cityToId = Integer.parseInt(request.getParameter("cityTo"));
			} else if (!request.getParameter("countryTo").equals("")) {
				countryToId = Integer.parseInt(request
						.getParameter("countryTo"));
			}
			if (request.getParameter("date") != null) {
				date = convertIntoTimestamp(request.getParameter("date"));
			}
			List<Flight> flights = Searcher.shearchFlight(airportFromId,
					cityFromId, countryFromId, airportToId, cityToId,
					countryToId, date);
			request.setAttribute("flights", flights);
			request.setAttribute("date", request.getParameter("date"));
			setFromSearchAttributes(request, airportFromId, cityFromId, countryFromId);
			setToSearchAttributes(request, airportToId, cityToId, countryToId);
			request.getRequestDispatcher("pages/home.jsp").forward(request,
					response);

		} catch (Exception e) {
			e.printStackTrace();
			response.sendError(500);
		}
	}

	
	private void setFromSearchAttributes(HttpServletRequest request,
			Integer airportFromId, Integer cityFromId, Integer countryFromId)
			throws ReflectiveOperationException, SQLException, UnsupportedEncodingException {
		String locale = (String) request.getSession().getAttribute("language");
        if (locale == null){
       	 locale = "en";
        }
		Country countryFrom;
		City cityFrom;
		Airport airportFrom;
		if (airportFromId != null) {
			airportFrom = new AirportService().getAirportById(airportFromId);
			cityFrom = new CityService().getCityById(airportFrom.getCityId());
			countryFrom = new CountryService().getCountryById(cityFrom
					.getCountryId());
		} else if (cityFromId != null) {
			airportFrom = new Airport();
			airportFrom.setName(Messager.getMessage("flight.select.airport", locale));
			cityFrom = new CityService().getCityById(cityFromId);
			countryFrom = new CountryService().getCountryById(cityFrom
					.getCountryId());
		} else if (countryFromId != null) {
			airportFrom = new Airport();
			airportFrom.setName(Messager.getMessage("flight.select.airport", locale));
			cityFrom = new City();
			cityFrom.setName(Messager.getMessage("flight.select.city", locale));
			countryFrom = new CountryService().getCountryById(countryFromId);

		} else {
			countryFrom = new Country();
			countryFrom.setName(Messager.getMessage("flight.select.country", locale));

			cityFrom = new City();
			cityFrom.setName(Messager.getMessage("flight.select.city", locale));

			airportFrom = new Airport();
			airportFrom.setName(Messager.getMessage("flight.select.airport", locale));
		}
		
		
		fillRequestAtributes(request, countryFrom, cityFrom,
				airportFrom, "From");

	}

	private void setToSearchAttributes(HttpServletRequest request,
			Integer airportToId, Integer cityToId, Integer countryToId)
			throws ReflectiveOperationException, SQLException, UnsupportedEncodingException {
		String locale = (String) request.getSession().getAttribute("language");
        if (locale == null){
       	 locale = "en";
        }
		Country countryTo;
		City cityTo;
		Airport airportTo;
		if (airportToId != null) {
			airportTo = new AirportService().getAirportById(airportToId);
			cityTo = new CityService().getCityById(airportTo.getCityId());
			countryTo = new CountryService().getCountryById(cityTo
					.getCountryId());
		} else if (cityToId != null) {
			airportTo = new Airport();
			airportTo.setName(Messager.getMessage("flight.select.airport", locale));
			cityTo = new CityService().getCityById(cityToId);
			countryTo = new CountryService().getCountryById(cityTo
					.getCountryId());
		} else if (countryToId != null) {
			airportTo = new Airport();
			airportTo.setName(Messager.getMessage("flight.select.airport", locale));
			cityTo = new City();
			cityTo.setName(Messager.getMessage("flight.select.city", locale));
			countryTo = new CountryService().getCountryById(countryToId);

		} else {
			countryTo = new Country();
			countryTo.setName(Messager.getMessage("flight.select.country", locale));

			cityTo = new City();
			cityTo.setName(Messager.getMessage("flight.select.city", locale));

			airportTo = new Airport();
			airportTo.setName(Messager.getMessage("flight.select.airport", locale));
		}
		
		
		fillRequestAtributes(request, countryTo, cityTo,
				airportTo, "To");

	}
	
	private void fillRequestAtributes(HttpServletRequest request,
			Country selectedCountry, City selectedCity, Airport selectedAirport, String direction)
			throws ReflectiveOperationException, SQLException, UnsupportedEncodingException {
		   String locale = (String) request.getSession().getAttribute("language");
           if (locale == null){
          	 locale = "en";
           }
		CountryService countryService = new CountryService();
		Country country = new Country();
		country.setId(null);
		country.setName(Messager.getMessage("flight.select.country", locale));
		List<Country> countries = countryService.getAllCountries();
		Collections.sort(countries, new ByNameCountryComparator());
		countries.add(0, country);
		request.setAttribute("selectedCountry"+direction, selectedCountry);
		
		request.setAttribute("countries"+direction, countries);

		CityService cityService = new CityService();
		City city = new City();
		city.setId(null);
		city.setName(Messager.getMessage("flight.select.city", locale));
		List<City> cities = cityService.getAllCities();
		Collections.sort(cities, new ByNameCityComparator());
		cities.add(0, city);
		request.setAttribute("selectedCity"+direction, selectedCity);
		request.setAttribute("cities"+direction, cities);

		AirportService airportService = new AirportService();
		Airport airport = new Airport();
		airport.setId(null);
		airport.setName(Messager.getMessage("flight.select.airport", locale));
		List<Airport> airports = airportService.getAllAirports();
		Collections.sort(airports, new ByNameAirportsComparator());

		airports.add(0, airport);
		
		request.setAttribute("selectedAirport"+direction, selectedAirport);
		request.setAttribute("airports"+direction, airports);
	}

	
	
	private Timestamp convertIntoTimestamp(String date) throws ParseException {
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		Timestamp result = new Timestamp(dateFormat.parse(date).getTime());
		return result;
	}
}
