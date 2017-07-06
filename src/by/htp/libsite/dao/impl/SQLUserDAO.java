package by.htp.libsite.dao.impl;

//utf-8
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import by.htp.libsite.dao.UserDAO;
import by.htp.libsite.dao.connection.ConnectionPool;
import by.htp.libsite.dao.exception.ConnectionPoolException;
import by.htp.libsite.domain.User;

public class SQLUserDAO implements UserDAO {
	private static final Logger log = LogManager.getRootLogger();
	
	private static final String SIGN_IN = "SELECT user_id, email, nickname, role FROM user WHERE email = ? AND password = ?"; 
	private static final String CHECK_IN = "INSERT INTO user (email, password, nickname, role) VALUES (?, ?, ?, ?);"; 
	private static final String GET_PASSWORD = "SELECT password FROM user WHERE email = ?"; 
	private static final String HAS_EMAIL = "SELECT user_id FROM user WHERE email = ?"; 
	private static final String HAS_NICKNAME = "SELECT user_id FROM user WHERE nickname = ?"; 
	private static final String GET_USER_ID = "SELECT user_id FROM user WHERE email = ?";
	private static final String GET_ALL_USER = "SELECT user_id, email, nickname, role FROM user";
	
	@Override
	public User signIn(String email, String password) throws ConnectionPoolException {
		User user = null;

		int user_idSQL = 0;
		String emailSQL = null;
		String nicknameSQL = null;
		String roleSQL = null;

		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;

		ConnectionPool pool = ConnectionPool.getInstance();
		connection = pool.takeConnection();
		try {
			preparedStatement = connection.prepareStatement(SIGN_IN);
			preparedStatement.setString(1, email);
			preparedStatement.setString(2, password);
			resultSet = preparedStatement.executeQuery();

			while (resultSet.next()) {
				user_idSQL = resultSet.getInt(1);
				emailSQL = resultSet.getString(2);
				nicknameSQL = resultSet.getString(3);
				roleSQL = resultSet.getString(4);
			}

			if (emailSQL != null && nicknameSQL != null && roleSQL != null) {
				user = new User(user_idSQL, emailSQL, nicknameSQL, roleSQL);
			}

		} catch (SQLException e) {
			throw new ConnectionPoolException("SQLException in signIn", e);
		} finally {
			if (resultSet != null) {
				try {
					resultSet.close();
				} catch (SQLException e) {
					throw new ConnectionPoolException("rs.close() in signIn", e);
				}
			}
			if (preparedStatement != null) {
				try {
					preparedStatement.close();
				} catch (SQLException e) {
					throw new ConnectionPoolException("st.close() in signIn", e);
				}
			}
			if (connection != null) {
				try {
					connection.close();
				} catch (SQLException e) {
					throw new ConnectionPoolException("con.close() in signIn", e);
				}
			}
		}
		return user;
	}

	@Override
	public User checkIn(String email, String password, String nickname) throws ConnectionPoolException {
		User user = null;
		final String ROLE = "user";
		int user_id = 0;

		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;

		ConnectionPool pool = ConnectionPool.getInstance();
		connection = pool.takeConnection();
		try {
			preparedStatement = connection.prepareStatement(CHECK_IN);

			preparedStatement.setString(1, email);
			preparedStatement.setString(2, password);
			preparedStatement.setString(3, nickname);
			preparedStatement.setString(4, ROLE);

			preparedStatement.executeUpdate();

			user_id = getUser_id(email);
			user = new User(user_id, email, nickname, ROLE);
		} catch (com.mysql.jdbc.exceptions.jdbc4.MySQLIntegrityConstraintViolationException e) {
			user = null;
			// log
		} catch (SQLException e) {
			throw new ConnectionPoolException("SQLException in checkIn", e);
		} finally {
			if (resultSet != null) {
				try {
					resultSet.close();
				} catch (SQLException e) {
					throw new ConnectionPoolException("rs.close() in checkIn", e);
				}
			}
			if (preparedStatement != null) {
				try {
					preparedStatement.close();
				} catch (SQLException e) {
					throw new ConnectionPoolException("st.close() in checkIn", e);
				}
			}
			if (connection != null) {
				try {
					connection.close();
				} catch (SQLException e) {
					throw new ConnectionPoolException("con.close() in checkIn", e);
				}
			}
		}
		return user;
	}

	@Override
	public String getPassword(String email) throws ConnectionPoolException {

		String passwordSQL = null;

		Connection connection = null;
		PreparedStatement ps = null;
		ResultSet resultSet = null;

		ConnectionPool pool = ConnectionPool.getInstance();
		connection = pool.takeConnection();
		try {
			ps = connection.prepareStatement(GET_PASSWORD);
			ps.setString(1, email);
			resultSet = ps.executeQuery();

			while (resultSet.next()) {
				passwordSQL = resultSet.getString(1);
			}

		} catch (SQLException e) {
			throw new ConnectionPoolException("SQLException in getPassword", e);
		} finally {
			if (resultSet != null) {
				try {
					resultSet.close();
				} catch (SQLException e) {
					throw new ConnectionPoolException("rs.close() in getPassword", e);
				}
			}
			if (ps != null) {
				try {
					ps.close();
				} catch (SQLException e) {
					throw new ConnectionPoolException("st.close() in getPassword", e);
				}
			}
			if (connection != null) {
				try {
					connection.close();
				} catch (SQLException e) {
					throw new ConnectionPoolException("con.close() in getPassword", e);
				}
			}
		}
		return passwordSQL;
	}

	@Override
	public boolean hasEmail(String email) throws ConnectionPoolException {

		boolean isHasEmail = false;

		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;

		ConnectionPool pool = ConnectionPool.getInstance();
		connection = pool.takeConnection();
		try {
			preparedStatement = connection.prepareStatement(HAS_EMAIL);
			preparedStatement.setString(1, email);
			resultSet = preparedStatement.executeQuery();

			if (resultSet.next()) {
				isHasEmail = true;
			}

		} catch (SQLException e) {
			throw new ConnectionPoolException("SQLException in hasEmail", e);
		} finally {
			if (resultSet != null) {
				try {
					resultSet.close();
				} catch (SQLException e) {
					throw new ConnectionPoolException("rs.close() in hasEmail", e);
				}
			}
			if (preparedStatement != null) {
				try {
					preparedStatement.close();
				} catch (SQLException e) {
					throw new ConnectionPoolException("st.close() in hasEmail", e);
				}
			}
			if (connection != null) {
				try {
					connection.close();
				} catch (SQLException e) {
					throw new ConnectionPoolException("con.close() in hasEmail", e);
				}
			}
		}
		return isHasEmail;
	}

	@Override
	public boolean hasNickname(String nickname) throws ConnectionPoolException {

		boolean isHasNickname = false;

		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;

		ConnectionPool pool = ConnectionPool.getInstance();
		connection = pool.takeConnection();
		try {
			preparedStatement = connection.prepareStatement(HAS_NICKNAME);
			preparedStatement.setString(1, nickname);
			resultSet = preparedStatement.executeQuery();

			if (resultSet.next()) {
				isHasNickname = true;
			}

		} catch (SQLException e) {
			throw new ConnectionPoolException("SQLException in hasNickname", e);
		} finally {
			if (resultSet != null) {
				try {
					resultSet.close();
				} catch (SQLException e) {
					throw new ConnectionPoolException("rs.close() in hasNickname", e);
				}
			}
			if (preparedStatement != null) {
				try {
					preparedStatement.close();
				} catch (SQLException e) {
					throw new ConnectionPoolException("st.close() in hasNickname", e);
				}
			}
			if (connection != null) {
				try {
					connection.close();
				} catch (SQLException e) {
					throw new ConnectionPoolException("con.close() in hasNickname", e);
				}
			}
		}
		return isHasNickname;
	}

	@Override
	public int getUser_id(String email) throws ConnectionPoolException {

		int user_idSQL = 0;

		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;

		ConnectionPool pool = ConnectionPool.getInstance();
		connection = pool.takeConnection();
		try {
			preparedStatement = connection.prepareStatement(GET_USER_ID);
			preparedStatement.setString(1, email);
			resultSet = preparedStatement.executeQuery();

			while (resultSet.next()) {
				user_idSQL = resultSet.getInt(1);
			}

		} catch (SQLException e) {
			throw new ConnectionPoolException("SQLException in getUser_id", e);
		} finally {
			if (resultSet != null) {
				try {
					resultSet.close();
				} catch (SQLException e) {
					throw new ConnectionPoolException("rs.close() in getUser_id", e);
				}
			}
			if (preparedStatement != null) {
				try {
					preparedStatement.close();
				} catch (SQLException e) {
					throw new ConnectionPoolException("st.close() in getUser_id", e);
				}
			}
			if (connection != null) {
				try {
					connection.close();
				} catch (SQLException e) {
					throw new ConnectionPoolException("con.close() in getUser_id", e);
				}
			}
		}
		return user_idSQL;
	}

	@Override
	public ArrayList<User> getAllUser() throws ConnectionPoolException {

		int user_id;
		String email;
		String nickname;
		String role;

		ArrayList<User> users = new ArrayList<>();
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;

		ConnectionPool pool = ConnectionPool.getInstance();
		connection = pool.takeConnection();
		try {
			preparedStatement = connection.prepareStatement(GET_ALL_USER);
			resultSet = preparedStatement.executeQuery();

			while (resultSet.next()) {
				user_id = resultSet.getInt(1);
				email = resultSet.getString(2);
				nickname = resultSet.getString(3);
				role = resultSet.getString(4);

				User user = new User(user_id, email, nickname, role);
				users.add(user);
			}
		} catch (SQLException e) {
			throw new ConnectionPoolException("SQLException in getUser_id", e);
		} finally {
			if (resultSet != null) {
				try {
					resultSet.close();
				} catch (SQLException e) {
					throw new ConnectionPoolException("rs.close() in getUser_id", e);
				}
			}
			if (preparedStatement != null) {
				try {
					preparedStatement.close();
				} catch (SQLException e) {
					throw new ConnectionPoolException("st.close() in getUser_id", e);
				}
			}
			if (connection != null) {
				try {
					connection.close();
				} catch (SQLException e) {
					throw new ConnectionPoolException("con.close() in getUser_id", e);
				}
			}
		}
		return users;
	}
}
