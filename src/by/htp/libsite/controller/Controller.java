package by.htp.libsite.controller;
//utf-8
import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import by.htp.libsite.controller.command.Command;
import by.htp.libsite.controller.exception.ControllerException;
import by.htp.libsite.controller.exception.InitDestroyException;
import by.htp.libsite.dao.connection.ConnectionPool;
import by.htp.libsite.dao.exception.ConnectionPoolException;
/**
 * Отслеживает и обробытывает все get и post запросы
 */
public class Controller extends HttpServlet {

	private static final Logger log = LogManager.getRootLogger();
	/**
	 * Вызывается при get и post запросах
	 * 
	 * @param provider CommandProvider в зависимости от имени команды возвращает ссылку на объект команды
	 * 
	 */
	private static final CommandProvider provider = new CommandProvider();
	
	/**
	 * Вызывается при get запросах
	 * 
	 * @param request HttpServletRequest объект передаётся из метода service класса HttpServlet
	 * @param response HttpServletResponse объект передаётся из метода service класса HttpServlet
	 * 
	 */

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String commandName = request.getParameter(PageParameter.COMMAND);

		Command command = provider.getCommand(commandName);
		command.execute(request, response);
	}

	/**
	 * Вызывается при post запросах
	 * 
	 * @param request HttpServletRequest объект передаётся из метода service класса HttpServlet
	 * @param response HttpServletResponse объект передаётся из метода service класса HttpServlet
	 * 
	 */
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		String commandName = request.getParameter(PageParameter.COMMAND);

		Command command = provider.getCommand(commandName);
		command.execute(request, response);
	}
	
	/**
	 * Закрывает все соединения с MySQL пула соединений
	 */

	@Override
	public void destroy() {
		ConnectionPool connectionPool = ConnectionPool.getInstance();
		try {
			connectionPool.destroyConnectionPool();
		} catch (ConnectionPoolException e) {
			log.error("fail in destroy()");
			throw new InitDestroyException("fail in destroy()");	
		}
	}
	
	/**
	 * Создает и инициализирует объект - пул соединиений с базой данных MySQL
	 */

	@Override
	public void init() throws ServletException {
		ConnectionPool connectionPool = ConnectionPool.getInstance();
		try {
			connectionPool.initPoolData();
		} catch (ConnectionPoolException e) {
			log.error("fail in init()");
			throw new InitDestroyException("fail in init()");
		}
	}
}
