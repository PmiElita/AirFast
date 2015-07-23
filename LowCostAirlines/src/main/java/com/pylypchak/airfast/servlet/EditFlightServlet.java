package com.pylypchak.airfast.servlet;

import java.io.IOException;
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

import com.pylypchak.airfast.comparator.ByNameAirportsComparator;
import com.pylypchak.airfast.exception.WrongInputDataException;
import com.pylypchak.airfast.model.Airport;
import com.pylypchak.airfast.model.Flight;
import com.pylypchak.airfast.service.AirportService;
import com.pylypchak.airfast.service.FlightService;

/**
 * Servlet implementation class EditFlightServlet
 */
public class EditFlightServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public EditFlightServlet() {
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
			if (request.getParameter("edit") != null) {
				Flight flight = new FlightService().getFlightById(Integer
						.parseInt(request.getParameter("flightId")));
				request.setAttribute("flight", flight);
				Airport airportFrom = new AirportService()
						.getAirportById(flight.getFromAirportId());
				Airport airportTo = new AirportService().getAirportById(flight
						.getToAirportId());
				fillRequestAtributes(request, airportFrom, "From");
				fillRequestAtributes(request, airportTo, "To");
				request.setAttribute("dateFrom", new SimpleDateFormat(
						"yyyy-MM-dd").format(flight.getDateFrom()));
				request.setAttribute("timeFrom", new SimpleDateFormat("hh:mm")
						.format(flight.getDateFrom()));
				request.setAttribute("dateTo", new SimpleDateFormat(
						"yyyy-MM-dd").format(flight.getDateTo()));
				request.setAttribute("timeTo", new SimpleDateFormat("hh:mm")
						.format(flight.getDateTo()));
				request.getRequestDispatcher("pages/editFlight.jsp").forward(
						request, response);
			} else if (request.getParameter("passengers") != null) {
				request.getRequestDispatcher("passengers").forward(request,
						response);
			} else {
				response.sendError(404);
			}
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
			FlightService service = new FlightService();
			Flight flight = fillFlight(request);
			service.updateFlight(flight);
			response.sendRedirect("adminHome");
		} catch (WrongInputDataException | SQLException e) {
			e.printStackTrace();
			Airport airport = new Airport();
			airport.setName("-- select airport --");
			try {
				Flight flight1 = new FlightService().getFlightById(Integer
						.parseInt(request.getParameter("flightId")));
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
			} catch (ReflectiveOperationException | SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
				response.sendError(500);
			}

			request.setAttribute("message", "Wrong input data");
			request.getRequestDispatcher("pages/editFlight.jsp").forward(
					request, response);
		} catch (Exception e) {
			e.printStackTrace();
			response.sendError(500);
		}

	}

	private Flight fillFlight(HttpServletRequest request)
			throws WrongInputDataException {
		Flight result = new Flight();
		try {
			result.setId(Integer.parseInt(request.getParameter("flightId")));
			Flight flight = new FlightService().getFlightById(Integer
					.parseInt(request.getParameter("flightId")));
			result.setFromAirportId(Integer.parseInt(request
					.getParameter("airportFrom")));

			result.setToAirportId(Integer.parseInt(request
					.getParameter("airportTo")));

			result.setAdminId((Integer) request.getSession().getAttribute(
					"userId"));
			result.setDateFrom(convertIntoTimestamp(
					request.getParameter("fromDate"),
					request.getParameter("fromTime")));

			result.setDateTo(convertIntoTimestamp(
					request.getParameter("toDate"),
					request.getParameter("toTime")));

			result.setMaxPrice(Double.parseDouble(request
					.getParameter("maxPrice")));

			result.setMinPrice(Double.parseDouble(request
					.getParameter("minPrice")));
			result.setPrimePrice(Double.parseDouble(request
					.getParameter("primePrice")));
			result.setSeats(Integer.parseInt(request.getParameter("seats")));
			result.setFreeSeats(result.getSeats()
					- (flight.getSeats() - flight.getFreeSeats()));
			if (result.getFreeSeats() < 0) {
				result.setFreeSeats(0);
			}
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

	private Timestamp convertIntoTimestamp(String date, String time)
			throws ParseException {
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm");
		Timestamp result = new Timestamp(dateFormat.parse(date + " " + time)
				.getTime());
		return result;
	}

	private void fillRequestAtributes(HttpServletRequest request,
			Airport selectedAirport, String direction)
			throws ReflectiveOperationException, SQLException {

		AirportService airportService = new AirportService();

		List<Airport> airports = airportService.getAllAirports();
		Collections.sort(airports, new ByNameAirportsComparator());

		request.setAttribute("selectedAirport" + direction, selectedAirport);

		request.setAttribute("airports" + direction, airports);
	}

}
