package by.htp.libsite.controller.command.impl;
//utf-8
import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import by.htp.libsite.controller.PageLibrary;
import by.htp.libsite.controller.PageParameter;
import by.htp.libsite.controller.PageSetAttribute;
import by.htp.libsite.controller.SessionAttribute;
import by.htp.libsite.controller.command.Command;
import by.htp.libsite.domain.User;
import by.htp.libsite.service.ServiceFactory;
import by.htp.libsite.service.UserService;
import by.htp.libsite.service.exception.ServiceException;
import by.htp.libsite.service.exception.ServiceExceptionInvalidParameter;

public class CheckIn implements Command {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String ADMIN_ROLE = "ADMIN";
		
		String email;
		String password;
		String nickname;
		
		String page;

		email = request.getParameter(PageParameter.EMAIL);
		password = request.getParameter(PageParameter.PASSWORD);
		nickname = request.getParameter(PageParameter.NICKNAME);

		ServiceFactory factory = ServiceFactory.getInstance();
		UserService userService = factory.getUserService();

		User user;
		try {
			user = userService.checkIn(email, password, nickname);
			if (user == null) {
				page = PageLibrary.REGISTRATION;

				if (userService.hasEmail(email)) {
					request.setAttribute(PageSetAttribute.ERROR_MESSAGE, "There is such a user " + email);
				} else if(userService.hasNickname(nickname)){
					request.setAttribute(PageSetAttribute.ERROR_MESSAGE, "There is such a user " + nickname);
				} else {
					request.setAttribute(PageSetAttribute.ERROR_MESSAGE, "Try another email or nickname");
				}

			} else {
				request.setAttribute(PageSetAttribute.WELCOME, "Hello " + user.getNickname());

				HttpSession session = request.getSession(true);
				session.setAttribute(SessionAttribute.USER_ID, user.getUser_id());
				session.setAttribute(SessionAttribute.ROLE, user.getRole());

				if (ADMIN_ROLE.equals(user.getRole())) {
					page = PageLibrary.ADMIN_PROFILE;
				} else {
					page = PageLibrary.USER_PROFILE;
				}
			}
		} catch (ServiceException e) {
			page = PageLibrary.INDEX;
			request.setAttribute(PageSetAttribute.ERROR_MESSAGE, "sorry fail");
		} catch (ServiceExceptionInvalidParameter e) {
			page = PageLibrary.INDEX;
			request.setAttribute(PageSetAttribute.ERROR_MESSAGE, "Invalid Parameter");
		}

		RequestDispatcher dispatcher = request.getRequestDispatcher(page);
		dispatcher.forward(request, response);	
	}
}
