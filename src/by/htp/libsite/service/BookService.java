package by.htp.libsite.service;

import java.util.ArrayList;
import by.htp.libsite.domain.Book;
import by.htp.libsite.service.exception.ServiceException;
import by.htp.libsite.service.exception.ServiceExceptionInvalidParameter;

public interface BookService {
	Book addBook(Integer user_id, String title, String author, String content, String genre) throws ServiceException, ServiceExceptionInvalidParameter;
	Book deleteBook(Integer user_id, String title, String author, String content, String genre) throws ServiceException, ServiceExceptionInvalidParameter;
	ArrayList<Book> getBookForTitle(String title) throws ServiceException, ServiceExceptionInvalidParameter;
	ArrayList<Book> getBookForAuthor(String author) throws ServiceException, ServiceExceptionInvalidParameter;
	ArrayList<Book> getBookForGenre(String genre) throws ServiceException, ServiceExceptionInvalidParameter;
	Book changeBookContent(Integer user_id, String title, String author, String content, String genre, String newContent) throws ServiceException, ServiceExceptionInvalidParameter;
	ArrayList<Book> getBookForUser_id(Integer user_id) throws ServiceException, ServiceExceptionInvalidParameter;
}
