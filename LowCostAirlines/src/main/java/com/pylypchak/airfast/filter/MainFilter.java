package com.pylypchak.airfast.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet Filter implementation class MainFilter
 */
public class MainFilter implements Filter {

	/**
	 * Default constructor.
	 */
	public MainFilter() {
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see Filter#destroy()
	 */
	public void destroy() {
		// TODO Auto-generated method stub
	}

	/**
	 * @see Filter#doFilter(ServletRequest, ServletResponse, FilterChain)
	 */
	public void doFilter(ServletRequest request, ServletResponse response,
			FilterChain chain) throws IOException, ServletException {
		request.setCharacterEncoding("UTF-8");

		HttpServletRequest httpRequest = (HttpServletRequest) request;
		HttpServletResponse httpResponse = (HttpServletResponse) response;
		HttpSession session = httpRequest.getSession();
		if (session.getAttribute("language") == null) {
			session.setAttribute("language", "en");
		}
		String uri = httpRequest.getRequestURI();
		if (uri.endsWith(".css") || uri.endsWith(".jpg")
				|| uri.endsWith(".svg") || uri.endsWith(".png")
				|| uri.endsWith(".js") || uri.endsWith("changeLanguage")
				|| uri.contains("newPassword") || uri.endsWith("setPassword")
				|| uri.endsWith("sendError") || uri.endsWith("reloadCity")
				|| uri.endsWith("reloadAirports") || uri.endsWith("index")
				|| uri.contains("confirmRegistration")) {
			chain.doFilter(request, response);

		} else if (session.getAttribute("userId") == null) {
			if (uri.endsWith("/") | uri.endsWith("index")
					|| uri.endsWith("login") || uri.endsWith("registration")
					|| uri.endsWith("adminLogin") || uri.endsWith("superadmin")
					|| uri.endsWith("forgotPassword")
					|| uri.endsWith("vkLoginCode") || uri.endsWith("vkLogin")
					|| uri.endsWith("facebookLogin")
					|| uri.endsWith("facebookLoginCode")) {
				chain.doFilter(request, response);
			} else {
				httpResponse.sendError(404);
				;
			}
		} else if (session.getAttribute("userRole").equals("ROLE_ADMIN")) {
			if (uri.endsWith("createFlight") || uri.endsWith("adminHome")
					|| uri.endsWith("logout") || uri.endsWith("editCountry")
					|| uri.endsWith("editCity") || uri.endsWith("getCountry")
					|| uri.endsWith("editAirport")
					|| uri.endsWith("editFlight") || uri.endsWith("getCity")
					|| uri.endsWith("myFlights") || uri.endsWith("passengers")) {
				chain.doFilter(request, response);
			} else {
				httpResponse.sendError(404);
			}
		} else if (session.getAttribute("userRole").equals("ROLE_SUPERADMIN")) {
			if (uri.endsWith("superadminHome") || uri.endsWith("logout")
					|| uri.endsWith("adminRegistration")
					|| uri.endsWith("showAdmins")) {
				chain.doFilter(request, response);
			} else {
				httpResponse.sendError(404);
			}
		} else {
			chain.doFilter(request, response);
		}

	}

	/**
	 * @see Filter#init(FilterConfig)
	 */
	public void init(FilterConfig fConfig) throws ServletException {
		// TODO Auto-generated method stub
	}

}
