package com.pylypchak.airfast.servlet;

import java.io.IOException;
import java.sql.Date;
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
import com.pylypchak.airfast.exception.WrongInputDataException;
import com.pylypchak.airfast.model.Airport;
import com.pylypchak.airfast.model.City;
import com.pylypchak.airfast.model.Country;
import com.pylypchak.airfast.model.Flight;
import com.pylypchak.airfast.service.AirportService;
import com.pylypchak.airfast.service.CityService;
import com.pylypchak.airfast.service.CountryService;
import com.pylypchak.airfast.service.FlightService;

/**
 * Servlet implementation class FlightCreationServlet
 */
public class FlightCreationServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public FlightCreationServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
  try{
		Airport airport = new Airport();
		airport.setName("-- select airport --");
		fillRequestAtributes(request,  airport, "From");
		fillRequestAtributes(request,  airport, "To");
		request.getRequestDispatcher("pages/flightCreation.jsp").forward(
				request, response);
  } catch (Exception e){
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
			FlightService service = new FlightService();
			Flight flight = fillFlight(request);
			service.saveFlight(flight);
            response.sendRedirect("adminHome");
		} catch (WrongInputDataException|SQLException e) {
			e.printStackTrace();
			
			Airport airport = new Airport();
			airport.setName("-- select airport --");
			try {
				Flight flight1 = fillFlight(request);
				request.setAttribute("flight", flight1);
				Airport airportFrom = new AirportService()
				.getAirportById(flight1.getFromAirportId());
		Airport airportTo = new AirportService().getAirportById(flight1
				.getToAirportId());
		fillRequestAtributes(request, airportFrom, "From");
		fillRequestAtributes(request, airportTo, "To");
				request.setAttribute("dateFrom", new SimpleDateFormat(
						"yyyy-MM-dd").format(flight1.getDateFrom()));
				request.setAttribute("timeFrom", new SimpleDateFormat("hh:mm")
						.format(flight1.getDateFrom()));
				request.setAttribute("dateTo", new SimpleDateFormat(
						"yyyy-MM-dd").format(flight1.getDateTo()));
				request.setAttribute("timeTo", new SimpleDateFormat("hh:mm")
						.format(flight1.getDateTo()));
			} catch (ReflectiveOperationException | SQLException |WrongInputDataException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
				response.sendError(500);
			} 
			
			request.setAttribute("message", "Wrong input data");
			request.getRequestDispatcher("pages/flightCreation.jsp").forward(request, response);
		} 
		catch (Exception e) {
			e.printStackTrace();
			response.sendError(500);
		}

	}

	private Flight fillFlight(HttpServletRequest request)
			throws WrongInputDataException {
		Flight result = new Flight();
		try {
			result.setFromAirportId(Integer.parseInt(
							request.getParameter("airportFrom")));

			result.setToAirportId(Integer.parseInt(
					request.getParameter("airportTo")));
			
			result.setAdminId((Integer) request.getSession().getAttribute(
					"userId"));
			result.setDateFrom(convertIntoTimestamp(
					request.getParameter("fromDate"),
					request.getParameter("fromTime")));
          
			result.setDateTo(convertIntoTimestamp(request.getParameter("toDate"),
					request.getParameter("toTime")));

			result.setMaxPrice(Double.parseDouble(request
					.getParameter("maxPrice")));

			result.setMinPrice(Double.parseDouble(request
					.getParameter("minPrice")));
            result.setPrimePrice(Double.parseDouble(request.getParameter("primePrice")));
			result.setSeats(Integer.parseInt(request.getParameter("seats")));
			if (request.getParameter("isHot") != null) {
				result.setIsHot(true);
				result.setHotPrice(Double.parseDouble(request
						.getParameter("hotPrice")));
			} else {
				result.setIsHot(false);
			}
		} catch (Exception e) {
			e.printStackTrace();
			throw new WrongInputDataException();
		}
		return result;

	}
	private void fillRequestAtributes(HttpServletRequest request,
			Airport selectedAirport, String direction)
			throws ReflectiveOperationException, SQLException {

		AirportService airportService = new AirportService();
		Airport airport = new Airport();
		airport.setId(null);
		airport.setName("-- select airport --");
		List<Airport> airports = airportService.getAllAirports();
		Collections.sort(airports, new ByNameAirportsComparator());
		airports.add(0, airport);

		request.setAttribute("selectedAirport" + direction, selectedAirport);

		request.setAttribute("airports" + direction, airports);
	}
	private Timestamp convertIntoTimestamp(String date, String time)
			throws ParseException {
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm");
		Timestamp result = new Timestamp(dateFormat.parse(date + " " + time).getTime());
		return result;
	}

}
