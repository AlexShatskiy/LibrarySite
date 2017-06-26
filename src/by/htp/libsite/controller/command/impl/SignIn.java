package by.htp.libsite.controller.command.impl;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import by.htp.libsite.domain.User;
import by.htp.libsite.controller.PageLibrary;
import by.htp.libsite.controller.command.Command;
import by.htp.libsite.service.ServiceFactory;
import by.htp.libsite.service.UserService;
import by.htp.libsite.service.exception.ServiceException;

public class SignIn implements Command{

	@Override
	public void execute(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String email;
		String password;
		String page;
		
		email = req.getParameter("email");
		password = req.getParameter("password");
		
		ServiceFactory factory = ServiceFactory.getInstance(); 
		UserService userService = factory.getUserService();
		
		User user;
		try {
			user = userService.signIn(email, password);
		} catch (ServiceException e) {
			user = null;
		}
		
		if(user != null){
			page = PageLibrary.INDEX;
			req.setAttribute("name", "ѕривет " + user.getName());
		} else{
			page = PageLibrary.INDEX;
			req.setAttribute("errorMessage", "не правильный логин или пароль");
		}
		
		RequestDispatcher dispatcher = req.getRequestDispatcher(page);
		dispatcher.forward(req, resp);	
	}
}
