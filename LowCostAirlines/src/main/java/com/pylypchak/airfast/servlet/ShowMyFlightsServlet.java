package com.pylypchak.airfast.servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.pylypchak.airfast.model.Flight;
import com.pylypchak.airfast.service.FlightService;

/**
 * Servlet implementation class ShowMyFlightsServlet
 */
public class ShowMyFlightsServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ShowMyFlightsServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try{
		List<Flight> flights = new FlightService().getFlightsByAdminId((Integer)request.getSession().getAttribute("userId"));
		request.setAttribute("flights", flights);
		request.getRequestDispatcher("pages/myFlights.jsp").forward(request, response);
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
