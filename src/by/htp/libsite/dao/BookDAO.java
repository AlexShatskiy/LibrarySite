package by.htp.libsite.dao;

import java.util.ArrayList;

import by.htp.libsite.dao.exception.ConnectionPoolException;
import by.htp.libsite.domain.Book;

//utf-8
public interface BookDAO {
	
	Book addBook(Book book) throws ConnectionPoolException;
	Book deleteBook(Book book) throws ConnectionPoolException;
	ArrayList<Book> getBookForTitle(String title) throws ConnectionPoolException;
	ArrayList<Book> getBookForAuthor(String author) throws ConnectionPoolException;
	ArrayList<Book> getBookForGenre(String genre) throws ConnectionPoolException;
	Book changeBookContent(Book book, String newContent) throws ConnectionPoolException;
	ArrayList<Book> getBookForUser_id(int user_id) throws ConnectionPoolException;
}
