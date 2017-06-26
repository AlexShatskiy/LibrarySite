package by.htp.libsite.controller;

import java.util.HashMap;
import java.util.Map;

import by.htp.libsite.controller.command.Command;
import by.htp.libsite.controller.command.impl.SignIn;
import by.htp.libsite.controller.command.impl.CheckIn;
import by.htp.libsite.controller.command.impl.ErrorCommand;
import by.htp.libsite.controller.command.impl.PasswordRecovery;
import by.htp.libsite.controller.command.impl.Search;


public class CommandProvider {

	private Map<CommandName, Command> commands = new HashMap<>();

	public CommandProvider() {
		commands.put(CommandName.SIGN_IN, new SignIn());
		commands.put(CommandName.CHECK_IN, new CheckIn());
		commands.put(CommandName.ERROR, new ErrorCommand());
		commands.put(CommandName.PASSWORD_RECOVERY, new PasswordRecovery());
		commands.put(CommandName.SEARCH, new Search());
	}

	public Command getCommand(String commandName) {
		commandName = commandName.toUpperCase();
		return commands.get(CommandName.valueOf(commandName));
	}
}
