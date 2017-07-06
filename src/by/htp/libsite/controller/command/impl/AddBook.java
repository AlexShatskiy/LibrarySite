package by.htp.libsite.controller.command.impl;

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
import by.htp.libsite.domain.Book;
import by.htp.libsite.service.BookService;
import by.htp.libsite.service.ServiceFactory;
import by.htp.libsite.service.exception.ServiceException;
import by.htp.libsite.service.exception.ServiceExceptionInvalidParameter;

public class AddBook implements Command {
	private static final Logger log = LogManager.getRootLogger();
	private static final String ADMIN_ROLE = "ADMIN";
	private static final String URL_GOOD_ADD = "http://localhost:8080/LibrarySite/Controller?command=good_add_book";
	
	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Integer user_id;
		String role;

		String title;
		String author;
		String content;
		String genre;

		Book book;
		String page = PageLibrary.ADDING_BOOK;

		HttpSession session = request.getSession(true);
		user_id = (Integer) session.getAttribute(SessionAttribute.USER_ID);

		ServiceFactory factory = ServiceFactory.getInstance();
		BookService bookService = factory.getBookService();

		title = request.getParameter(PageParameter.TITLE);
		author = request.getParameter(PageParameter.AUTHOR);
		content = request.getParameter(PageParameter.CONTENT);
		genre = request.getParameter(PageParameter.GENRE);
		
		try {

			book = bookService.addBook(user_id, title, author, content, genre);
			if (book != null) {
				response.sendRedirect(URL_GOOD_ADD);
			} else {
				request.setAttribute(PageSetAttribute.ERROR_MESSAGE, "You can not add this book");
				RequestDispatcher dispatcher = request.getRequestDispatcher(page);
				dispatcher.forward(request, response);
			}
		} catch (ServiceException e) {
			log.error("ServiceException in AddBook");
			RequestDispatcher dispatcher = request.getRequestDispatcher(page);
			dispatcher.forward(request, response);
		} catch (ServiceExceptionInvalidParameter e) {
			log.error("ServiceExceptionInvalidParameter in AddBook");
			RequestDispatcher dispatcher = request.getRequestDispatcher(page);
			dispatcher.forward(request, response);
		}
	}
}
