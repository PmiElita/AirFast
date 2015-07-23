package com.pylypchak.airfast.servlet;

import java.io.IOException;
import java.util.Collections;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.pylypchak.airfast.comparator.ByDateTicketsComparator;
import com.pylypchak.airfast.model.Ticket;
import com.pylypchak.airfast.service.TicketService;

/**
 * Servlet implementation class MyTicketsServlet
 */
public class MyTicketsServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public MyTicketsServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	 try{
		HttpSession session= request.getSession();
		TicketService ticketService = new TicketService();
		List<Ticket> tickets=ticketService.getTicketByUserId((Integer)session.getAttribute("userId"));
		Collections.sort(tickets, new ByDateTicketsComparator());
		request.setAttribute("tickets", tickets);
		request.getRequestDispatcher("pages/myTickets.jsp").forward(request, response);
	 }catch (Exception e){
		 e.printStackTrace();
		 response.sendError(500);
	 }
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

}
