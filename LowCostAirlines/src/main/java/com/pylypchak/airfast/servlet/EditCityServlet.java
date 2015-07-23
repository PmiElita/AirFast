package com.pylypchak.airfast.servlet;

import java.io.IOException;
import java.util.Collections;
import java.util.List;

import javax.print.attribute.standard.Severity;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.apache.log4j.xml.DOMConfigurator;

import com.pylypchak.airfast.comparator.ByNameCityComparator;
import com.pylypchak.airfast.comparator.ByNameCountryComparator;
import com.pylypchak.airfast.model.City;
import com.pylypchak.airfast.model.Country;
import com.pylypchak.airfast.service.CityService;
import com.pylypchak.airfast.service.CountryService;

/**
 * Servlet implementation class EditCityServlet
 */
public class EditCityServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    /**
     * @see HttpServlet#HttpServlet()
     */
    public EditCityServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
		List<City> cities = new CityService().getAllCities();
	Collections.sort(cities, new ByNameCityComparator());
		request.setAttribute("cities", cities);
		
		List<Country> countries = new CountryService().getAllCountries();
		Collections.sort(countries, new ByNameCountryComparator());
		request.setAttribute("countries", countries);
		request.getRequestDispatcher("pages/editCity.jsp").forward(request, response);
		}catch(Exception e){
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
				if (!request.getParameter("cityName").trim().equals("")){
					City city = new City();
					city.setCountryId(Integer.parseInt(request.getParameter("country")));
					city.setName(request.getParameter("cityName").trim());
				new CityService().saveCity(city);
				    request.setAttribute("message", "City was added");
				    request.getRequestDispatcher("pages/success.jsp").forward(request, response);
				}
				else {
					List<City> cities = new CityService().getAllCities();
					Collections.sort(cities, new ByNameCityComparator());
						request.setAttribute("cities", cities);
						
						List<Country> countries = new CountryService().getAllCountries();
						Collections.sort(countries, new ByNameCountryComparator());
						request.setAttribute("countries", countries);
					request.setAttribute("message", "Wrong city name");
			    request.getRequestDispatcher("pages/editCity.jsp").forward(request, response);
				}
			} 
			if (request.getParameter("save")!=null){
		String cityId = request.getParameter("cityId");
		String cityName = request.getParameter("cityName");
		String countryId= request.getParameter("country");
		if (cityId!= null&&!cityName.trim().equals("")){
			CityService service =new CityService();
			City city= service.getCityById(Integer.parseInt(cityId));
			city.setName(cityName);
			city.setCountryId(Integer.parseInt(countryId));
			service.updateCity(city);
			request.setAttribute("message", "City was updated");
			request.getRequestDispatcher("pages/success.jsp").forward(request, response);
		} else {
			List<City> cities = new CityService().getAllCities();
			Collections.sort(cities, new ByNameCityComparator());
				request.setAttribute("cities", cities);
				
				List<Country> countries = new CountryService().getAllCountries();
				Collections.sort(countries, new ByNameCountryComparator());
				request.setAttribute("countries", countries);
			request.setAttribute("message", "Wrong data");
			request.getRequestDispatcher("pages/editCity.jsp").forward(request, response);
		}
			}
		} catch (Exception e){
			e.printStackTrace();
			response.sendError(500);
		}
	}

}
