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

public class ChangeBook implements Command {
	private static final Logger log = LogManager.getRootLogger();
	private static final String ADMIN_ROLE = "ADMIN";
	
	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Integer user_idSession;
		Integer user_idBook;
		String role;

		String title;
		String author;
		String content;
		String genre;
		String newContent;

		Book book = null;
		String page = PageLibrary.INDEX;

		HttpSession session = request.getSession(true);
		user_idSession = (Integer) session.getAttribute(SessionAttribute.USER_ID);
		role = (String) session.getAttribute(SessionAttribute.ROLE);

		ServiceFactory factory = ServiceFactory.getInstance();
		BookService bookService = factory.getBookService();

		user_idBook = Integer.parseInt(request.getParameter(PageParameter.USER_ID));
		title = request.getParameter(PageParameter.TITLE);
		author = request.getParameter(PageParameter.AUTHOR);
		content = request.getParameter(PageParameter.CONTENT);
		genre = request.getParameter(PageParameter.GENRE);
		newContent = request.getParameter(PageParameter.NEW_CONTENT);

		try {
			if (ADMIN_ROLE.equals(role)) {
				page = PageLibrary.ADMIN_PROFILE;
				book = bookService.changeBookContent(user_idBook, title, author, content, genre, newContent);
			} else {
				page = PageLibrary.USER_PROFILE;
				if (user_idSession != user_idBook) {
					System.out.println(user_idSession);
					System.out.println(user_idBook);
					request.setAttribute(PageSetAttribute.ERROR_MESSAGE, "You can not change this book");
				} else {
					book = bookService.changeBookContent(user_idBook, title, author, content, genre, newContent);
				}
			}
			if (book != null) {
				request.setAttribute(PageSetAttribute.MESSAGE, "Book changed");
			}
		} catch (ServiceException e) {
			log.error("ServiceException in ChangeBook");
		} catch (ServiceExceptionInvalidParameter e) {
			log.error("ServiceExceptionInvalidParameter in ChangeBook");
		}
		RequestDispatcher dispatcher = request.getRequestDispatcher(page);
		dispatcher.forward(request, response);	
	}
}
