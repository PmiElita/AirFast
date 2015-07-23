package com.pylypchak.airfast.tag;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.List;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.SimpleTagSupport;

import com.pylypchak.airfast.model.Ticket;
import com.pylypchak.airfast.service.AirportService;
import com.pylypchak.airfast.service.FlightService;
import com.pylypchak.airfast.util.Messager;

public class ShowTickets extends SimpleTagSupport {
	private List<Ticket> tickets;
	private String locale;

	public void setLocale(String locale) {
		this.locale = locale;
	}

	public void setTickets(List<Ticket> tickets) {
		this.tickets = tickets;
	}

	public void doTag() throws JspException, IOException {
		AirportService airportService = new AirportService();
		FlightService flightService = new FlightService();
		if (tickets != null) {
			/* Use message from attribute */
			JspWriter out = getJspContext().getOut();
			try {
				for (Ticket t : tickets) {
					out.println("<div class = \"flight\">"
							+ " <div class=\"leftFounded\">");
					out.println("<p>"
							+ Messager.getMessage("flight.from", locale) + " ");
					out.println("<span class=\"foundedFrom\">"
							+ airportService
									.getCountryCityAirportNamesByAirportId(flightService
											.getFlightById(t.getFlightId())
											.getFromAirportId())
							+ "</span></p>");
					out.println("<p>"
							+ Messager.getMessage("flight.to", locale) + " <span class=\"foundedTo\">"
							+ airportService
									.getCountryCityAirportNamesByAirportId(flightService
											.getFlightById(t.getFlightId())
											.getToAirportId()) + "</span></p>");
					out.println(" <p>"
							+ Messager.getMessage("ticket.baggage", locale) + ": <span>");
					out.println(t.getBagageQuantity() + "</span> </p>");
					out.println(" <p>"
							+ Messager.getMessage("ticket.primeregistration", locale) + ": <span>");
					
					if (t.getIsPrimeRegistration()){
						out.println( Messager.getMessage("yes", locale));
					} else{
						out.println(Messager.getMessage("no", locale));
					}
					out.println( "</span> </p>");
					out.println(" <p>"
							+ Messager.getMessage("ticket.primeboarding", locale) + ": <span>");
					if (t.getIsPrimeBoarding()){
						out.println( Messager.getMessage("yes", locale));
					} else{
						out.println(Messager.getMessage("no", locale));
					}
					out.println( "</span> </p>");
					out.println(" <p>"
							+ Messager.getMessage("ticket.status", locale) + ": <span>");
					if (t.getStatus().equals("Reserved"))
						out.println(Messager.getMessage("ticket.status.reserved", locale));
					else {
						out.println(Messager.getMessage("ticket.status.bought", locale));
					}
					out.println( "</span> </p>");
					out.println(" <p>"
							+ Messager.getMessage("ticket.creationdate", locale) + ": <span>");
					out.println(new SimpleDateFormat("yyyy.MM.dd hh:mm")
							.format(t.getDate()) + "</span> </p> </div>");

					out.println("   <div id=\"rightFounded\">");
					out.println(" <p class=\"date\">"
							+ new SimpleDateFormat("yyyy.MM.dd hh:mm")
									.format(flightService.getFlightById(
											t.getFlightId()).getDateFrom())
							+ "</p>");
					out.println(" <p class=\"date\">"
							+ new SimpleDateFormat("yyyy.MM.dd hh:mm")
									.format(flightService.getFlightById(
											t.getFlightId()).getDateTo())
							+ "</p>");
					out.println("<p class=\"price\"><span class=\"_price\">"
							+ t.getPrice() + ""
							+ Messager.getMessage("uah", locale) + " </span></p>");
					out.println("</div>");
					if (t.getStatus().equals("Reserved")) {
						out.println("<form action=\"buyTicket\" method=\"get\">");
						out.println("<input type=\"text\" name=\"ticketId\" value=\""
								+ t.getId() + "\" hidden>");
						out.println("<button type=\"submit\" class=\"buyTicket\">"
							+ Messager.getMessage("ticket.buy", locale) + "</button>");
						out.println("</form>");
					}

					out.println("</div>");
				}
			} catch (Exception e) {
				e.printStackTrace();
			}

		} else {
			JspWriter out = getJspContext().getOut();
			out.println( Messager.getMessage("ticket.noticket", locale) );
		}

	}
}
