package com.pylypchak.airfast.servlet;

import java.io.IOException;
import java.text.SimpleDateFormat;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.pylypchak.airfast.model.Flight;
import com.pylypchak.airfast.service.AirportService;
import com.pylypchak.airfast.service.FlightService;

/**
 * Servlet implementation class TicketFormServlet
 */
public class TicketFormServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public TicketFormServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	doPost(request, response);	
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try{
			Flight flight = new FlightService().getFlightById(Integer.parseInt(request.getParameter("flightId")));
			if (flight!=null &&flight.getFreeSeats()>0){
			AirportService airportService = new AirportService();
			String adressFrom=airportService.getCountryCityAirportNamesByAirportId(flight.getFromAirportId());
			String adressTo=airportService.getCountryCityAirportNamesByAirportId(flight.getToAirportId());
			String dateFrom = new SimpleDateFormat("dd.MM.yyyy hh:mm").format(flight.getDateFrom());
			String dateTo = new SimpleDateFormat("dd.MM.yyyy hh:mm").format(flight.getDateTo());
			
			request.setAttribute("flightId", flight.getId());
			request.setAttribute("dateTo", dateTo);
			request.setAttribute("dateFrom", dateFrom);
			request.setAttribute("adressFrom", adressFrom);
			request.setAttribute("adressTo", adressTo);
			request.setAttribute("price",  flight.getActualPrice());
			request.setAttribute("prime", flight.getPrimePrice());
			request.getRequestDispatcher("pages/ticketForm.jsp").forward(request, response);
			} else {
				response.sendError(404);
			}
		} catch(Exception e){
			e.printStackTrace();
			response.sendError(500);
		}
	}

}
