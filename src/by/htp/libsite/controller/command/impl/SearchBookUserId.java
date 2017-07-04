package by.htp.libsite.controller.command.impl;

import java.io.IOException;
import java.util.ArrayList;

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
import by.htp.libsite.domain.Book;
import by.htp.libsite.service.BookService;
import by.htp.libsite.service.ServiceFactory;
import by.htp.libsite.service.exception.ServiceException;
import by.htp.libsite.service.exception.ServiceExceptionInvalidParameter;

public class SearchBookUserId implements Command {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		ArrayList<Book> books = new ArrayList<>();
		String ADMIN_ROLE = "ADMIN";
		
		Integer user_id;
		String role;
		String page;
		
		HttpSession session = request.getSession(true);
		user_id = (Integer) session.getAttribute(SessionAttribute.USER_ID);
		role = (String) session.getAttribute(SessionAttribute.ROLE);
		
		if (ADMIN_ROLE.equals(role)) {
			page = PageLibrary.ADMIN_PROFILE;
		} else {
			page = PageLibrary.USER_PROFILE;
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
