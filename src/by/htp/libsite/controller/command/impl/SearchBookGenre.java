package by.htp.libsite.controller.command.impl;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import by.htp.libsite.controller.PageLibrary;
import by.htp.libsite.controller.PageParameter;
import by.htp.libsite.controller.PageSetAttribute;
import by.htp.libsite.controller.command.Command;
import by.htp.libsite.domain.Book;
import by.htp.libsite.service.BookService;
import by.htp.libsite.service.ServiceFactory;
import by.htp.libsite.service.exception.ServiceException;
import by.htp.libsite.service.exception.ServiceExceptionInvalidParameter;

public class SearchBookGenre implements Command {
	private static final Logger log = LogManager.getRootLogger();

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		ArrayList<Book> books = new ArrayList<>();
		
		String genre;
		String page = PageLibrary.INDEX;
		
		genre = request.getParameter(PageParameter.SEARCH);
		
		ServiceFactory factory = ServiceFactory.getInstance();
		BookService bookService = factory.getBookService();

		try {
			books = bookService.getBookForGenre(genre);
			request.setAttribute(PageSetAttribute.BOOKS, books);
			
		} catch (ServiceException e) {
			
		} catch (ServiceExceptionInvalidParameter e) {
			request.setAttribute(PageSetAttribute.ERROR_MESSAGE, "Invalid Parameter");
		}
		
		RequestDispatcher dispatcher = request.getRequestDispatcher(page);
		dispatcher.forward(request, response);
	}
}
