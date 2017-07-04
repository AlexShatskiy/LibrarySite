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

public class ReadBook implements Command {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String ADMIN_ROLE = "ADMIN";
		Integer user_id;
		String role;

		String title;
		String author;
		String content;
		String genre;

		Book book;
		String page;

		HttpSession session = request.getSession(true);
		user_id = (Integer) session.getAttribute(SessionAttribute.USER_ID);
		role = (String) session.getAttribute(SessionAttribute.ROLE);

		if (user_id == null || role == null) {
			page = PageLibrary.INDEX;
			request.setAttribute(PageSetAttribute.ERROR_MESSAGE, "Please log in");
		} else {
			
			if (ADMIN_ROLE.equals(role)) {
				page = PageLibrary.ADMIN_PROFILE;
			} else {
				page = PageLibrary.USER_PROFILE;
			}

			title = request.getParameter(PageParameter.TITLE);
			author = request.getParameter(PageParameter.AUTHOR);
			content = request.getParameter(PageParameter.CONTENT);
			genre = request.getParameter(PageParameter.GENRE);

			book = new Book(title, author, content, genre);
			request.setAttribute(PageSetAttribute.BOOK, book);
		}
		RequestDispatcher dispatcher = request.getRequestDispatcher(page);
		dispatcher.forward(request, response);
	}
}
