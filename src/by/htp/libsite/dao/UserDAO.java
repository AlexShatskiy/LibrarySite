package by.htp.libsite.dao;

import by.htp.libsite.dao.exception.ConnectionPoolException;
import by.htp.libsite.domain.User;

public interface UserDAO {
	
	User signIn(String email, String password) throws ConnectionPoolException;
	//void checkIn();
	
}
