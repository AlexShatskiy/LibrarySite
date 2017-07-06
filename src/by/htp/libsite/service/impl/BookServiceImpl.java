package by.htp.libsite.service.impl;

import java.util.ArrayList;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import by.htp.libsite.dao.BookDAO;
import by.htp.libsite.dao.exception.ConnectionPoolException;
import by.htp.libsite.dao.factory.DAOFactory;
import by.htp.libsite.domain.Book;
import by.htp.libsite.service.BookService;
import by.htp.libsite.service.exception.ServiceException;
import by.htp.libsite.service.exception.ServiceExceptionInvalidParameter;

public class BookServiceImpl implements BookService {
	private static final Logger log = LogManager.getRootLogger();

	@Override
	public Book addBook(Integer user_id, String title, String author, String content, String genre)
			throws ServiceException, ServiceExceptionInvalidParameter {
		Book book = null;
		Book resultBook = null;

		if (!ParameterValidator.isUser_idValid(user_id) || !ParameterValidator.isBookTextValid(title)
				|| !ParameterValidator.isBookTextValid(author) || !ParameterValidator.isBookTextValid(content)
				|| !ParameterValidator.isBookTextValid(genre)) {
			throw new ServiceExceptionInvalidParameter("Not valid parameters in BookServiceImpl");
		}
		book = new Book(user_id, title, author, content, genre);

		DAOFactory factory = DAOFactory.getInstance();
		BookDAO bookDAO = factory.getBookDAO();

		try {
			resultBook = bookDAO.addBook(book);
		} catch (ConnectionPoolException e) {
			throw new ServiceException("fail in BookServiceImpl", e);
		}
		return resultBook;
	}

	@Override
	public Book deleteBook(Integer user_id, String title, String author, String content, String genre)
			throws ServiceException, ServiceExceptionInvalidParameter {
		Book book = null;
		Book resultBook = null;

		if (!ParameterValidator.isUser_idValid(user_id) || !ParameterValidator.isBookTextValid(title)
				|| !ParameterValidator.isBookTextValid(author) || !ParameterValidator.isBookTextValid(content)
				|| !ParameterValidator.isBookTextValid(genre)) {
			throw new ServiceExceptionInvalidParameter("Not valid parameters in BookServiceImpl");
		}
		book = new Book(user_id, title, author, content, genre);

		DAOFactory factory = DAOFactory.getInstance();
		BookDAO bookDAO = factory.getBookDAO();

		try {
			resultBook = bookDAO.deleteBook(book);
		} catch (ConnectionPoolException e) {
			throw new ServiceException("fail in BookServiceImpl", e);
		}
		return resultBook;
	}

	@Override
	public ArrayList<Book> getBookForTitle(String title) throws ServiceException, ServiceExceptionInvalidParameter {
		ArrayList<Book> books = new ArrayList<>();

		if (!ParameterValidator.isBookTextValid(title)) {
			throw new ServiceExceptionInvalidParameter("Not valid parameters in BookServiceImpl");
		}

		DAOFactory factory = DAOFactory.getInstance();
		BookDAO bookDAO = factory.getBookDAO();

		try {
			books = bookDAO.getBookForTitle(title);
		} catch (ConnectionPoolException e) {
			throw new ServiceException("fail in BookServiceImpl", e);
		}
		return books;
	}

	@Override
	public ArrayList<Book> getBookForAuthor(String author) throws ServiceException, ServiceExceptionInvalidParameter {
		ArrayList<Book> books = new ArrayList<>();

		if (!ParameterValidator.isBookTextValid(author)) {
			throw new ServiceExceptionInvalidParameter("Not valid parameters in BookServiceImpl");
		}

		DAOFactory factory = DAOFactory.getInstance();
		BookDAO bookDAO = factory.getBookDAO();

		try {
			books = bookDAO.getBookForAuthor(author);
		} catch (ConnectionPoolException e) {
			throw new ServiceException("fail in BookServiceImpl", e);
		}
		return books;
	}

	@Override
	public ArrayList<Book> getBookForGenre(String genre) throws ServiceException, ServiceExceptionInvalidParameter {
		ArrayList<Book> books = new ArrayList<>();

		if (!ParameterValidator.isBookTextValid(genre)) {
			throw new ServiceExceptionInvalidParameter("Not valid parameters in BookServiceImpl");
		}

		DAOFactory factory = DAOFactory.getInstance();
		BookDAO bookDAO = factory.getBookDAO();

		try {
			books = bookDAO.getBookForGenre(genre);
		} catch (ConnectionPoolException e) {
			throw new ServiceException("fail in BookServiceImpl", e);
		}
		return books;
	}

	@Override
	public Book changeBookContent(Integer user_id, String title, String author, String content, String genre,
			String newContent) throws ServiceException, ServiceExceptionInvalidParameter {
		Book book = null;
		Book resultBook = null;

		if (!ParameterValidator.isUser_idValid(user_id) || !ParameterValidator.isBookTextValid(title)
				|| !ParameterValidator.isBookTextValid(author) || !ParameterValidator.isBookTextValid(content)
				|| !ParameterValidator.isBookTextValid(genre)|| !ParameterValidator.isBookTextValid(newContent)) {
			throw new ServiceExceptionInvalidParameter("Not valid parameters in BookServiceImpl");
		}
		book = new Book(user_id, title, author, content, genre);

		DAOFactory factory = DAOFactory.getInstance();
		BookDAO bookDAO = factory.getBookDAO();

		try {
			resultBook = bookDAO.changeBookContent(book, newContent);
		} catch (ConnectionPoolException e) {
			throw new ServiceException("fail in BookServiceImpl", e);
		}
		return resultBook;
	}

	@Override
	public ArrayList<Book> getBookForUser_id(Integer user_id) throws ServiceException, ServiceExceptionInvalidParameter {
		ArrayList<Book> books = new ArrayList<>();
		int user_idInt = 0;
		
		if (!ParameterValidator.isUser_idValid(user_id)) {
			throw new ServiceExceptionInvalidParameter("Not valid parameters in BookServiceImpl");
		}

		DAOFactory factory = DAOFactory.getInstance();
		BookDAO bookDAO = factory.getBookDAO();

		try {
			books = bookDAO.getBookForUser_id(user_id);
		} catch (ConnectionPoolException e) {
			throw new ServiceException("fail in BookServiceImpl", e);
		}
		return books;
	}
}
