package by.htp.libsite.service;
//utf-8
import by.htp.libsite.service.impl.UserServiceImpl;

public class ServiceFactory {
	private final static ServiceFactory instance = new ServiceFactory();
	
	private UserService userService = new UserServiceImpl();
	
	public UserService getUserService(){
		return userService;
	}
	
	public static ServiceFactory getInstance(){
		return instance;
	}

}
