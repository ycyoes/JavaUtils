package com.ycyoes.sources.mybatis.reflection;

import com.ycyoes.demos.basic.reflection.RichType;

import java.beans.IntrospectionException;
import java.beans.PropertyDescriptor;
import java.lang.reflect.Method;

public class PropertyDescriptorsTest {
    public static void main(String[] args) throws IntrospectionException {
        PropertyDescriptor propertyDescriptor = new PropertyDescriptor("richField", RichType.class);
        Method readMethod = propertyDescriptor.getReadMethod();
        System.out.println(readMethod.getName());
        Method writeMethod = propertyDescriptor.getWriteMethod();
        System.out.println(writeMethod.getName());
    }
}
