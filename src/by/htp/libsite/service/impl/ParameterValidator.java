package by.htp.libsite.service.impl;
//utf-8
import java.util.regex.Matcher;
import java.util.regex.Pattern;

final class ParameterValidator {
	
	private static String EMAIL_REGEXP = "^([a-z0-9_-]+\\.)*[a-z0-9_-]+@[a-z0-9_-]+(\\.[a-z0-9_-]+)*\\.[a-z]{2,6}$";
	//private static String USER_ID_REGEXP = "[1-9][0-9]*"; old

	public static boolean isEmailValid(String email) {
		if (email == null) {
			return false;
		} else if (email.isEmpty()) {
			return false;
		}

		Pattern pattern = Pattern.compile(EMAIL_REGEXP);
		Matcher matcher = pattern.matcher(email);

		return matcher.matches();
	}

	public static boolean isPasswordValid(String password) {
		if (password == null) {
			return false;
		} else if (password.isEmpty()) {
			return false;
		}
		return true;
	}
	
	public static boolean isNicknameValid(String nickname) {
		if (nickname == null) {
			return false;
		} else if (nickname.isEmpty()) {
			return false;
		}
		return true;
	}
	
	public static boolean isUser_idValid(Integer user_id) {
		if (user_id == null) {
			return false;
		} else if (user_id <= 0) {
			return false;
		}
		return true;
	}
	public static boolean isBookTextValid(String text) {
		if (text == null) {
			return false;
		} else if (text.isEmpty()) {
			return false;
		}
		return true;
	}
}
