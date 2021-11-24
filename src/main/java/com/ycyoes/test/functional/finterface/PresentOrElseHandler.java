package com.ycyoes.test.functional.finterface;

import java.util.function.Consumer;

/**
 *
 * 空值与非空值分支处理
 *
 * @auth ycyoes
 * @since 2021-11-24
 */
public interface PresentOrElseHandler<T extends Object> {

    /**
     * 值为空时执行其他的操作
     *
     * @param action    值不为空时，执行消费操作
     * @param emptyAction   值为空时，执行的操作
     */
    void presentOrElseHandle(Consumer<? super T> action, Runnable emptyAction);
}
