package com.ycyoes.test.reflection;

import java.lang.reflect.Constructor;
import java.lang.reflect.Method;
import java.lang.reflect.Type;

import com.ycyoes.demos.basic.reflection.RichType;
import com.ycyoes.test.entity.Student1;
import com.ycyoes.test.entity.User;
import org.apache.ibatis.reflection.TypeParameterResolver;

public class TypeTest {
	public static void main(String[] args) throws NoSuchMethodException {
		RichType richType = getInstance(RichType.class);
		System.out.println(richType.getRichProperty());
		//null可直接转为类类型
		RichType rType = (RichType)null;
		System.out.println(rType);

		Method method = null;
		try {
			method = Class.forName("com.ycyoes.demos.basic.reflection.RichType").getMethod("setRichType", new Class[]{RichType.class});
			Class cls = method.getParameterTypes()[0];
			System.out.println(cls);


		} catch (NoSuchMethodException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}

		//泛型类型
		//使用TypeParameterResolver分析User类中getInfo方法输出结果的具体类型
		Type type = TypeParameterResolver.resolveReturnType(User.class.getMethod("getInfo"),User.class);
		System.out.println("User类中getInfo方法的输出结果类型：\n" + type);

		Type type2 = TypeParameterResolver.resolveReturnType(User.class.getMethod("getInfo"), Student1.class);
		System.out.println("Student类中getInfo方法的输出结果类型: \n" + type2);

		Method method1 = User.class.getMethod("getInfo");
		System.out.println(method1.getDeclaringClass());

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
//			获得空参的构造方法
			Constructor<?> c = cls.getConstructor();
			return (RichType)c.newInstance();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	
	
}
