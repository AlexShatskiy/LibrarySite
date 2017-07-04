package by.htp.libsite.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
//utf-8
import java.util.ArrayList;

import by.htp.libsite.dao.BookDAO;
import by.htp.libsite.dao.connection.ConnectionPool;
import by.htp.libsite.dao.exception.ConnectionPoolException;
import by.htp.libsite.domain.Book;
import by.htp.libsite.domain.User;

public class SQLBookDAO implements BookDAO {

	@Override
	public Book addBook(int user_id, Book book) throws ConnectionPoolException {

		String SQL = "INSERT INTO book (user_id, title, author, content, genre, status) VALUES (?, ?, ?, ?, ?, ?);";

		User user = null;
		String status = "ADDED";

		String title = book.getTitle();
		String author = book.getAuthor();
		String content = book.getContent();
		String genre = book.getGenre();

		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;

		ConnectionPool pool = ConnectionPool.getInstance();
		connection = pool.takeConnection();

		try {
			preparedStatement = connection.prepareStatement(SQL);

			preparedStatement.setInt(1, user_id);
			preparedStatement.setString(2, title);
			preparedStatement.setString(3, author);
			preparedStatement.setString(4, content);
			preparedStatement.setString(5, genre);
			preparedStatement.setString(6, status);

			preparedStatement.executeUpdate();

			book = new Book(title, author, content, genre);

		} catch (com.mysql.jdbc.exceptions.jdbc4.MySQLIntegrityConstraintViolationException e) {
			book = null;
			// log
		} catch (SQLException e) {
			throw new ConnectionPoolException("SQLException in addBook", e);
		} finally {
			if (resultSet != null) {
				try {
					resultSet.close();
				} catch (SQLException e) {
					throw new ConnectionPoolException("rs.close() in addBook", e);
				}
			}
			if (preparedStatement != null) {
				try {
					preparedStatement.close();
				} catch (SQLException e) {
					throw new ConnectionPoolException("st.close() in addBook", e);
				}
			}
			if (connection != null) {
				try {
					connection.close();
				} catch (SQLException e) {
					throw new ConnectionPoolException("con.close() in addBook", e);
				}
			}
		}
		return book;
	}

	@Override
	public Book deleteBook(int user_id, Book book) throws ConnectionPoolException {
		User user = null;

		String title = book.getTitle();
		String author = book.getAuthor();
		String content = book.getContent();
		String genre = book.getGenre();
		
		String SQL = "update book set status = 'REMUVED' where user_id = " + user_id + " and title = '"+title+"' and author = '"+author+"' and content = '"+content+"' and genre = '"+genre+"'";
		Connection connection = null;
		Statement statement = null;
		ResultSet resultSet = null;

		ConnectionPool pool = ConnectionPool.getInstance();
		connection = pool.takeConnection();

		try {
			statement = connection.createStatement();
			statement.executeUpdate(SQL);
			
			book = new Book(title, author, content, genre);
		} catch (com.mysql.jdbc.exceptions.jdbc4.MySQLIntegrityConstraintViolationException e) {
			book = null;
			// log
		} catch (SQLException e) {
			throw new ConnectionPoolException("SQLException in deleteBook", e);
		} finally {
			if (resultSet != null) {
				try {
					resultSet.close();
				} catch (SQLException e) {
					throw new ConnectionPoolException("rs.close() in deleteBook", e);
				}
			}
			if (statement != null) {
				try {
					statement.close();
				} catch (SQLException e) {
					throw new ConnectionPoolException("st.close() in deleteBook", e);
				}
			}
			if (connection != null) {
				try {
					connection.close();
				} catch (SQLException e) {
					throw new ConnectionPoolException("con.close() in deleteBook", e);
				}
			}
		}
		return book;
	}

	@Override
	public ArrayList<Book> getBookForTitle(String title) throws ConnectionPoolException {
		String SQL = "SELECT title, author, content, genre FROM book WHERE title = ? and status = 'ADDED'";

		ArrayList<Book> books = new ArrayList<Book>();

		String titleSQL = null;
		String authorSQL = null;
		String contentSQL = null;
		String genreSQL = null;

		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;

		ConnectionPool pool = ConnectionPool.getInstance();
		connection = pool.takeConnection();

		try {
			preparedStatement = connection.prepareStatement(SQL);
			preparedStatement.setString(1, title);
			resultSet = preparedStatement.executeQuery();

			while (resultSet.next()) {
				titleSQL = resultSet.getString(1);
				authorSQL = resultSet.getString(2);
				contentSQL = resultSet.getString(3);
				genreSQL = resultSet.getString(4);

				books.add(new Book(titleSQL, authorSQL, contentSQL, genreSQL));
			}
		} catch (SQLException e) {
			throw new ConnectionPoolException("SQLException in getBookForName", e);
		} finally {
			if (resultSet != null) {
				try {
					resultSet.close();
				} catch (SQLException e) {
					throw new ConnectionPoolException("rs.close() in getBookForName", e);
				}
			}
			if (preparedStatement != null) {
				try {
					preparedStatement.close();
				} catch (SQLException e) {
					throw new ConnectionPoolException("st.close() in getBookForName", e);
				}
			}
			if (connection != null) {
				try {
					connection.close();
				} catch (SQLException e) {
					throw new ConnectionPoolException("con.close() in getBookForName", e);
				}
			}
		}
		return books;
	}

	@Override
	public ArrayList<Book> getBookForAuthor(String author) throws ConnectionPoolException {
		String SQL = "SELECT title, author, content, genre FROM book WHERE author = ? and status = 'ADDED'";

		ArrayList<Book> books = new ArrayList<Book>();

		String titleSQL = null;
		String authorSQL = null;
		String contentSQL = null;
		String genreSQL = null;

		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;

		ConnectionPool pool = ConnectionPool.getInstance();
		connection = pool.takeConnection();

		try {
			preparedStatement = connection.prepareStatement(SQL);
			preparedStatement.setString(1, author);
			resultSet = preparedStatement.executeQuery();

			while (resultSet.next()) {
				titleSQL = resultSet.getString(1);
				authorSQL = resultSet.getString(2);
				contentSQL = resultSet.getString(3);
				genreSQL = resultSet.getString(4);

				books.add(new Book(titleSQL, authorSQL, contentSQL, genreSQL));
			}
		} catch (SQLException e) {
			throw new ConnectionPoolException("SQLException in getBookForAuthor", e);
		} finally {
			if (resultSet != null) {
				try {
					resultSet.close();
				} catch (SQLException e) {
					throw new ConnectionPoolException("rs.close() in getBookForAuthor", e);
				}
			}
			if (preparedStatement != null) {
				try {
					preparedStatement.close();
				} catch (SQLException e) {
					throw new ConnectionPoolException("st.close() in getBookForAuthor", e);
				}
			}
			if (connection != null) {
				try {
					connection.close();
				} catch (SQLException e) {
					throw new ConnectionPoolException("con.close() in getBookForAuthor", e);
				}
			}
		}
		return books;
	}

	@Override
	public ArrayList<Book> getAllBook() throws ConnectionPoolException {
		String SQL = "SELECT title, author, content, genre FROM book WHERE status = 'ADDED'";

		ArrayList<Book> books = new ArrayList<Book>();

		String titleSQL = null;
		String authorSQL = null;
		String contentSQL = null;
		String genreSQL = null;

		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;

		ConnectionPool pool = ConnectionPool.getInstance();
		connection = pool.takeConnection();

		try {
			preparedStatement = connection.prepareStatement(SQL);
			resultSet = preparedStatement.executeQuery();

			while (resultSet.next()) {
				titleSQL = resultSet.getString(1);
				authorSQL = resultSet.getString(2);
				contentSQL = resultSet.getString(3);
				genreSQL = resultSet.getString(4);

				books.add(new Book(titleSQL, authorSQL, contentSQL, genreSQL));
			}
		} catch (SQLException e) {
			throw new ConnectionPoolException("SQLException in getAllBook", e);
		} finally {
			if (resultSet != null) {
				try {
					resultSet.close();
				} catch (SQLException e) {
					throw new ConnectionPoolException("rs.close() in getAllBook", e);
				}
			}
			if (preparedStatement != null) {
				try {
					preparedStatement.close();
				} catch (SQLException e) {
					throw new ConnectionPoolException("st.close() in getAllBook", e);
				}
			}
			if (connection != null) {
				try {
					connection.close();
				} catch (SQLException e) {
					throw new ConnectionPoolException("con.close() in getAllBook", e);
				}
			}
		}
		return books;
	}

	@Override
	public Book changeBookContent(int user_id, Book book, String newContent) throws ConnectionPoolException {
		User user = null;

		String title = book.getTitle();
		String author = book.getAuthor();
		String content = book.getContent();
		String genre = book.getGenre();
		
		String SQL = "update book set content = '" + newContent + "' where user_id = " + user_id + " and title = '"+title+"' and author = '"+author+"' and content = '"+content+"' and genre = '"+genre+"'";
		
		Connection connection = null;
		Statement statement = null;
		ResultSet resultSet = null;

		ConnectionPool pool = ConnectionPool.getInstance();
		connection = pool.takeConnection();

		try {
			statement = connection.createStatement();
			statement.executeUpdate(SQL);
			
			book = new Book(title, author, content, genre);
		} catch (com.mysql.jdbc.exceptions.jdbc4.MySQLIntegrityConstraintViolationException e) {
			book = null;
			// log
		} catch (SQLException e) {
			throw new ConnectionPoolException("SQLException in deleteBook", e);
		} finally {
			if (resultSet != null) {
				try {
					resultSet.close();
				} catch (SQLException e) {
					throw new ConnectionPoolException("rs.close() in deleteBook", e);
				}
			}
			if (statement != null) {
				try {
					statement.close();
				} catch (SQLException e) {
					throw new ConnectionPoolException("st.close() in deleteBook", e);
				}
			}
			if (connection != null) {
				try {
					connection.close();
				} catch (SQLException e) {
					throw new ConnectionPoolException("con.close() in deleteBook", e);
				}
			}
		}
		return book;
	}

	@Override
	public ArrayList<Book> getBookForGenre(String genre) throws ConnectionPoolException {
		String SQL = "SELECT title, author, content, genre FROM book WHERE genre = ? and status = 'ADDED'";

		ArrayList<Book> books = new ArrayList<Book>();

		String titleSQL = null;
		String authorSQL = null;
		String contentSQL = null;
		String genreSQL = null;

		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;

		ConnectionPool pool = ConnectionPool.getInstance();
		connection = pool.takeConnection();

		try {
			preparedStatement = connection.prepareStatement(SQL);
			preparedStatement.setString(1, genre);
			resultSet = preparedStatement.executeQuery();

			while (resultSet.next()) {
				titleSQL = resultSet.getString(1);
				authorSQL = resultSet.getString(2);
				contentSQL = resultSet.getString(3);
				genreSQL = resultSet.getString(4);

				books.add(new Book(titleSQL, authorSQL, contentSQL, genreSQL));
			}
		} catch (SQLException e) {
			throw new ConnectionPoolException("SQLException in getBookForGenre", e);
		} finally {
			if (resultSet != null) {
				try {
					resultSet.close();
				} catch (SQLException e) {
					throw new ConnectionPoolException("rs.close() in getBookForGenre", e);
				}
			}
			if (preparedStatement != null) {
				try {
					preparedStatement.close();
				} catch (SQLException e) {
					throw new ConnectionPoolException("st.close() in getBookForGenre", e);
				}
			}
			if (connection != null) {
				try {
					connection.close();
				} catch (SQLException e) {
					throw new ConnectionPoolException("con.close() in getBookForGenre", e);
				}
			}
		}
		return books;
	}

	@Override
	public ArrayList<Book> getBookForUser_id(int user_id) throws ConnectionPoolException {
		String SQL = "SELECT title, author, content, genre FROM book WHERE user_id = ? and status = 'ADDED'";

		ArrayList<Book> books = new ArrayList<Book>();

		String titleSQL = null;
		String authorSQL = null;
		String contentSQL = null;
		String genreSQL = null;

		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;

		ConnectionPool pool = ConnectionPool.getInstance();
		connection = pool.takeConnection();

		try {
			preparedStatement = connection.prepareStatement(SQL);
			preparedStatement.setInt(1, user_id);;
			resultSet = preparedStatement.executeQuery();

			while (resultSet.next()) {
				titleSQL = resultSet.getString(1);
				authorSQL = resultSet.getString(2);
				contentSQL = resultSet.getString(3);
				genreSQL = resultSet.getString(4);

				books.add(new Book(titleSQL, authorSQL, contentSQL, genreSQL));
			}
		} catch (SQLException e) {
			throw new ConnectionPoolException("SQLException in getBookForUser_id", e);
		} finally {
			if (resultSet != null) {
				try {
					resultSet.close();
				} catch (SQLException e) {
					throw new ConnectionPoolException("rs.close() in getBookForUser_id", e);
				}
			}
			if (preparedStatement != null) {
				try {
					preparedStatement.close();
				} catch (SQLException e) {
					throw new ConnectionPoolException("st.close() in getBookForUser_id", e);
				}
			}
			if (connection != null) {
				try {
					connection.close();
				} catch (SQLException e) {
					throw new ConnectionPoolException("con.close() in getBookForUser_id", e);
				}
			}
		}
		return books;
	}
}