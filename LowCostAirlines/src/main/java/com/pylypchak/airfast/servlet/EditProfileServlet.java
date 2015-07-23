package com.pylypchak.airfast.servlet;

import java.io.IOException;
import java.security.NoSuchAlgorithmException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.pylypchak.airfast.exception.WrongInputDataException;
import com.pylypchak.airfast.model.User;
import com.pylypchak.airfast.service.UserService;
import com.pylypchak.airfast.util.Hasher;

/**
 * Servlet implementation class EditProfileServlet
 */
public class EditProfileServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public EditProfileServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	try{
		HttpSession session = request.getSession();
		if (session.getAttribute("userId")!=null){
			User user = new UserService().getUserById((Integer.parseInt(session.getAttribute("userId").toString())));
			request.setAttribute("firstName", user.getFirstName());
			request.setAttribute("lastName", user.getLastName());
			request.setAttribute("email", user.getEmail());
			request.getRequestDispatcher("pages/editProfile.jsp").forward(request, response);;
		} else {
			throw new WrongInputDataException();
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
		try {
		UserService service = new UserService();
		HttpSession session = request.getSession();
		User user = service.getUserById((Integer)session.getAttribute("userId"));

			if (request.getParameter("email").equals(""))
				throw new WrongInputDataException();
			if ((service.isEmailFree(request.getParameter("email")))||user.getEmail().equals(request.getParameter("email"))) {

				
				fillUser(request,user);
				service.updateUser(user); 
				request.setAttribute("message", "Profile was updated");
				request.getRequestDispatcher("pages/loginedSuccess.jsp").forward(request, response);
				
			} else {
				request.setAttribute("firstName",
						request.getParameter("firstName"));
				request.setAttribute("lastName",
						request.getParameter("lastName"));
				request.setAttribute("email", request.getParameter("email"));
				request.setAttribute("message",
						"Email isn't free, try another.");
				request.getRequestDispatcher("pages/editProfile.jsp").forward(
						request, response);
			}
		} catch (WrongInputDataException e) {
			request.setAttribute("firstName", request.getParameter("firstName"));
			request.setAttribute("lastName", request.getParameter("lastName"));
			request.setAttribute("email", request.getParameter("email"));
			request.setAttribute("message", "Wrong input data");
			request.getRequestDispatcher("pages/editProfile.jsp").forward(
					request, response);
		} catch (Exception e) {			
			e.printStackTrace();
			response.sendError(500);
		}
	}
	private User fillUser(HttpServletRequest request, User user)
			throws NoSuchAlgorithmException, WrongInputDataException {
		if (!request.getParameter("firstName").equals("")
				&& !request.getParameter("lastName").equals("")
				&& !request.getParameter("email").equals("")) {
			user.setFirstName(request.getParameter("firstName"));
			user.setLastName(request.getParameter("lastName"));
			user.setEmail(request.getParameter("email"));
		} else
			throw new WrongInputDataException();
		return user;
	}

}
