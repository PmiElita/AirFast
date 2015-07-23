package com.pylypchak.airfast.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.pylypchak.airfast.model.User;
import com.pylypchak.airfast.service.UserService;
import com.pylypchak.airfast.util.Hasher;

/**
 * Servlet implementation class LoginServlet
 */
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public LoginServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.getRequestDispatcher("pages/login.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try{
			boolean isInputDataGood=true;
			String formEmail=request.getParameter("email");
			String formPassword = request.getParameter("password");
			UserService service = new UserService();
			if (formEmail!=null && formPassword!= null&&!service.isEmailFree(formEmail)){
				
				User user=service.getUserByEmail(formEmail);
				if (user.getPassword().equals(Hasher.hashing(formPassword))&&user.getIsEnabled()&&user.getRole().equals("ROLE_USER")){
					HttpSession session = request.getSession(true);
					session.setAttribute("userId", user.getId());
					session.setAttribute("userRole", user.getRole());
					response.sendRedirect("home");
				} else{
					isInputDataGood = false;
				}
			} else {
				isInputDataGood = false;
			}
			if(!isInputDataGood){
				request.setAttribute("email", formEmail);
				request.setAttribute("message", "Wrong email or password");
				request.getRequestDispatcher("pages/login.jsp").forward(request, response);
			}
		}catch (Exception e){
			e.printStackTrace();
			response.sendError(500);
		}
	}

}
