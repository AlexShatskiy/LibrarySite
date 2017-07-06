package by.htp.libsite.controller;
//utf-8
import java.util.HashMap;
import java.util.Map;

import by.htp.libsite.controller.command.Command;
import by.htp.libsite.controller.command.impl.SignIn;
import by.htp.libsite.controller.command.impl.SignOut;
import by.htp.libsite.controller.command.impl.AddBook;
import by.htp.libsite.controller.command.impl.AddingPage;
import by.htp.libsite.controller.command.impl.ChangeBook;
import by.htp.libsite.controller.command.impl.CheckIn;
import by.htp.libsite.controller.command.impl.DeleteBook;
import by.htp.libsite.controller.command.impl.GetAllUser;
import by.htp.libsite.controller.command.impl.GoodAddBook;
import by.htp.libsite.controller.command.impl.PasswordRecovery;
import by.htp.libsite.controller.command.impl.ProfilePage;
import by.htp.libsite.controller.command.impl.ReadBook;
import by.htp.libsite.controller.command.impl.RecoveryPage;
import by.htp.libsite.controller.command.impl.RegistrationPage;
import by.htp.libsite.controller.command.impl.SearchBookAuthor;
import by.htp.libsite.controller.command.impl.SearchBookGenre;
import by.htp.libsite.controller.command.impl.SearchBookTitle;
import by.htp.libsite.controller.command.impl.SearchBookUserId;

public class CommandProvider {

	private Map<CommandName, Command> commands = new HashMap<>();

	public CommandProvider() {
		commands.put(CommandName.SIGN_IN, new SignIn());
		commands.put(CommandName.CHECK_IN, new CheckIn());
		commands.put(CommandName.PASSWORD_RECOVERY, new PasswordRecovery());
		commands.put(CommandName.SEARCH_BOOK_TITLE, new SearchBookTitle());
		commands.put(CommandName.REGISTRATION_PAGE, new RegistrationPage());
		commands.put(CommandName.RECOVERY_PAGE, new RecoveryPage());
		commands.put(CommandName.SIGN_OUT, new SignOut());
		commands.put(CommandName.SEARCH_BOOK_AUTHOR, new SearchBookAuthor());
		commands.put(CommandName.SEARCH_BOOK_GENRE, new SearchBookGenre());
		commands.put(CommandName.READ_BOOK, new ReadBook());
		commands.put(CommandName.SEARCH_BOOK_USER_ID, new SearchBookUserId());
		commands.put(CommandName.DELETE_BOOK, new DeleteBook());
		commands.put(CommandName.CHANGE_BOOK_CONTENT, new ChangeBook());
		commands.put(CommandName.ADDING_PAGE, new AddingPage());
		commands.put(CommandName.ADD_BOOK, new AddBook());
		commands.put(CommandName.GET_ALL_USER, new GetAllUser());
		commands.put(CommandName.PROFILE_PAGE, new ProfilePage());
		commands.put(CommandName.GOOD_ADD_BOOK, new GoodAddBook());
	}

	public Command getCommand(String commandName) {
		commandName = commandName.toUpperCase();
		return commands.get(CommandName.valueOf(commandName));
	}
}
