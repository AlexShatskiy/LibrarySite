package by.htp.libsite.dao;
//utf-8
import by.htp.libsite.dao.exception.ConnectionPoolException;
import by.htp.libsite.domain.User;

public interface UserDAO {
	
	User signIn(String email, String password) throws ConnectionPoolException;
	User checkIn(String email, String password, String nickname) throws ConnectionPoolException;
	boolean hasEmail(String email) throws ConnectionPoolException;
	boolean hasNickname(String nickname) throws ConnectionPoolException;
	String getPassword(String email) throws ConnectionPoolException;
	int getUser_id(String email) throws ConnectionPoolException;
}
