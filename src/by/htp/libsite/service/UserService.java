package by.htp.libsite.service;

import java.util.ArrayList;

import by.htp.libsite.dao.exception.ConnectionPoolException;
//utf-8
import by.htp.libsite.domain.User;
import by.htp.libsite.service.exception.ServiceException;
import by.htp.libsite.service.exception.ServiceExceptionInvalidParameter;

public interface UserService {
	User signIn(String email, String password) throws ServiceException, ServiceExceptionInvalidParameter;
	User checkIn(String email, String password, String nickname) throws ServiceException, ServiceExceptionInvalidParameter;
	boolean hasEmail(String email) throws ServiceException, ServiceExceptionInvalidParameter;
	boolean hasNickname(String nickname) throws ServiceException, ServiceExceptionInvalidParameter;
	boolean sendPassword(String email) throws ServiceException, ServiceExceptionInvalidParameter;
	int getUser_id(String email) throws ServiceException, ServiceExceptionInvalidParameter;
	ArrayList<User> getAllUser() throws ServiceException;
}
