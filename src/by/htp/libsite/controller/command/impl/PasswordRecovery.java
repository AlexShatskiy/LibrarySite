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

public class PasswordRecovery implements Command {
	private static final Logger log = LogManager.getRootLogger();

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String email;
		String page;

		email = request.getParameter(PageParameter.EMAIL);

		ServiceFactory factory = ServiceFactory.getInstance();
		UserService userService = factory.getUserService();

		try {
			
			if (userService.sendPassword(email)){
				request.setAttribute(PageSetAttribute.MESSAGE, "password send");
				page = PageLibrary.INDEX;
			} else if (userService.hasEmail(email)){
				request.setAttribute(PageSetAttribute.ERROR_MESSAGE, "sorry, we will send later");
				page = PageLibrary.INDEX;
			} else {
				request.setAttribute(PageSetAttribute.ERROR_MESSAGE, "There is no such email");
				page = PageLibrary.PASSWORD_RECOVERY;
			}

		} catch (ServiceException e) {
			log.error("ServiceException in PasswordRecovery");
			page = PageLibrary.INDEX;
			request.setAttribute(PageSetAttribute.ERROR_MESSAGE, "sorry fail");
		} catch (ServiceExceptionInvalidParameter e) {
			log.error("ServiceExceptionInvalidParameter in PasswordRecovery");
			page = PageLibrary.PASSWORD_RECOVERY;
			request.setAttribute(PageSetAttribute.ERROR_MESSAGE, "Invalid Parameter");
		}
		RequestDispatcher dispatcher = request.getRequestDispatcher(page);
		dispatcher.forward(request, response);
	}
}
