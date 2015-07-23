package com.pylypchak.airfast.servlet;

import java.io.IOException;
import java.util.Collections;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.apache.log4j.xml.DOMConfigurator;

import com.pylypchak.airfast.comparator.ByNameAirportsComparator;
import com.pylypchak.airfast.comparator.ByNameCityComparator;
import com.pylypchak.airfast.model.Airport;
import com.pylypchak.airfast.model.City;
import com.pylypchak.airfast.service.AirportService;
import com.pylypchak.airfast.service.CityService;

/**
 * Servlet implementation class EditAirportServlet
 */
public class EditAirportServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public EditAirportServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	try{
		List<Airport> airports = new AirportService().getAllAirports();
		List<City> cities = new CityService().getAllCities();
		Collections.sort(airports, new ByNameAirportsComparator());
		Collections.sort(cities, new ByNameCityComparator());
		request.setAttribute("airports", airports);
		request.setAttribute("cities", cities);
		request.getRequestDispatcher("pages/editAirport.jsp").forward(request, response);
	} catch (Exception e){
		e.printStackTrace();
		response.sendError(500);
	}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try{
			if (request.getParameter("add")!=null){
				if (!request.getParameter("airportName").trim().equals("")){
					Airport airport = new Airport();
					airport.setCityId(Integer.parseInt(request.getParameter("city")));
					airport.setName(request.getParameter("airportName").trim());
				new AirportService().saveAirport(airport);
				    request.setAttribute("message", "Airport was added");
				    request.getRequestDispatcher("pages/success.jsp").forward(request, response);
				}
				else { 
					List<Airport> airports = new AirportService().getAllAirports();
					List<City> cities = new CityService().getAllCities();
					Collections.sort(airports, new ByNameAirportsComparator());
					Collections.sort(cities, new ByNameCityComparator());
					request.setAttribute("airports", airports);
					request.setAttribute("cities", cities);					
					request.setAttribute("message", "Wrong airport name");
			    request.getRequestDispatcher("pages/editAirport.jsp").forward(request, response);
				}
			} 
			if (request.getParameter("save")!=null){
		String airportId = request.getParameter("airportId");
		String airportName = request.getParameter("airportName");
		String cityId= request.getParameter("city");
		if (airportId!= null&&!airportName.trim().equals("")){
			AirportService service =new AirportService();
			Airport airport= service.getAirportById(Integer.parseInt(airportId));
			airport.setName(airportName);
			airport.setCityId(Integer.parseInt(cityId));
			service.updateAirport(airport);
			request.setAttribute("message", "Airport was updated");
			request.getRequestDispatcher("pages/success.jsp").forward(request, response);
		} else {
			List<Airport> airports = new AirportService().getAllAirports();
			List<City> cities = new CityService().getAllCities();
			Collections.sort(airports, new ByNameAirportsComparator());
			Collections.sort(cities, new ByNameCityComparator());
			request.setAttribute("airports", airports);
			request.setAttribute("cities", cities);
			request.setAttribute("message", "Wrong data");
			request.getRequestDispatcher("pages/editAirport.jsp").forward(request, response);
		}
			}
		} catch (Exception e){
			e.printStackTrace();
			response.sendError(500);
		}
	}

}
