package by.htp.libsite.controller.command.impl;

//utf-8
import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import by.htp.libsite.domain.User;
import by.htp.libsite.controller.PageLibrary;
import by.htp.libsite.controller.PageParameter;
import by.htp.libsite.controller.PageSetAttribute;
import by.htp.libsite.controller.SessionAttribute;
import by.htp.libsite.controller.command.Command;
import by.htp.libsite.service.ServiceFactory;
import by.htp.libsite.service.UserService;
import by.htp.libsite.service.exception.ServiceException;
import by.htp.libsite.service.exception.ServiceExceptionInvalidParameter;

public class SignIn implements Command {
	private static final Logger log = LogManager.getRootLogger();
	private static final String ADMIN_ROLE = "ADMIN";

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
			if (user == null) {
				page = PageLibrary.INDEX;

				if (userService.hasEmail(email)) {
					request.setAttribute(PageSetAttribute.ERROR_MESSAGE, "Wrong password " + email);
				} else {
					request.setAttribute(PageSetAttribute.ERROR_MESSAGE, "No user " + email);
				}

			} else {
				request.setAttribute(PageSetAttribute.WELCOME, "Hello " + user.getNickname());

				HttpSession session = request.getSession(true);
				session.setAttribute(SessionAttribute.USER_ID, user.getUser_id());
				session.setAttribute(SessionAttribute.ROLE, user.getRole());
				session.setAttribute(SessionAttribute.NICKNAME, user.getNickname());

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
