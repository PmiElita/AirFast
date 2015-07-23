package com.pylypchak.airfast.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.pylypchak.airfast.oauth2.VKOAuth2Details;

/**
 * Servlet implementation class VKLoginServlet
 */
public class VKLoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public VKLoginServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String autorizationString = VKOAuth2Details.userAuthorizationUri
				+ "?client_id=" + VKOAuth2Details.clientId + "&redirect_uri="
				+ VKOAuth2Details.redirectUri + "&scope="
				+ VKOAuth2Details.scope + "&display=" + VKOAuth2Details.display;
		response.sendRedirect(autorizationString);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

}
