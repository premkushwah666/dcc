package com.dcc.Validation;

import java.util.regex.Pattern;

public class Valide {

	private static final String regex = "^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+\\.com$";
	public static boolean isValideEmail(String email)
	{
		if (email == null) return false;
        return Pattern.matches(regex, email);
	}
}
