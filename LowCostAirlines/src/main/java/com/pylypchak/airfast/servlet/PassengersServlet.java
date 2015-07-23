package com.pylypchak.airfast.servlet;

import java.io.IOException;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.pylypchak.airfast.model.Flight;
import com.pylypchak.airfast.model.Ticket;
import com.pylypchak.airfast.model.User;
import com.pylypchak.airfast.service.FlightService;
import com.pylypchak.airfast.service.UserService;

/**
 * Servlet implementation class PassengersServlet
 */
public class PassengersServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public PassengersServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try{
		
	    Map<Ticket, User> map = new UserService().getUsersAndTicketsByflightId(Integer.parseInt(request.getParameter("flightId")));
		request.setAttribute("map", map);
		request.getRequestDispatcher("pages/passengers.jsp").forward(request, response);
		} catch (Exception e){
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
