package by.htp.libsite.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
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

		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;

		ConnectionPool pool = ConnectionPool.getInstance();
		con = pool.takeConnection();

		try {
			ps = con.prepareStatement(SQL);

			ps.setInt(1, user_id);
			ps.setString(2, title);
			ps.setString(3, author);
			ps.setString(4, content);
			ps.setString(5, genre);
			ps.setString(6, status);

			ps.executeUpdate();

			book = new Book(title, author, content, genre);

		} catch (com.mysql.jdbc.exceptions.jdbc4.MySQLIntegrityConstraintViolationException e) {
			book = null;
			// log
		} catch (SQLException e) {
			throw new ConnectionPoolException("SQLException in addBook", e);
		} finally {
			if (rs != null) {
				try {
					rs.close();
				} catch (SQLException e) {
					throw new ConnectionPoolException("rs.close() in addBook", e);
				}
			}
			if (ps != null) {
				try {
					ps.close();
				} catch (SQLException e) {
					throw new ConnectionPoolException("st.close() in addBook", e);
				}
			}
			if (con != null) {
				try {
					con.close();
				} catch (SQLException e) {
					throw new ConnectionPoolException("con.close() in addBook", e);
				}
			}
		}
		return book;
	}

	@Override
	public Book deleteBook(int user_id, Book book) throws ConnectionPoolException {
		String SQL = "update book set status = 'REMUVED' where user_id = ? and title = ? and author = ? and content = ? and genre = ?";

		User user = null;

		String title = book.getTitle();
		String author = book.getAuthor();
		String content = book.getContent();
		String genre = book.getGenre();

		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;

		ConnectionPool pool = ConnectionPool.getInstance();
		con = pool.takeConnection();

		try {
			ps = con.prepareStatement(SQL);

			ps.setInt(1, user_id);
			ps.setString(2, title);
			ps.setString(3, author);
			ps.setString(4, content);
			ps.setString(5, genre);

			ps.executeUpdate();

			book = new Book(title, author, content, genre);

		} catch (com.mysql.jdbc.exceptions.jdbc4.MySQLIntegrityConstraintViolationException e) {
			book = null;
			// log
		} catch (SQLException e) {
			throw new ConnectionPoolException("SQLException in deleteBook", e);
		} finally {
			if (rs != null) {
				try {
					rs.close();
				} catch (SQLException e) {
					throw new ConnectionPoolException("rs.close() in deleteBook", e);
				}
			}
			if (ps != null) {
				try {
					ps.close();
				} catch (SQLException e) {
					throw new ConnectionPoolException("st.close() in deleteBook", e);
				}
			}
			if (con != null) {
				try {
					con.close();
				} catch (SQLException e) {
					throw new ConnectionPoolException("con.close() in deleteBook", e);
				}
			}
		}
		return book;
	}

	@Override
	public ArrayList<Book> getBookForName(String name) throws ConnectionPoolException {
		String SQL = "SELECT title, author, content, genre FROM book WHERE name = ?";

		ArrayList<Book> books = new ArrayList<Book>();

		String titleSQL = null;
		String authorSQL = null;
		String contentSQL = null;
		String genreSQL = null;

		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;

		ConnectionPool pool = ConnectionPool.getInstance();
		con = pool.takeConnection();

		try {
			ps = con.prepareStatement(SQL);
			ps.setString(1, name);
			rs = ps.executeQuery();

			while (rs.next()) {
				titleSQL = rs.getString(1);
				authorSQL = rs.getString(2);
				contentSQL = rs.getString(3);
				genreSQL = rs.getString(4);

				books.add(new Book(titleSQL, authorSQL, contentSQL, genreSQL));
			}
		} catch (SQLException e) {
			throw new ConnectionPoolException("SQLException in getBookForName", e);
		} finally {
			if (rs != null) {
				try {
					rs.close();
				} catch (SQLException e) {
					throw new ConnectionPoolException("rs.close() in getBookForName", e);
				}
			}
			if (ps != null) {
				try {
					ps.close();
				} catch (SQLException e) {
					throw new ConnectionPoolException("st.close() in getBookForName", e);
				}
			}
			if (con != null) {
				try {
					con.close();
				} catch (SQLException e) {
					throw new ConnectionPoolException("con.close() in getBookForName", e);
				}
			}
		}
		return books;
	}

	@Override
	public ArrayList<Book> getBookForAuthor(String author) throws ConnectionPoolException {
		String SQL = "SELECT title, author, content, genre FROM book WHERE author = ?";

		ArrayList<Book> books = new ArrayList<Book>();

		String titleSQL = null;
		String authorSQL = null;
		String contentSQL = null;
		String genreSQL = null;

		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;

		ConnectionPool pool = ConnectionPool.getInstance();
		con = pool.takeConnection();

		try {
			ps = con.prepareStatement(SQL);
			ps.setString(1, author);
			rs = ps.executeQuery();

			while (rs.next()) {
				titleSQL = rs.getString(1);
				authorSQL = rs.getString(2);
				contentSQL = rs.getString(3);
				genreSQL = rs.getString(4);

				books.add(new Book(titleSQL, authorSQL, contentSQL, genreSQL));
			}
		} catch (SQLException e) {
			throw new ConnectionPoolException("SQLException in getBookForAuthor", e);
		} finally {
			if (rs != null) {
				try {
					rs.close();
				} catch (SQLException e) {
					throw new ConnectionPoolException("rs.close() in getBookForAuthor", e);
				}
			}
			if (ps != null) {
				try {
					ps.close();
				} catch (SQLException e) {
					throw new ConnectionPoolException("st.close() in getBookForAuthor", e);
				}
			}
			if (con != null) {
				try {
					con.close();
				} catch (SQLException e) {
					throw new ConnectionPoolException("con.close() in getBookForAuthor", e);
				}
			}
		}
		return books;
	}

	@Override
	public ArrayList<Book> getAllBook() throws ConnectionPoolException {
		String SQL = "SELECT title, author, content, genre FROM book";

		ArrayList<Book> books = new ArrayList<Book>();

		String titleSQL = null;
		String authorSQL = null;
		String contentSQL = null;
		String genreSQL = null;

		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;

		ConnectionPool pool = ConnectionPool.getInstance();
		con = pool.takeConnection();

		try {
			ps = con.prepareStatement(SQL);
			rs = ps.executeQuery();

			while (rs.next()) {
				titleSQL = rs.getString(1);
				authorSQL = rs.getString(2);
				contentSQL = rs.getString(3);
				genreSQL = rs.getString(4);

				books.add(new Book(titleSQL, authorSQL, contentSQL, genreSQL));
			}
		} catch (SQLException e) {
			throw new ConnectionPoolException("SQLException in getAllBook", e);
		} finally {
			if (rs != null) {
				try {
					rs.close();
				} catch (SQLException e) {
					throw new ConnectionPoolException("rs.close() in getAllBook", e);
				}
			}
			if (ps != null) {
				try {
					ps.close();
				} catch (SQLException e) {
					throw new ConnectionPoolException("st.close() in getAllBook", e);
				}
			}
			if (con != null) {
				try {
					con.close();
				} catch (SQLException e) {
					throw new ConnectionPoolException("con.close() in getAllBook", e);
				}
			}
		}
		return books;
	}

	@Override
	public Book changeBookContent(int user_id, Book book, String newContent) throws ConnectionPoolException {
		String SQL = "update book set content = ? where user_id = ? and title = ? and author = ? and content = ? and genre = ?";

		User user = null;

		String title = book.getTitle();
		String author = book.getAuthor();
		String content = book.getContent();
		String genre = book.getGenre();

		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;

		ConnectionPool pool = ConnectionPool.getInstance();
		con = pool.takeConnection();

		try {
			ps = con.prepareStatement(SQL);

			ps.setInt(1, user_id);
			ps.setString(2, title);
			ps.setString(3, author);
			ps.setString(4, content);
			ps.setString(5, genre);

			ps.executeUpdate();

			book = new Book(title, author, content, genre);

		} catch (com.mysql.jdbc.exceptions.jdbc4.MySQLIntegrityConstraintViolationException e) {
			book = null;
			// log
		} catch (SQLException e) {
			throw new ConnectionPoolException("SQLException in changeBookContent", e);
		} finally {
			if (rs != null) {
				try {
					rs.close();
				} catch (SQLException e) {
					throw new ConnectionPoolException("rs.close() in changeBookContent", e);
				}
			}
			if (ps != null) {
				try {
					ps.close();
				} catch (SQLException e) {
					throw new ConnectionPoolException("st.close() in changeBookContent", e);
				}
			}
			if (con != null) {
				try {
					con.close();
				} catch (SQLException e) {
					throw new ConnectionPoolException("con.close() in changeBookContent", e);
				}
			}
		}
		return book;
	}

	@Override
	public ArrayList<Book> getBookForGenre(String genre) throws ConnectionPoolException {
		String SQL = "SELECT title, author, content, genre FROM book WHERE genre = ?";

		ArrayList<Book> books = new ArrayList<Book>();

		String titleSQL = null;
		String authorSQL = null;
		String contentSQL = null;
		String genreSQL = null;

		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;

		ConnectionPool pool = ConnectionPool.getInstance();
		con = pool.takeConnection();

		try {
			ps = con.prepareStatement(SQL);
			ps.setString(1, genre);
			rs = ps.executeQuery();

			while (rs.next()) {
				titleSQL = rs.getString(1);
				authorSQL = rs.getString(2);
				contentSQL = rs.getString(3);
				genreSQL = rs.getString(4);

				books.add(new Book(titleSQL, authorSQL, contentSQL, genreSQL));
			}
		} catch (SQLException e) {
			throw new ConnectionPoolException("SQLException in getBookForGenre", e);
		} finally {
			if (rs != null) {
				try {
					rs.close();
				} catch (SQLException e) {
					throw new ConnectionPoolException("rs.close() in getBookForGenre", e);
				}
			}
			if (ps != null) {
				try {
					ps.close();
				} catch (SQLException e) {
					throw new ConnectionPoolException("st.close() in getBookForGenre", e);
				}
			}
			if (con != null) {
				try {
					con.close();
				} catch (SQLException e) {
					throw new ConnectionPoolException("con.close() in getBookForGenre", e);
				}
			}
		}
		return books;
	}
}
