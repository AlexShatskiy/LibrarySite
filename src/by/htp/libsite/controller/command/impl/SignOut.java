package by.htp.libsite.controller.command.impl;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import by.htp.libsite.controller.PageLibrary;
import by.htp.libsite.controller.SessionAttribute;
import by.htp.libsite.controller.command.Command;

public class SignOut implements Command {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession(true);
		session.removeAttribute(SessionAttribute.USER_ID);
		session.removeAttribute(SessionAttribute.ROLE);
		
		RequestDispatcher dispatcher = request.getRequestDispatcher(PageLibrary.INDEX);
		dispatcher.forward(request, response);
	}
}
