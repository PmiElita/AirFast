package com.pylypchak.airfast.tag;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Map;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.SimpleTagSupport;

import com.pylypchak.airfast.model.Ticket;
import com.pylypchak.airfast.model.User;
import com.pylypchak.airfast.service.AirportService;
import com.pylypchak.airfast.service.FlightService;
import com.pylypchak.airfast.util.Messager;

public class ShowPassengers extends SimpleTagSupport {
	private Map<Ticket, User> passengers;
	private String locale;

	public void setPassengers(Map<Ticket, User> map) {
		this.passengers = map;
	}

	public void setLocale(String locale) {
		this.locale = locale;
	}

	public void doTag() throws JspException, IOException {

		if (passengers!= null&&passengers.size() != 0) {
			/* Use message from attribute */
			JspWriter out = getJspContext().getOut();
			try {
				out.println( "<table> <tr>");
				out.println( "<th>№</th>");
				out.println( "<th>"+Messager.getMessage("registration.firstname", locale)+"</th>");
				out.println( "<th>"+Messager.getMessage("registration.lastname", locale)+"</th>");
				out.println( "<th>"+Messager.getMessage("email", locale)+"</th>");
				out.println( "<th>"+Messager.getMessage("ticket.price", locale)+"</th>");
				out.println( "<th>"+Messager.getMessage("ticket.status", locale)+"</th>");
				out.println( "<th>"+Messager.getMessage("ticket.creationdate", locale)+"</th>");
				out.println( "</tr>");
				int index = 1;
				for (Ticket t : passengers.keySet()) {
					User u = passengers.get(t);
					out.println( "<tr>");
					out.println( "<td>"+index+"</td>");
					out.println( "<td>"+u.getFirstName()+"</td>");
					out.println( "<td>"+u.getLastName()+"</td>");
					out.println( "<td>"+u.getEmail()+"</td>");
					out.println( "<td>"+t.getPrice()+"</td>");
					out.println( "<td>"+t.getStatus()+"</td>");
					out.println( "<td>"+new SimpleDateFormat("yyyy.MM.dd hh:mm").format(t.getDate())+"</td>");
					out.println( "</tr>");
					index++;
				}
				out.println( "</table>");
			} catch (Exception e) {
				e.printStackTrace();
			}

		} else {
			JspWriter out = getJspContext().getOut();
			out.println( Messager.getMessage("ticket.noticket", locale) );
		}

	}
}
