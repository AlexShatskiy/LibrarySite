package by.htp.libsite.service;

import by.htp.libsite.domain.User;
import by.htp.libsite.service.exception.ServiceException;

public interface UserService {
	User signIn(String email, String password) throws ServiceException;

}
