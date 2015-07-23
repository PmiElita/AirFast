package com.pylypchak.airfast.servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.pylypchak.airfast.model.User;
import com.pylypchak.airfast.service.UserService;

/**
 * Servlet implementation class ShowAdminsServlet
 */
public class ShowAdminsServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public ShowAdminsServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		try {
			List<User> admins = new UserService().getAllAdmins();
			request.setAttribute("admins", admins);
			request.getRequestDispatcher("pages/showAdmins.jsp").forward(
					request, response);
		} catch (Exception e) {
			e.printStackTrace();
			response.sendError(500);
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		try {
			Integer adminId = Integer.parseInt(request.getParameter("adminId"));
			UserService service = new UserService();
			User admin = service.getUserById(adminId);
			if (admin.getIsEnabled()) {
				admin.setIsEnabled(false);
			} else {
				admin.setIsEnabled(true);
			}
			service.updateUser(admin);
			doGet(request, response);
		} catch (Exception e) {
			e.printStackTrace();
			response.sendError(500);
		}
	}

}
