package com.pylypchak.airfast.servlet;

import java.io.IOException;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.pylypchak.airfast.model.ForgottenPassword;
import com.pylypchak.airfast.model.User;
import com.pylypchak.airfast.service.ForgottenPasswordService;
import com.pylypchak.airfast.service.UserService;
import com.pylypchak.airfast.util.Hasher;

/**
 * Servlet implementation class NewPasswordServlet
 */
public class NewPasswordServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public NewPasswordServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		   try{
		   String code=request.getRequestURI().split("/")[3];
		   ForgottenPasswordService forgottenService= new ForgottenPasswordService();
		   ForgottenPassword forgottenPassword =forgottenService.getForgottenPasswordByCode(code);
		   if (forgottenPassword!=null&&forgottenPassword.getIsEnabled()&&(new Date().getTime()-forgottenPassword.getCreationDate().getTime())<86400000){

			   request.getSession().setAttribute("code", forgottenPassword.getCode());

               response.sendRedirect("../setPassword");			   
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
		try{
			boolean isDataGood = true;
		if (request.getParameter("code")!=null&&request.getParameter("password")!= null &&!request.getParameter("password").trim().equals("")){ 
		ForgottenPasswordService forgottenService= new ForgottenPasswordService();
		   ForgottenPassword forgottenPassword =forgottenService.getForgottenPasswordByCode(request.getParameter("code"));
		   if (forgottenPassword!=null&&forgottenPassword.getIsEnabled()&&(new Date().getTime()-forgottenPassword.getCreationDate().getTime())<86400000){
			   User user = new UserService().getUserById(forgottenPassword.getUserId());
			   forgottenPassword.setIsEnabled(false);
			   forgottenService.updateFrogottenPassword(forgottenPassword);
			   user.setPassword(Hasher.hashing(request.getParameter("password")));
			   new UserService().updateUser(user);
			   HttpSession session = request.getSession();
			   session.setAttribute("userId", user.getId());
			   session.setAttribute("userRole", user.getRole());
			   response.sendRedirect("index");
		   } else {
			   isDataGood = false;
		   }
		 } else {
			 isDataGood = false;
		 }
		if (!isDataGood){
			response.sendRedirect("index");
		}
		} catch (Exception e){
			e.printStackTrace();
			response.sendError(500);
		}
	}

}
