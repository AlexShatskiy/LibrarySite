package by.htp.libsite.service;
import by.htp.libsite.service.impl.BookServiceImpl;
//utf-8
import by.htp.libsite.service.impl.UserServiceImpl;

public class ServiceFactory {
	private final static ServiceFactory instance = new ServiceFactory();
	
	private UserService userService = new UserServiceImpl();
	private BookService bookService = new BookServiceImpl();
	
	public static ServiceFactory getInstance(){
		return instance;
	}
	
	public UserService getUserService(){
		return userService;
	}
	
	public BookService getBookService(){
		return bookService;
	}
}
