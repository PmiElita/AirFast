package com.pylypchak.airfast.servlet;

import java.io.IOException;
import java.util.Random;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.pylypchak.airfast.model.ForgottenPassword;
import com.pylypchak.airfast.model.User;
import com.pylypchak.airfast.service.ForgottenPasswordService;
import com.pylypchak.airfast.service.UserService;
import com.pylypchak.airfast.util.Hasher;
import com.pylypchak.airfast.util.Sender;

/**
 * Servlet implementation class ForgotPasswordServlet
 */
public class ForgotPasswordServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ForgotPasswordServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try{
		ForgottenPasswordService service = new ForgottenPasswordService();
		ForgottenPassword f = new ForgottenPassword();
		f.setUserId(1);
		f=service.saveForgottenPassword(f);
		request.getRequestDispatcher("pages/forgotPassword.jsp").forward(request, response);
		} catch (Exception e){
			e.printStackTrace();
			response.sendError(500);
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try{
			boolean isInputDataGood=true;
			String formEmail=request.getParameter("email");
			UserService service = new UserService();
			if (formEmail!=null&&!service.isEmailFree(formEmail)){
				
				User user=service.getUserByEmail(formEmail);
                ForgottenPasswordService forgottenPasswordService = new ForgottenPasswordService();
                ForgottenPassword forgottenPassword = new ForgottenPassword();
                forgottenPassword.setUserId(user.getId());
                forgottenPassword = forgottenPasswordService.saveForgottenPassword(forgottenPassword);
        		Random random = new Random();
        		String code=Hasher.hashing(new Long(random.nextLong()).toString()+forgottenPassword.getId().toString());
        		forgottenPassword.setCode(code);
        		forgottenPasswordService.updateFrogottenPassword(forgottenPassword);
			    sendEmail(forgottenPassword,user);
			    request.setAttribute("message", "Letter was sent successfully");
			    request.getRequestDispatcher("pages/success.jsp").forward(request, response);

			} else {
				request.setAttribute("email", formEmail);
				request.setAttribute("message", "Wrong email");
				request.getRequestDispatcher("pages/forgotPassword.jsp").forward(request, response);
			}
		}catch (Exception e){
			e.printStackTrace();
			response.sendError(500);
		}
	}
	void sendEmail(ForgottenPassword forgottenPassword , User user) {
         Sender sender = new Sender();
         String subject ="AirFast Forgotten Password";
         String link = "http://localhost:8080/LowCostAirlines/newPassword/"+forgottenPassword.getCode();
         String text ="<h1>Hello "+user.getFirstName()+", Here is a link for seting your new password</h1> "+link;
        		 
         sender.send(subject, text, user.getEmail());
	}

}
