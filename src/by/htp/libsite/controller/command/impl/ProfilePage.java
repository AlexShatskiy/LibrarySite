package by.htp.libsite.controller.command.impl;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import by.htp.libsite.controller.PageLibrary;
import by.htp.libsite.controller.PageSetAttribute;
import by.htp.libsite.controller.SessionAttribute;
import by.htp.libsite.controller.command.Command;

public class ProfilePage implements Command {
	private static final String ADMIN_ROLE = "ADMIN";

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String role;
		String page; 
		
		HttpSession session = request.getSession(true);
		role = (String) session.getAttribute(SessionAttribute.ROLE);
	
			if (ADMIN_ROLE.equals(role)) {
				page = PageLibrary.ADMIN_PROFILE;
			} else {
				page = PageLibrary.USER_PROFILE;
			}
		RequestDispatcher dispatcher = request.getRequestDispatcher(page);
		dispatcher.forward(request, response);
	}
}
