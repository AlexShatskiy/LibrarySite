package by.htp.libsite.dao.factory;
import by.htp.libsite.dao.BookDAO;
//utf-8
import by.htp.libsite.dao.UserDAO;
import by.htp.libsite.dao.impl.SQLBookDAO;
import by.htp.libsite.dao.impl.SQLUserDAO;

public class DAOFactory {
	private static final DAOFactory instance = new DAOFactory();
	
	private final UserDAO sqlUserImpl = new SQLUserDAO();
	private final BookDAO sqlBookImpl = new SQLBookDAO();
	
	private DAOFactory(){}
	
	public static DAOFactory getInstance(){
		return instance;
	}
	
	public UserDAO getUserDAO(){
		return sqlUserImpl;
	}
	
	public BookDAO getBookDAO(){
		return sqlBookImpl;
	}
}
