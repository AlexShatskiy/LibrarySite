package by.htp.libsite.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import by.htp.libsite.controller.command.Command;
import by.htp.libsite.controller.exception.ControllerException;
import by.htp.libsite.dao.connection.ConnectionPool;
import by.htp.libsite.dao.exception.ConnectionPoolException;

public class Controller extends HttpServlet {

	private static final CommandProvider provider = new CommandProvider();

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String commandName = req.getParameter("command");

		Command command = provider.getCommand(commandName);

		command.execute(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String commandName = req.getParameter("command");

		Command command = provider.getCommand(commandName);

		command.execute(req, resp);
	}

	@Override
	public void destroy() {
		ConnectionPool connectionPool = ConnectionPool.getInstance();
		try {
			connectionPool.destroyConnectionPool();
		} catch (ConnectionPoolException e) {

		}
	}

	@Override
	public void init() throws ServletException {
		ConnectionPool connectionPool = ConnectionPool.getInstance();
		try {
			connectionPool.initPoolData();
		} catch (ConnectionPoolException e) {
			System.out.println("ConnectionPoolException");
		}
	}
}
