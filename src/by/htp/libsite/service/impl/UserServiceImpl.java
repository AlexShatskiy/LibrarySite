package by.htp.libsite.service.impl;

//utf-8
import by.htp.libsite.dao.UserDAO;
import by.htp.libsite.dao.exception.ConnectionPoolException;
import by.htp.libsite.dao.factory.DAOFactory;
import by.htp.libsite.domain.User;
import by.htp.libsite.service.UserService;
import by.htp.libsite.service.exception.ServiceException;
import by.htp.libsite.service.exception.ServiceExceptionInvalidParameter;

public class UserServiceImpl implements UserService {

	@Override
	public User signIn(String email, String password) throws ServiceException, ServiceExceptionInvalidParameter {

		User user = null;

		if (!ParameterValidator.isEmailValid(email) || !ParameterValidator.isPasswordValid(password)) {
			throw new ServiceExceptionInvalidParameter("Not valid parameters in UserServiceImpl");
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

	@Override
	public User checkIn(String email, String password, String nickname) throws ServiceException, ServiceExceptionInvalidParameter {

		User user = null;

		if (!ParameterValidator.isEmailValid(email) || !ParameterValidator.isPasswordValid(password) || !ParameterValidator.isNicknameValid(nickname)) {
			throw new ServiceExceptionInvalidParameter("Not valid parameters in UserServiceImpl");
		}

		DAOFactory factory = DAOFactory.getInstance();
		UserDAO userDAO = factory.getUserDAO();

		try {
			user = userDAO.checkIn(email, password, nickname);
		} catch (ConnectionPoolException e) {
			throw new ServiceException("fail in UserServiceImpl", e);
		}
		return user;
	}

	@Override
	public boolean hasEmail(String email) throws ServiceException, ServiceExceptionInvalidParameter {
		
		boolean isHasEmail = false;

		if (!ParameterValidator.isEmailValid(email)) {
			throw new ServiceExceptionInvalidParameter("Not valid parameters in UserServiceImpl");
		}

		DAOFactory factory = DAOFactory.getInstance();
		UserDAO userDAO = factory.getUserDAO();

		try {
			isHasEmail = userDAO.hasEmail(email);
		} catch (ConnectionPoolException e) {
			throw new ServiceException("fail in UserServiceImpl", e);
		}
		return isHasEmail;
	}

	@Override
	public boolean hasNickname(String nickname) throws ServiceException, ServiceExceptionInvalidParameter {
		
		boolean isHasNickname = false;

		if (!ParameterValidator.isNicknameValid(nickname)) {
			throw new ServiceExceptionInvalidParameter("Not valid parameters in UserServiceImpl");
		}

		DAOFactory factory = DAOFactory.getInstance();
		UserDAO userDAO = factory.getUserDAO();

		try {
			isHasNickname = userDAO.hasNickname(nickname);
		} catch (ConnectionPoolException e) {
			throw new ServiceException("fail in UserServiceImpl", e);
		}
		return isHasNickname;
	}

	@Override
	public boolean sendPassword(String email) throws ServiceException, ServiceExceptionInvalidParameter {
		boolean isSend = false;
		
		if (!ParameterValidator.isEmailValid(email)) {
			throw new ServiceExceptionInvalidParameter("Not valid parameters in UserServiceImpl");
		}
		// TODO 
		
		return isSend;
	}

	@Override
	public int getUser_id(String email) throws ServiceException, ServiceExceptionInvalidParameter {
		
		int user_id = 0;

		if (!ParameterValidator.isEmailValid(email)) {
			throw new ServiceExceptionInvalidParameter("Not valid parameters in UserServiceImpl");
		}

		DAOFactory factory = DAOFactory.getInstance();
		UserDAO userDAO = factory.getUserDAO();

		try {
			user_id = userDAO.getUser_id(email);
		} catch (ConnectionPoolException e) {
			throw new ServiceException("fail in UserServiceImpl", e);
		}
		return user_id;
	}
}
