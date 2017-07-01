package by.htp.libsite.service.impl;
//utf-8
import java.util.regex.Matcher;
import java.util.regex.Pattern;

final class ParameterValidator {
	
	private static String EMAIL_REGEXP = "^([a-z0-9_-]+\\.)*[a-z0-9_-]+@[a-z0-9_-]+(\\.[a-z0-9_-]+)*\\.[a-z]{2,6}$";

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
}
