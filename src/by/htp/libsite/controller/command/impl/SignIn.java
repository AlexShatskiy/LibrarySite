package by.htp.libsite.controller.command.impl;
//utf-8
import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import by.htp.libsite.domain.User;
import by.htp.libsite.controller.PageLibrary;
import by.htp.libsite.controller.PageParameter;
import by.htp.libsite.controller.command.Command;
import by.htp.libsite.service.ServiceFactory;
import by.htp.libsite.service.UserService;
import by.htp.libsite.service.exception.ServiceException;

public class SignIn implements Command{

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String email;
		String password;
		String page;
		
		email = request.getParameter(PageParameter.EMAIL);
		password = request.getParameter(PageParameter.PASSWORD);
		
		ServiceFactory factory = ServiceFactory.getInstance(); 
		UserService userService = factory.getUserService();
		
		User user;
		try {
			user = userService.signIn(email, password);
			page = PageLibrary.INDEX;
			request.setAttribute("name", "������ " + user.getNickname());
		} catch (ServiceException e) {
			page = PageLibrary.INDEX;
			request.setAttribute("errorMessage", "�� ���������� ����� ��� ������");
		}
		
		RequestDispatcher dispatcher = request.getRequestDispatcher(page);
		dispatcher.forward(request, response);	
	}
}
