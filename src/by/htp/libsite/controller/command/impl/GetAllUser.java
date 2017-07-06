package by.htp.libsite.controller.command.impl;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import by.htp.libsite.controller.PageLibrary;
import by.htp.libsite.controller.PageSetAttribute;
import by.htp.libsite.controller.command.Command;
import by.htp.libsite.domain.User;
import by.htp.libsite.service.ServiceFactory;
import by.htp.libsite.service.UserService;
import by.htp.libsite.service.exception.ServiceException;

public class GetAllUser implements Command {
	private static final Logger log = LogManager.getRootLogger();

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		ArrayList<User> users = new ArrayList<>();
		String page = PageLibrary.ADMIN_PROFILE;
		
		ServiceFactory factory = ServiceFactory.getInstance();
		UserService userService = factory.getUserService();
		
		try {
			users = userService.getAllUser();
			request.setAttribute(PageSetAttribute.USERS, users);
			
		} catch (ServiceException e) {
			log.error("ServiceException in GetAllUser");
			request.setAttribute(PageSetAttribute.ERROR_MESSAGE, "sorry fail");
			e.printStackTrace();
		}
		RequestDispatcher dispatcher = request.getRequestDispatcher(page);
		dispatcher.forward(request, response);
	}
}
