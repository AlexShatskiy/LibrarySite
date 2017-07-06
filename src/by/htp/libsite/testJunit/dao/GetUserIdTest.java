package by.htp.libsite.testJunit.dao;

import org.junit.Test;

import by.htp.libsite.dao.connection.ConnectionPool;
import by.htp.libsite.dao.exception.ConnectionPoolException;
import by.htp.libsite.dao.impl.SQLUserDAO;

public class GetUserIdTest {
	private static final String EMAIL = "admin@gmail.com";
	
	@Test
	public void hasEmailTest() throws ConnectionPoolException{
		ConnectionPool connectionPool = ConnectionPool.getInstance();
		connectionPool.initPoolData();
		
		SQLUserDAO sqlUserDAO = new SQLUserDAO();
		int expected = 1;
		int actual = sqlUserDAO.getUser_id(EMAIL);
		
		org.junit.Assert.assertEquals(expected, actual);
		
		connectionPool.destroyConnectionPool();
	}
}
