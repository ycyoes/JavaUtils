package com.ycyoes.test.functional;

import com.ycyoes.test.functional.finterface.BranchHandle;
import com.ycyoes.test.functional.finterface.PresentOrElseHandler;
import com.ycyoes.test.functional.finterface.ThrowExceptionFunction;

/**
 *
 * 抛异常接口
 *
 * @auth ycyoes
 * @since 2021-11-24
 */
public class VUtils {

    public static PresentOrElseHandler<?> isBlankOrNoBlank(String str) {
        return (consumer, runnable) -> {
            if (str == null || str.length() == 0) {
                runnable.run();
            } else {
                consumer.accept(str);
            }
        };
    }

    public static ThrowExceptionFunction isTrue(boolean b) {
        return (errorMessage) -> {
            if (b) {
                throw new RuntimeException(errorMessage);
            }
        };
    }

    public static BranchHandle isTrueOrFalse(boolean b) {
        return (trueHandle, falseHandle) -> {
            if (b) {
                trueHandle.run();
            } else {
                falseHandle.run();
            }
        };
    }

}
