package com.pylypchak.airfast.servlet;

import java.io.IOException;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.apache.log4j.xml.DOMConfigurator;

import com.pylypchak.airfast.model.User;
import com.pylypchak.airfast.service.UserService;

/**
 * Servlet implementation class ConfirmRegistrationServlet
 */
public class ConfirmRegistrationServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public ConfirmRegistrationServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		 try{
			   String code=request.getRequestURI().split("/")[3];
			  
			   UserService userService = new UserService();
			   User user =userService.getUserByConfirmCode(code);
			   if (user!=null&&!user.getIsEnabled()){
                  user.setIsEnabled(true);
                  userService.updateUser(user);
				   request.getSession().setAttribute("userId", user.getId());
				   request.getSession().setAttribute("userRole", user.getRole());
				   request.getSession().setAttribute("complete", "Registration complete");
	               response.sendRedirect("../home");			   
			   }else{
				   response.sendRedirect("../sendError");;
			   } 
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
