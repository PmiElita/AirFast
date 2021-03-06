package com.pylypchak.airfast.servlet;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.pylypchak.airfast.comparator.ByNameAirportsComparator;
import com.pylypchak.airfast.model.Airport;
import com.pylypchak.airfast.model.City;
import com.pylypchak.airfast.service.AirportService;
import com.pylypchak.airfast.service.CityService;

/**
 * Servlet implementation class ReloadAirportsServlet
 */
public class ReloadAirportsServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public ReloadAirportsServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		try {
			List<Map<String, String>> list = new ArrayList<Map<String, String>>();
			AirportService airportService = new AirportService();
			CityService cityService = new CityService();
			List<Airport> airports;
			if (request.getParameter("selectedCity").equals("")) {
				if (request.getParameter("selectedCountry").equals("")) {
					airports = airportService.getAllAirports();
				} else {
					List<City> cities = cityService
							.getCitiesByCountryId(Integer.parseInt(request
									.getParameter("selectedCountry")));

					airports = new ArrayList<Airport>();
					for (City c : cities) {
						airports.addAll(airportService.getAirportsByCityId(c
								.getId()));
					}
				}

			} else {
				airports = airportService.getAirportsByCityId(Integer
						.parseInt(request.getParameter("selectedCity")));
			}
			for (Airport airport : airports) {
				Map<String, String> map = new HashMap<String, String>();
				map.put("value", airport.getId().toString());
				map.put("text", airport.getName());
				list.add(map);
			}
			Collections.sort(airports, new ByNameAirportsComparator());
			response.setContentType("application/json");
			response.setCharacterEncoding("UTF-8");
			response.getWriter().write(new Gson().toJson(list));
		} catch (Exception e) {
			e.printStackTrace();
			response.sendError(500);
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		try {
			List<Map<String, String>> list = new ArrayList<Map<String, String>>();
			AirportService airportService = new AirportService();
			CityService cityService = new CityService();
			List<City> cities;
			if (request.getParameter("selected").equals("")) {
				cities = cityService.getAllCities();
			} else {
				cities = cityService.getCitiesByCountryId(Integer
						.parseInt(request.getParameter("selected")));
			}
			List<Airport> airports = new ArrayList<Airport>();
			for (City c : cities) {
				airports.addAll(airportService.getAirportsByCityId(c.getId()));
			}
			Collections.sort(airports, new ByNameAirportsComparator());

			for (Airport airport : airports) {
				Map<String, String> map = new HashMap<String, String>();
				map.put("value", airport.getId().toString());
				map.put("text", airport.getName());
				list.add(map);
			}

			response.setContentType("application/json");
			response.setCharacterEncoding("UTF-8");
			response.getWriter().write(new Gson().toJson(list));
		} catch (Exception e) {
			e.printStackTrace();
			response.sendError(500);
		}
	}

}
