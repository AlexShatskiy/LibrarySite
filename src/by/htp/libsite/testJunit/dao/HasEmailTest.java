package by.htp.libsite.testJunit.dao;

import org.junit.Test;

import by.htp.libsite.dao.connection.ConnectionPool;
import by.htp.libsite.dao.exception.ConnectionPoolException;
import by.htp.libsite.dao.impl.SQLUserDAO;

public class HasEmailTest {
	
	private static final String EMAIL = "admin@gmail.com";
	
	@Test
	public void hasEmailTest() throws ConnectionPoolException{
		ConnectionPool connectionPool = ConnectionPool.getInstance();
		connectionPool.initPoolData();
		
		SQLUserDAO sqlUserDAO = new SQLUserDAO();
		boolean actual = sqlUserDAO.hasEmail(EMAIL);
		
		org.junit.Assert.assertFalse(!actual);
		connectionPool.destroyConnectionPool();
	}
}

