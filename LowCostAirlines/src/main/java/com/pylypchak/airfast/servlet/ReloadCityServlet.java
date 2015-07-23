package com.pylypchak.airfast.servlet;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.pylypchak.airfast.comparator.ByNameAirportsComparator;
import com.pylypchak.airfast.comparator.ByNameCityComparator;
import com.pylypchak.airfast.model.City;
import com.pylypchak.airfast.service.CityService;

/**
 * Servlet implementation class ReloadCityServlet
 */
public class ReloadCityServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ReloadCityServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try{
		List<Map<String, String>> list = new ArrayList<Map<String, String>>();
		CityService service = new CityService();
		List<City> cities;
		if (request.getParameter("selected").equals("")){
			cities=service.getAllCities();
		} else {
		 cities = service.getCitiesByCountryId(Integer.parseInt(request.getParameter("selected")));
		}
		Collections.sort(cities, new ByNameCityComparator());
		for (City c: cities){
			Map<String,String> map = new HashMap<String, String>();
			map.put("value",c.getId().toString() );
			map.put("text", c.getName());
			list.add(map);
		}

	 response.setContentType("application/json");
	 response.setCharacterEncoding("UTF-8");
	 response.getWriter().write(new Gson().toJson(list));
		} catch (Exception e){
			e.printStackTrace();
			response.sendError(500);
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

	}

}
