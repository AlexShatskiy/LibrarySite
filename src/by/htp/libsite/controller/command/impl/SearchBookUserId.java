package by.htp.libsite.controller.command.impl;

import java.io.IOException;
import java.util.ArrayList;

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
import by.htp.libsite.domain.Book;
import by.htp.libsite.service.BookService;
import by.htp.libsite.service.ServiceFactory;
import by.htp.libsite.service.exception.ServiceException;
import by.htp.libsite.service.exception.ServiceExceptionInvalidParameter;

public class SearchBookUserId implements Command {
	private static final Logger log = LogManager.getRootLogger();
	private static final String ADMIN_ROLE = "ADMIN";

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		ArrayList<Book> books = new ArrayList<>();
		
		Integer user_id;
		String role;
		String page;
		
		HttpSession session = request.getSession(true);
		role = (String) session.getAttribute(SessionAttribute.ROLE);
		
		if (ADMIN_ROLE.equals(role)) {
			page = PageLibrary.ADMIN_PROFILE;
			user_id = Integer.parseInt(request.getParameter(PageParameter.USER_ID));
		} else {
			page = PageLibrary.USER_PROFILE;
			user_id = (Integer) session.getAttribute(SessionAttribute.USER_ID);
		}
		
		ServiceFactory factory = ServiceFactory.getInstance();
		BookService bookService = factory.getBookService();

		try {
			books = bookService.getBookForUser_id(user_id);
			request.setAttribute(PageSetAttribute.BOOKS, books);

		} catch (ServiceException e) {

		} catch (ServiceExceptionInvalidParameter e) {
			request.setAttribute(PageSetAttribute.ERROR_MESSAGE, "Invalid Parameter");
		}

		RequestDispatcher dispatcher = request.getRequestDispatcher(page);
		dispatcher.forward(request, response);
	}
}
