package com.pylypchak.airfast.servlet;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.pylypchak.airfast.model.City;
import com.pylypchak.airfast.model.Country;
import com.pylypchak.airfast.service.CityService;
import com.pylypchak.airfast.service.CountryService;

/**
 * Servlet implementation class GetCountryServlet
 */
public class GetCountryServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public GetCountryServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	try{
		City city = new  CityService().getCityById(Integer.parseInt(request.getParameter("cityId")));
		Country country = new CountryService().getCountryById(city.getCountryId());
		Map<String, String> map = new HashMap<String, String>();
		map.put("countryId", country.getId().toString());
		response.setContentType("application/json");
		response.setCharacterEncoding("UTF-8");
		response.getWriter().write(new Gson().toJson(map));
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
