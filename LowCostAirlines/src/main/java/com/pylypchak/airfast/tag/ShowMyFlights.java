package com.pylypchak.airfast.tag;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Collections;
import java.util.List;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.SimpleTagSupport;

import com.pylypchak.airfast.comparator.ByDateFlightComparator;
import com.pylypchak.airfast.model.Flight;
import com.pylypchak.airfast.service.AirportService;
import com.pylypchak.airfast.util.Messager;

public class ShowMyFlights extends SimpleTagSupport {
	private List<Flight> flights;
	private String locale;

	public void setLocale(String locale) {
		this.locale = locale;
	}

	public void setFlights(List<Flight> flights) {
		this.flights = flights;

	}

	public void doTag() throws JspException, IOException {

		AirportService service = new AirportService();
		if (flights != null) {
			/* Use message from attribute */
			Collections.sort(flights, new ByDateFlightComparator());
			JspWriter out = getJspContext().getOut();
			try {
				for (Flight f : flights) {
					out.println("<div class = \"flight\">"
							+ " <div class=\"leftFounded\">");
					out.println("<p>"
							+ Messager.getMessage("flight.from", locale) + " ");
					out.println("<span class=\"foundedFrom\">"
							+ service.getCountryCityAirportNamesByAirportId(f
									.getFromAirportId()) + "</span></p>");
					out.println("<p>"
							+ Messager.getMessage("flight.to", locale)
							+ "<span class=\"foundedTo\">"
							+ service.getCountryCityAirportNamesByAirportId(f
									.getToAirportId()) + "</span></p>");
					out.println("<p>"+Messager.getMessage("flight.freeseats", locale)+": <span class=\"foundedTo\">"
							+ f.getFreeSeats() + "</span></p></div>");
					out.println("   <div id=\"rightFounded\">");
					out.println(" <p class=\"date\">"
							+ new SimpleDateFormat("yyyy.MM.dd hh:mm").format(f
									.getDateFrom()) + "</p>");
					out.println("<p class=\"price\"><span class=\"_price\">"
							+ f.getActualPrice() + "</span>uah</p>");
					out.println("</div>");
					out.println("<form action=\"editFlight\" method=\"get\">");
					out.println("<input type=\"text\" name=\"flightId\" value=\""
							+ f.getId() + "\" hidden>");
					out.println("<button type=\"submit\" class=\"buyTicket\" name =\"passengers\" >"+Messager.getMessage("flight.passengers", locale)+"</button>");
					out.println("<button type=\"submit\" class=\"buyTicket\" name =\"edit\" >"+Messager.getMessage("edit", locale)+"</button>");
					out.println("</form>");
					out.println("</div>");
				}
			} catch (Exception e) {
				e.printStackTrace();
			}

		} else {
			JspWriter out = getJspContext().getOut();
			out.println(Messager.getMessage("flight.noflight", locale));
		}

	}
}
