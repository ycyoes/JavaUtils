package com.ycyoes.demos.basic.reflection;

import java.lang.reflect.Method;
import java.util.Arrays;

/**
 * 获取方法签名
 * @author issuser
 *
 */
public class MethodSignature {
	public static void main(String[] args) throws NoSuchMethodException, SecurityException {
		Method[] methods = MethodSignature.class.getDeclaredMethods();
		System.out.println(Arrays.asList(methods));
		Method noParam = MethodSignature.class.getDeclaredMethod("test");
		String noParamSignature = getSignature(noParam);
		System.out.println("无参: " + noParamSignature);
		Method method = MethodSignature.class.getDeclaredMethod("test", java.lang.String.class);
		String withParamSignature = getSignature(method);
		System.out.println(withParamSignature);
	}
	
	private static String getSignature(Method method) {
	    StringBuilder sb = new StringBuilder();
	    Class<?> returnType = method.getReturnType();
	    if (returnType != null) {
	      sb.append(returnType.getName()).append('#');
	    }
	    sb.append(method.getName());
	    Class<?>[] parameters = method.getParameterTypes();
	    for (int i = 0; i < parameters.length; i++) {
	      sb.append(i == 0 ? ':' : ',').append(parameters[i].getName());
	    }
	    return sb.toString();
	  }
	
	private void test() {
		System.out.println("without params");
	}
	
	private void test(String name) {
		System.out.println(name);
	}
}
