package com.pylypchak.airfast.servlet;

import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.util.Random;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.apache.log4j.xml.DOMConfigurator;

import com.pylypchak.airfast.exception.WrongInputDataException;
import com.pylypchak.airfast.model.ForgottenPassword;
import com.pylypchak.airfast.model.User;
import com.pylypchak.airfast.service.UserService;
import com.pylypchak.airfast.util.Hasher;
import com.pylypchak.airfast.util.Sender;

/**
 * Servlet implementation class AdminRegistrationServlet
 */
public class AdminRegistrationServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public AdminRegistrationServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		request.getRequestDispatcher("pages/adminRegistration.jsp").forward(
				request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		UserService service = new UserService();
		try {
			if (request.getParameter("email").equals("")) {
				throw new WrongInputDataException();
			}

			if (service.isEmailFree(request.getParameter("email"))) {
				User user = fillUser(request);
				user.setRole("ROLE_ADMIN");
				user.setIsEnabled(true);
				String password = stringGenerator();
				user.setPassword(Hasher.hashing(password));
				service.saveUser(user);
				sendEmail(password, user);
				request.setAttribute("message", "Adminnistrator was register");
				request.getRequestDispatcher("pages/success.jsp").forward(request, response);
			} else {
				request.setAttribute("firstName",
						request.getParameter("firstName"));
				request.setAttribute("lastName",
						request.getParameter("lastName"));
				request.setAttribute("email", request.getParameter("email"));
				request.setAttribute("message",
						"Email isn't free, try another.");
				request.getRequestDispatcher("pages/adminRegistration.jsp").forward(
						request, response);
			}
		} catch (WrongInputDataException e) {
			request.setAttribute("firstName", request.getParameter("firstName"));
			request.setAttribute("lastName", request.getParameter("lastName"));
			request.setAttribute("email", request.getParameter("email"));
			request.setAttribute("message", "Wrong input data");
			request.getRequestDispatcher("pages/adminRegistration.jsp").forward(
					request, response);
		} catch (Exception e) {
			e.printStackTrace();
			response.sendError(500);
		}
	}

	void sendEmail(String password, User user) {
		Sender sender = new Sender();
		String subject = "AirFast Password";
		String text = "<h3>Hello "
				+ user.getFirstName()
				+ ", Here is your adminstrator login data</h3> <br><h4> Email :"
				+ user.getEmail() + "</h4><br><h4> Password :"
				+ password + "</h4>";

		sender.send(subject, text, user.getEmail());
	}

	private User fillUser(HttpServletRequest request)
			throws NoSuchAlgorithmException, WrongInputDataException {
		User result = new User();
		if (!request.getParameter("firstName").equals("")
				&& !request.getParameter("lastName").equals("")
				&& !request.getParameter("email").equals("")) {
			result.setFirstName(request.getParameter("firstName"));
			result.setLastName(request.getParameter("lastName"));
			result.setEmail(request.getParameter("email"));

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
