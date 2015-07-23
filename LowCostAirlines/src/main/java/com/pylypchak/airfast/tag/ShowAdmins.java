package com.pylypchak.airfast.tag;

import java.io.IOException;
import java.util.List;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.SimpleTagSupport;

import com.pylypchak.airfast.model.User;
import com.pylypchak.airfast.util.Messager;

public class ShowAdmins extends SimpleTagSupport {
	private List<User> admins;
    String locale ;
	public void setLocale(String locale) {
		this.locale = locale;
	}

	public void setAdmins(List<User> admins) {
		this.admins = admins;
	}

	public void doTag() throws JspException, IOException {

		if (admins != null) {
		
			JspWriter out = getJspContext().getOut();
			try {
				for (User u : admins) {
					out.println("<div class = \"flight\">"
							+ " <div class=\"leftFounded\">");
					out.println("<p>"+Messager.getMessage("registration.firstname", locale)+": ");
					out.println("<span >" + u.getFirstName() + "</span></p>");
					out.println("<p>"+Messager.getMessage("registration.lastname", locale)+": <span>" + u.getLastName()
							+ "</span></p>");
					out.println(" <p>"+Messager.getMessage("email", locale)+": <span>");
					out.println(u.getEmail() + "</span> </p>");

					out.println("<form action=\"showAdmins\" method=\"post\">");
					out.println("<input type=\"text\" name=\"adminId\" value=\""
							+ u.getId() + "\" hidden>");
				
					if (u.getIsEnabled()){
						out.println("<button type=\"submit\" >"+Messager.getMessage("admin.block", locale)+"</button>");
					} else {
						out.println("<button type=\"submit\" >"+Messager.getMessage("admin.unblock", locale)+"</button>");
					}
					out.println("</form>");
					out.println("</div>");
					out.println("</div>");
				}
			} catch (Exception e) {
				e.printStackTrace();
			}

		} else {
			JspWriter out = getJspContext().getOut();
			out.println(Messager.getMessage("admin.noadmin", locale));
		}

	}

}
