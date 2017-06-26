package by.htp.libsite.service.impl;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

final class ParameterValidator {

	public static boolean isEmailValid(String email) {
		if (email == null) {
			return false;
		} else if (email.isEmpty()) {
			return false;
		}
		String regexp = "^([a-z0-9_-]+\\.)*[a-z0-9_-]+@[a-z0-9_-]+(\\.[a-z0-9_-]+)*\\.[a-z]{2,6}$";

		Pattern pattern = Pattern.compile(regexp);
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
