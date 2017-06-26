package by.htp.libsite.service.impl;

import by.htp.libsite.dao.UserDAO;
import by.htp.libsite.dao.exception.ConnectionPoolException;
import by.htp.libsite.dao.factory.DAOFactory;
import by.htp.libsite.domain.User;
import by.htp.libsite.service.UserService;
import by.htp.libsite.service.exception.ServiceException;

public class UserServiceImpl implements UserService {

	@Override
	public User signIn(String email, String password) throws ServiceException {
		
		User user = null;
		
		if(!ParameterValidator.isEmailValid(email) || !ParameterValidator.isPasswordValid(password)){
			throw new ServiceException("Not valid parameters in UserServiceImpl");
		}
		
		DAOFactory factory = DAOFactory.getInstance();
		UserDAO userDAO = factory.getUserDAO();
		
		try {
			user = userDAO.signIn(email, password);
		} catch (ConnectionPoolException e) {
			throw new ServiceException("fail in UserServiceImpl", e);
		}
		return user;
	}

}
