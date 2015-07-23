package com.pylypchak.airfast.servlet;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.pylypchak.airfast.model.Flight;
import com.pylypchak.airfast.model.Ticket;
import com.pylypchak.airfast.service.AirportService;
import com.pylypchak.airfast.service.FlightService;

/**
 * Servlet implementation class TicketAction
 */
public class TicketAction extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public TicketAction() {
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
			Flight flight = new FlightService().getFlightById(Integer
					.parseInt(request.getParameter("flightId")));
			AirportService airportService = new AirportService();

			String adressFrom=airportService.getCountryCityAirportNamesByAirportId(flight.getFromAirportId());
			String adressTo=airportService.getCountryCityAirportNamesByAirportId(flight.getToAirportId());
			String dateFrom = new SimpleDateFormat("dd.MM.yyyy hh:mm").format(flight.getDateFrom());
			String dateTo = new SimpleDateFormat("dd.MM.yyyy hh:mm").format(flight.getDateTo());
			
			
			request.setAttribute("dateTo", dateTo);
			request.setAttribute("dateFrom", dateFrom);
			request.setAttribute("adressFrom", adressFrom);
			request.setAttribute("adressTo", adressTo);
			
			
			HttpSession session = request.getSession();
			Ticket ticket = new Ticket();
			
			ticket.setFlightId(flight.getId());
			ticket.setUserId((Integer) session.getAttribute("userId"));
			ticket.setDate(new Timestamp(new Date().getTime()));
			
			if (request.getParameter("primeRegistration") != null) {
				ticket.setIsPrimeRegistration(true);
			}
			if (request.getParameter("primeBoarding") != null) {
				ticket.setIsPrimeBoarding(true);
			}
			if (request.getParameter("baggage").trim().length() > 0) {
				ticket.setBagageQuantity(Integer.parseInt(request.getParameter(
						"baggage").trim()));
			}
			culculatePrice(ticket, flight);
			
			request.setAttribute("ticket", ticket);
			
			System.out.println(ticket.getPrice());
			if (request.getParameter("buy") != null) {
				request.getRequestDispatcher("buyTicket").forward(
						request, response);
			} else
			if (request.getParameter("reserve") != null) {
				request.getRequestDispatcher("reserveTicket")
						.forward(request, response);
			} else {
				response.sendError(404);
			}

		} catch (Exception e) {
			e.printStackTrace();
			response.sendError(404);
		}
	}

	private void culculatePrice(Ticket ticket, Flight flight) throws IOException {
		double result = flight.getActualPrice();
		if (ticket.getIsPrimeBoarding()) {
		result+=flight.getPrimePrice();

		}
		if (ticket.getIsPrimeRegistration()) {
			result+=flight.getPrimePrice();
		}
		result += flight.getActualPrice() * 1.25 * ticket.getBagageQuantity();
		ticket.setPrice(result);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

}
