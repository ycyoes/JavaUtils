package com.ycyoes.demos.test.string;

import java.util.Locale;
import java.util.regex.Pattern;

public class StringTest {
	private static Pattern humpPattern = Pattern.compile("[A-Z]");
	private static Pattern linePattern = Pattern.compile("_(\\w)");
	public static void main(String[] args) {
		String propName = "richType";
		System.out.println(propName.toUpperCase(Locale.ENGLISH));
		String fName = "auth_id";
		int index = fName.indexOf("_");

		System.out.println(fName.length());
		System.out.println(fName.substring(0, index));
		System.out.println(fName.substring(index+1, index+2).toUpperCase());
		System.out.println(fName.substring(index+2));
	}

}
