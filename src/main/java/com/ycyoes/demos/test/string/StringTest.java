package com.ycyoes.demos.test.string;

import java.io.*;
import java.net.URL;
import java.util.*;
import java.util.jar.JarEntry;
import java.util.jar.JarInputStream;

public class StringTest {

	public static void main(String[] args) {
		String propName = "richType";
		System.out.println(propName.toUpperCase(Locale.ENGLISH));
		String fName = "auth_id";
		int index = fName.indexOf("_");

		System.out.println(fName.length());
		System.out.println(fName.substring(0, index));
		System.out.println(fName.substring(index+1, index+2).toUpperCase());
		System.out.println(fName.substring(index+2));

		String fqn = "com.ycyoes.demos.test.string.StringTest";
		String externalName = fqn.substring(0, fqn.indexOf('.')).replace('/', '.');
		System.out.println(externalName);
		System.out.println(fqn.replace('.', '/'));
	}



}
