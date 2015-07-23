package com.pylypchak.airfast.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.pylypchak.airfast.model.User;
import com.pylypchak.airfast.service.FacebookLoginService;


/**
 * Servlet implementation class FacebookLoginCodeServlet
 */
public class FacebookLoginCodeServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public FacebookLoginCodeServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try{
			String code = request.getParameter("code");

			if (code != null) {
				FacebookLoginService facebookLoginService = new FacebookLoginService();
				facebookLoginService.getAccessToken(code);
                facebookLoginService.getUserFromSocilanetwork();
				if (!facebookLoginService.isRegistrated()) {
					User user = facebookLoginService.getUserFromSocilanetwork();
	            
				   request.setAttribute("user", user);
					request.getRequestDispatcher("pages/socialRegistration.jsp").forward(request, response);
				} else {
				User user = facebookLoginService.getUserForLogin();
				HttpSession session = request.getSession();
				session.setAttribute("userId", user.getId());
				session.setAttribute("userRole", user.getRole());
				response.sendRedirect("home");
				}
			} else {
	                response.sendRedirect("index");  
			}
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
