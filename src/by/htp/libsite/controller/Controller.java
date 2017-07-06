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

public class Controller extends HttpServlet {

	private static final Logger log = LogManager.getRootLogger();
	private static final CommandProvider provider = new CommandProvider();

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String commandName = request.getParameter(PageParameter.COMMAND);

		Command command = provider.getCommand(commandName);
		command.execute(request, response);
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String commandName = request.getParameter(PageParameter.COMMAND);

		Command command = provider.getCommand(commandName);
		command.execute(request, response);
	}

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
