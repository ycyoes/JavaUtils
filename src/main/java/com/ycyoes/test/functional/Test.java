package com.ycyoes.test.functional;

/**
 *
 * test
 *
 * @auth ycyoes
 * @since 2021-11-24
 */
public class Test {

    @org.junit.Test
    public void isBlankOrNoBlank() {
        VUtils.isBlankOrNoBlank("hello")
                .presentOrElseHandle(System.out::println,
                        () -> {
                            System.out.println("空字符串");
                        });
    }

    @org.junit.Test
    public void isTrueOrFalse() {
        VUtils.isTrueOrFalse(true)
                .trueOrFalse(() -> {
                    System.out.println("------------true----------");
                }, () -> {
                    System.out.println("------------false----------");
                });
    }

    @org.junit.Test
    public void isTrue() {
        VUtils.isTrue(true).throwMessage("throw message");
    }
}
