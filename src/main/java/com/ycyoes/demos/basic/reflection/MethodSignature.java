package com.ycyoes.demos.basic.reflection;

import java.lang.reflect.Method;

/**
 * 获取方法签名
 * @author issuser
 *
 */
public class MethodSignature {
	public static void main(String[] args) throws NoSuchMethodException, SecurityException {
		Method method = MethodSignature.class.getDeclaredMethod("test");
		String signature = getSignature(method);
		System.out.println(signature);
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
		
	}
}
