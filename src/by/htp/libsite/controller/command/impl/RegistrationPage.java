package by.htp.libsite.controller.command.impl;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import by.htp.libsite.controller.PageLibrary;
import by.htp.libsite.controller.PageSetAttribute;
import by.htp.libsite.controller.command.Command;

public class RegistrationPage implements Command {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		RequestDispatcher dispatcher = request.getRequestDispatcher(PageLibrary.REGISTRATION);
		dispatcher.forward(request, response);
	}
}
