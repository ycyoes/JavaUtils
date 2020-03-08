package com.ycyoes.demos.basic.reflection.access;

import java.lang.reflect.ReflectPermission;

/**
 * Reflect 访问权限控制
 * 
 * @since 2020-03-08
 * @author ycyoes
 *
 */
public class ControlAccess {
	public static void main(String[] args) {
		System.out.println(canControlMemberAccessible());
	}
	
	/**
	 * Checks whether can control member accessible.
	 *
	 * 判断，是否可以修改可访问性
	 *
	 * @return If can control member accessible, it return {@literal true}
	 * @since 3.5.0
	 */
	public static boolean canControlMemberAccessible() {
		try {
			SecurityManager securityManager = System.getSecurityManager();
			if (null != securityManager) {
				securityManager.checkPermission(new ReflectPermission("suppressAccessChecks"));
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}
		return true;
	}
	
}
