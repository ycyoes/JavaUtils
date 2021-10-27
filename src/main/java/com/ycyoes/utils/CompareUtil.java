package com.ycyoes.utils;


import java.beans.PropertyDescriptor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.Arrays;

/**
 *  对象属性对比工具类
 * @param <T>
 */
public class CompareUtil<T> {

    private final T original;
    private final T current;
    private final String[] excludeFields;

    public CompareUtil(Builder<T> builder) {
        this.excludeFields = builder.excludeFields;
        this.original = builder.original;
        this.current = builder.current;
    }

    public Status compare() {
        if (this.original == null) {
            return Status.NEW;
        }
        boolean isEqual = true;
        try {
            Class clazz = this.original.getClass();
            Field[] fields = clazz.getDeclaredFields();

            for (Field field : fields) {
                if (excludeFields != null && Arrays.asList(excludeFields).contains(field.getName())) {
                    continue;
                }
                Method getMethod = null;
                try {
                    PropertyDescriptor pd = new PropertyDescriptor(field.getName(), clazz);
                    getMethod = pd.getReadMethod();
                } catch (Exception e) {
                    //跳过无函数访问的属性
                }
                if (getMethod == null) {
                    continue;
                }
                Object o1 = getMethod.invoke(this.original);
                Object o2 = getMethod.invoke(this.current);
                String s1 = o1 == null ? "" : o1.toString();
                String s2 = o2 == null ? "" : o2.toString();

                if (!s1.equals(s2)) {
                    isEqual = false;
                    System.out.println("不一样的属性：" + field.getName() + " origin属性值：[" + s1 + "]"+ "   current属性值：[" + s2 + "]");
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return isEqual ? Status.NO_CHANGE : Status.CHANGE;
    }

    public static class Builder<T> {
        private T original;
        private T current;
        private String[] excludeFields;

        public Builder<T> original(T original) {
            this.original = original;
            return this;
        }

        public Builder<T> current(T current) {
            this.current = current;
            return this;
        }

        public Builder<T> excludeFields(String[] excludeFields) {
            this.excludeFields = excludeFields;
            return this;
        }

        public CompareUtil<T> build() {
            return new CompareUtil<T>(this);
        }
    }


    public enum Status {
        /**
         * 标记新数据
         */
        NEW,
        /**
         * 标记变更数据
         */
        CHANGE,
        /**
         * 标记没有变更
         */
        NO_CHANGE,
    }

}
