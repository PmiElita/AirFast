package com.pylypchak.airfast.servlet;

import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.util.Random;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.pylypchak.airfast.exception.WrongInputDataException;
import com.pylypchak.airfast.model.User;
import com.pylypchak.airfast.service.UserService;
import com.pylypchak.airfast.util.Hasher;
import com.pylypchak.airfast.util.Sender;

/**
 * Servlet implementation class RegistrationServlet
 */
public class RegistrationServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public RegistrationServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		request.getRequestDispatcher("pages/registration.jsp").forward(request,
				response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		UserService service = new UserService();
		try {
			if (request.getParameter("email").equals(""))
				throw new WrongInputDataException();
			if (!request.getParameter("password").equals(request.getParameter("confirm"))){
				throw new WrongInputDataException();
			}
			if (service.isEmailFree(request.getParameter("email"))) {
				User user = fillUser(request);
				
				service.saveUser(user);
				
				user = service.getUserByEmail(user.getEmail());
				String confirmCode = Hasher.hashing(stringGenerator()+user.getId());
				user.setConfirmCode(confirmCode);
				service.updateUser(user);
				sendConfirm(user);
				request.setAttribute("message", "We sent a letter to your email for complete registration");
			    request.getRequestDispatcher("pages/success.jsp").forward(request, response);
			} else {
				request.setAttribute("firstName",
						request.getParameter("firstName"));
				request.setAttribute("lastName",
						request.getParameter("lastName"));
				request.setAttribute("email", request.getParameter("email"));
				request.setAttribute("message",
						"Email isn't free, try another.");
				request.getRequestDispatcher("pages/registration.jsp").forward(
						request, response);
			}
		} catch (WrongInputDataException e) {
			request.setAttribute("firstName", request.getParameter("firstName"));
			request.setAttribute("lastName", request.getParameter("lastName"));
			request.setAttribute("email", request.getParameter("email"));
			request.setAttribute("message", "Wrong input data");
			request.getRequestDispatcher("pages/registration.jsp").forward(
					request, response);
		} catch (Exception e) {
			e.printStackTrace();
			response.sendError(500);
		}
	}

	private void sendConfirm(User user) {
		Sender sender = new Sender();
	    String subject ="Confirm registration";
        String link = "http://localhost:8080/LowCostAirlines/confirmRegistration/"+user.getConfirmCode();
        String text ="<h1>Hello "+user.getFirstName()+", to complete the registration, click on the link </h1> "+link;
       	
		sender.send(subject, text, user.getEmail());
		
	}

	private User fillUser(HttpServletRequest request)
			throws NoSuchAlgorithmException, WrongInputDataException {
		User result = new User();
		if (!request.getParameter("firstName").equals("")
				&& !request.getParameter("lastName").equals("")
				&& !request.getParameter("email").equals("")
				&& !request.getParameter("password").equals("")) {
			result.setFirstName(request.getParameter("firstName"));
			result.setLastName(request.getParameter("lastName"));
			result.setEmail(request.getParameter("email"));
			result.setPassword(Hasher.hashing(request.getParameter("password")));
		} else
			throw new WrongInputDataException();
		return result;
	}
	
	public String stringGenerator() {
		StringBuilder result = new StringBuilder();
		Random random = new Random();
		while (result.length() < 11) {
			int index = (random.nextInt() / 123);
			if ((index >= 48 && index <= 57) || (index >= 65 && index <= 90)
					|| (index >= 97 && index <= 122)) {
				result.append((char) index);
			}
		}
		return result.toString();
	}

}
