package com.ycyoes.test.reflection;

import java.lang.reflect.Constructor;
import com.ycyoes.demos.basic.reflection.RichType;

public class TypeTest {
	public static void main(String[] args) {
		RichType richType = getInstance(RichType.class);
		System.out.println(richType.getRichProperty());
	}
	
	/**
	 * 获取类实例
	 * 
	 * @param <T>
	 * @param cls
	 * @return
	 */
	public static <T> RichType getInstance(Class<?> cls) {
		try {
			Constructor<?> c = cls.getConstructor();
			return (RichType)c.newInstance();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	
	
}
