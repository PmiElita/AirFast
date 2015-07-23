package com.pylypchak.airfast.service;

import java.sql.SQLException;
import java.util.List;

import com.pylypchak.airfast.dao.TicketDAO;
import com.pylypchak.airfast.model.Flight;
import com.pylypchak.airfast.model.Ticket;

public class TicketService {
	private TicketDAO ticketDAO;

	public TicketService(){
		ticketDAO= new TicketDAO();
	}
	
	public void saveTicket(Ticket ticket) throws SQLException,
			ReflectiveOperationException {
		ticketDAO.saveTicket(ticket);
		FlightService flightService = new FlightService();
		Flight flight = flightService.getFlightById(ticket.getFlightId());
		flight.setFreeSeats(flight.getFreeSeats()-1);
		flightService.updateFlight(flight);
	}
	
	public void updateTicket(Ticket ticket) throws SQLException,
	ReflectiveOperationException {
		ticketDAO.updateTicket(ticket);
	}

	public List<Ticket> getTicketByUserId(int userId) throws SQLException,
			ReflectiveOperationException {
		return ticketDAO.getTicketByUserId(userId);
	}
	
	public Ticket getTicketById(int id) throws ReflectiveOperationException, SQLException{
		return ticketDAO.getTicketById(id);
	}
}
