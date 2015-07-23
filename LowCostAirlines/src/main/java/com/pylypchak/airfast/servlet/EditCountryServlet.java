package com.pylypchak.airfast.servlet;

import java.io.IOException;
import java.util.Collections;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.pylypchak.airfast.comparator.ByNameCountryComparator;
import com.pylypchak.airfast.model.Country;
import com.pylypchak.airfast.service.CountryService;

/**
 * Servlet implementation class EditCountryServlet
 */
public class EditCountryServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public EditCountryServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	 try{
		List<Country> countries = new CountryService().getAllCountries();
		Collections.sort(countries, new ByNameCountryComparator());
	    request.setAttribute("countries", countries);
		request.getRequestDispatcher("pages/editCountry.jsp").forward(request, response);
		
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
			if (!request.getParameter("countryName").trim().equals("")){
				Country country = new Country();
				country.setName(request.getParameter("countryName").trim());
				new CountryService().saveCountry(country);
			    request.setAttribute("message", "Country was added");
			    request.getRequestDispatcher("pages/success.jsp").forward(request, response);
			}
			else {
				List<Country> countries = new CountryService().getAllCountries();
				Collections.sort(countries, new ByNameCountryComparator());
			    request.setAttribute("countries", countries);
				request.setAttribute("message", "Wrong country name");
		    request.getRequestDispatcher("pages/editCountry.jsp").forward(request, response);
			}
		} 
		if (request.getParameter("save")!=null){
			if (!request.getParameter("countryName").trim().equals("")){
				Country country = new CountryService().getCountryById(Integer.parseInt(request.getParameter("countryId")));
		
				country.setName(request.getParameter("countryName").trim());
				new CountryService().updateCountry(country);
			    request.setAttribute("message", "Country was updated");
			    request.getRequestDispatcher("pages/success.jsp").forward(request, response);
			}
			else {
				List<Country> countries = new CountryService().getAllCountries();
				Collections.sort(countries, new ByNameCountryComparator());
			    request.setAttribute("countries", countries);
				request.setAttribute("message", "Wrong country name");
		    request.getRequestDispatcher("pages/editCountry.jsp").forward(request, response);
			}
		}
	} catch(Exception e){
		e.printStackTrace();
		response.sendError(500);
	}
	}

}
