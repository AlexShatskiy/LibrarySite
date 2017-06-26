package by.htp.libsite.dao.impl;

import java.sql.Connection;
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

		String emailSQL = null;
		String nameSQL = null;
		String roleSQL = null;

		Connection con = null;
		Statement st = null;
		ResultSet rs = null;

		ConnectionPool pool = ConnectionPool.getInstance();
		con = pool.takeConnection();
		try {
			st = con.createStatement();
			rs = st.executeQuery("SELECT email, name, role " 
			        + "FROM user " 
					+ "WHERE email=" + "'" + email + "'"
					+ "AND password=" + "'" + password + "';");
			
			while (rs.next()) {
				emailSQL = rs.getString(1);
				nameSQL = rs.getString(2);
				roleSQL = rs.getString(3);
			}
			
			if (emailSQL != null && nameSQL != null && roleSQL != null) {
				user = new User(emailSQL, nameSQL, roleSQL);
			}
			
		} catch (SQLException e) {
			throw new ConnectionPoolException("SQLException in signIn", e);
		} finally {
			if (rs != null ){try {
				rs.close();
			} catch (SQLException e) {
				throw new ConnectionPoolException("rs.close() in signIn", e);
			}}
			if (st != null){ try {
				st.close();
			} catch (SQLException e) {
				throw new ConnectionPoolException("st.close() in signIn", e);
			}}
			if (con != null) {try {
				con.close();
			} catch (SQLException e) {
				throw new ConnectionPoolException("con.close() in signIn", e);
			}}
		}
		return user;
	}
}
