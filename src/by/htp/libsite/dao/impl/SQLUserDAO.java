package by.htp.libsite.dao.impl;

//utf-8
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import by.htp.libsite.dao.UserDAO;
import by.htp.libsite.dao.connection.ConnectionPool;
import by.htp.libsite.dao.exception.ConnectionPoolException;
import by.htp.libsite.domain.User;

public class SQLUserDAO implements UserDAO {

	@Override
	public User signIn(String email, String password) throws ConnectionPoolException {
		User user = null;
		String SQL = "SELECT user_id, email, nickname, role FROM user WHERE email = ? AND password = ?";

		int user_idSQL = 0;
		String emailSQL = null;
		String nicknameSQL = null;
		String roleSQL = null;

		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;

		ConnectionPool pool = ConnectionPool.getInstance();
		con = pool.takeConnection();
		try {
			ps = con.prepareStatement(SQL);
			ps.setString(1, email);
			ps.setString(2, password);
			rs = ps.executeQuery();

			while (rs.next()) {
				user_idSQL = rs.getInt(1);
				emailSQL = rs.getString(2);
				nicknameSQL = rs.getString(3);
				roleSQL = rs.getString(4);
			}

			if (emailSQL != null && nicknameSQL != null && roleSQL != null) {
				user = new User(user_idSQL, emailSQL, nicknameSQL, roleSQL);
			}

		} catch (SQLException e) {
			throw new ConnectionPoolException("SQLException in signIn", e);
		} finally {
			if (rs != null) {
				try {
					rs.close();
				} catch (SQLException e) {
					throw new ConnectionPoolException("rs.close() in signIn", e);
				}
			}
			if (ps != null) {
				try {
					ps.close();
				} catch (SQLException e) {
					throw new ConnectionPoolException("st.close() in signIn", e);
				}
			}
			if (con != null) {
				try {
					con.close();
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

		String SQL = "INSERT INTO user (email, password, nickname, role) VALUES (?, ?, ?, ?);";

		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;

		ConnectionPool pool = ConnectionPool.getInstance();
		con = pool.takeConnection();
		try {
			ps = con.prepareStatement(SQL);

			ps.setString(1, email);
			ps.setString(2, password);
			ps.setString(3, nickname);
			ps.setString(4, ROLE);

			ps.executeUpdate();

			user_id = getUser_id(email);
			user = new User(user_id, email, nickname, ROLE);
		} catch (com.mysql.jdbc.exceptions.jdbc4.MySQLIntegrityConstraintViolationException e) {
			user = null;
			// log
		} catch (SQLException e) {
			throw new ConnectionPoolException("SQLException in checkIn", e);
		} finally {
			if (rs != null) {
				try {
					rs.close();
				} catch (SQLException e) {
					throw new ConnectionPoolException("rs.close() in checkIn", e);
				}
			}
			if (ps != null) {
				try {
					ps.close();
				} catch (SQLException e) {
					throw new ConnectionPoolException("st.close() in checkIn", e);
				}
			}
			if (con != null) {
				try {
					con.close();
				} catch (SQLException e) {
					throw new ConnectionPoolException("con.close() in checkIn", e);
				}
			}
		}
		return user;
	}

	@Override
	public String getPassword(String email) throws ConnectionPoolException {
		String SQL = "SELECT password FROM user WHERE email = ?";

		String passwordSQL = null;

		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;

		ConnectionPool pool = ConnectionPool.getInstance();
		con = pool.takeConnection();
		try {
			ps = con.prepareStatement(SQL);
			ps.setString(1, email);
			rs = ps.executeQuery();

			while (rs.next()) {
				passwordSQL = rs.getString(1);
			}

		} catch (SQLException e) {
			throw new ConnectionPoolException("SQLException in getPassword", e);
		} finally {
			if (rs != null) {
				try {
					rs.close();
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
			if (con != null) {
				try {
					con.close();
				} catch (SQLException e) {
					throw new ConnectionPoolException("con.close() in getPassword", e);
				}
			}
		}
		return passwordSQL;
	}

	@Override
	public boolean hasEmail(String email) throws ConnectionPoolException {
		String SQL = "SELECT user_id FROM user WHERE email = ?";

		boolean isHasEmail = false;

		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;

		ConnectionPool pool = ConnectionPool.getInstance();
		con = pool.takeConnection();
		try {
			ps = con.prepareStatement(SQL);
			ps.setString(1, email);
			rs = ps.executeQuery();

			if (rs.next()) {
				isHasEmail = true;
			}

		} catch (SQLException e) {
			throw new ConnectionPoolException("SQLException in hasEmail", e);
		} finally {
			if (rs != null) {
				try {
					rs.close();
				} catch (SQLException e) {
					throw new ConnectionPoolException("rs.close() in hasEmail", e);
				}
			}
			if (ps != null) {
				try {
					ps.close();
				} catch (SQLException e) {
					throw new ConnectionPoolException("st.close() in hasEmail", e);
				}
			}
			if (con != null) {
				try {
					con.close();
				} catch (SQLException e) {
					throw new ConnectionPoolException("con.close() in hasEmail", e);
				}
			}
		}
		return isHasEmail;
	}

	@Override
	public boolean hasNickname(String nickname) throws ConnectionPoolException {
		String SQL = "SELECT user_id FROM user WHERE nickname = ?";

		boolean isHasNickname = false;

		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;

		ConnectionPool pool = ConnectionPool.getInstance();
		con = pool.takeConnection();
		try {
			ps = con.prepareStatement(SQL);
			ps.setString(1, nickname);
			rs = ps.executeQuery();

			if (rs.next()) {
				isHasNickname = true;
			}

		} catch (SQLException e) {
			throw new ConnectionPoolException("SQLException in hasNickname", e);
		} finally {
			if (rs != null) {
				try {
					rs.close();
				} catch (SQLException e) {
					throw new ConnectionPoolException("rs.close() in hasNickname", e);
				}
			}
			if (ps != null) {
				try {
					ps.close();
				} catch (SQLException e) {
					throw new ConnectionPoolException("st.close() in hasNickname", e);
				}
			}
			if (con != null) {
				try {
					con.close();
				} catch (SQLException e) {
					throw new ConnectionPoolException("con.close() in hasNickname", e);
				}
			}
		}
		return isHasNickname;
	}

	@Override
	public int getUser_id(String email) throws ConnectionPoolException {
		String SQL = "SELECT user_id FROM user WHERE email = ?";

		int user_idSQL = 0;

		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;

		ConnectionPool pool = ConnectionPool.getInstance();
		con = pool.takeConnection();
		try {
			ps = con.prepareStatement(SQL);
			ps.setString(1, email);
			rs = ps.executeQuery();

			while (rs.next()) {
				user_idSQL = rs.getInt(1);
			}

		} catch (SQLException e) {
			throw new ConnectionPoolException("SQLException in getUser_id", e);
		} finally {
			if (rs != null) {
				try {
					rs.close();
				} catch (SQLException e) {
					throw new ConnectionPoolException("rs.close() in getUser_id", e);
				}
			}
			if (ps != null) {
				try {
					ps.close();
				} catch (SQLException e) {
					throw new ConnectionPoolException("st.close() in getUser_id", e);
				}
			}
			if (con != null) {
				try {
					con.close();
				} catch (SQLException e) {
					throw new ConnectionPoolException("con.close() in getUser_id", e);
				}
			}
		}
		return user_idSQL;
	}
}
