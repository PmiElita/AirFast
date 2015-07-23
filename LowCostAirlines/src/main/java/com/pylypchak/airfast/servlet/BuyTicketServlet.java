package com.pylypchak.airfast.servlet;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.apache.log4j.xml.DOMConfigurator;

import com.pylypchak.airfast.model.Flight;
import com.pylypchak.airfast.model.Ticket;
import com.pylypchak.airfast.service.AirportService;
import com.pylypchak.airfast.service.FlightService;
import com.pylypchak.airfast.service.TicketService;

/**
 * Servlet implementation class BuyTicketServlet
 */
public class BuyTicketServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public BuyTicketServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try{
			HttpSession session = request.getSession();
			
			Ticket ticket= new Ticket();
			if (request.getParameter("ticketId")!=null){
				ticket=new TicketService().getTicketById(Integer.parseInt(request.getParameter("ticketId")));
			} else {
			
		Flight flight= new FlightService().getFlightById(Integer.parseInt(request.getParameter("flightId")));

		ticket.setFlightId(flight.getId());
		ticket.setUserId((Integer)session.getAttribute("userId"));
		ticket.setDate(new Timestamp(new Date().getTime()));
		if (request.getParameter("primeRegistration")!=null){
			ticket.setIsPrimeRegistration(true);
		}
		if (request.getParameter("primeBoarding")!=null){
			ticket.setIsPrimeBoarding(true);
		}
		if (request.getParameter("baggage").trim().length()>0 ){
			ticket.setBagageQuantity(Integer.parseInt(request.getParameter("baggage").trim()));
		}
		culculatePrice(ticket, flight);
			}
			Flight flight = new FlightService().getFlightById(ticket.getFlightId());
			AirportService airportService = new AirportService();

			String adressFrom=airportService.getCountryCityAirportNamesByAirportId(flight.getFromAirportId());
			String adressTo=airportService.getCountryCityAirportNamesByAirportId(flight.getToAirportId());
			String dateFrom = new SimpleDateFormat("dd.MM.yyyy hh:mm").format(flight.getDateFrom());
			String dateTo = new SimpleDateFormat("dd.MM.yyyy hh:mm").format(flight.getDateTo());
			
			
			request.setAttribute("dateTo", dateTo);
			request.setAttribute("dateFrom", dateFrom);
			request.setAttribute("adressFrom", adressFrom);
			request.setAttribute("adressTo", adressTo);
			request.setAttribute("date",new SimpleDateFormat("dd.MM.yyyy").format(new Date()));
		request.setAttribute("ticket", ticket);
		session.setAttribute("ticket", ticket);
		request.getRequestDispatcher("pages/buyTicket.jsp").forward(request, response);

		} catch (Exception e){
			e.printStackTrace();
			response.sendError(500);
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
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try{
			if (request.getParameter("ticketId")!=null){
				doGet(request, response);
			} else
		if (request.getParameter("cancel")!=null){
			response.sendRedirect("home");
		} else {
		
			HttpSession session = request.getSession();
			if (session.getAttribute("ticket")!=null){
				
			Ticket ticket =(Ticket)session.getAttribute("ticket");
			
			TicketService service = new TicketService();
			ticket.setStatus("Bought");
			ticket.setDate(new Timestamp(new Date().getTime()));
			if (ticket.getId()!=null){
				service.updateTicket(ticket);
			}else {
			service.saveTicket(ticket);
			}
			session.removeAttribute("ticket");
			}
			response.sendRedirect("myTickets");
		}
		
		} catch (Exception e){
			e.printStackTrace();
			response.sendError(500);
		}
	}

}
