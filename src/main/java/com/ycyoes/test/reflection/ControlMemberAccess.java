package com.ycyoes.test.reflection;

import java.lang.reflect.ReflectPermission;

public class ControlMemberAccess {
    public static void main(String[] args) {
        SecurityManager securityManager = System.getSecurityManager();
        System.out.println("securityManager: " + securityManager);
        if (null != securityManager) {
            securityManager.checkPermission(new ReflectPermission("suppressAccessChecks"));
        }
        System.out.println("securityManager2: " + securityManager);
    }
}
