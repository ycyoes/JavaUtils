package com.ycyoes.test.functional.finterface;

/**
 *
 * 抛异常接口
 *
 * @auth ycyoes
 * @since 2021-11-24
 */
@FunctionalInterface
public interface ThrowExceptionFunction {

    /**
     * 抛出异常信息
     *
     * @param message   异常信息
     */
    void throwMessage(String message);
}
