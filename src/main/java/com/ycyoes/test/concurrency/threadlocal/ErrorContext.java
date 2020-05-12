package com.ycyoes.test.concurrency.threadlocal;

import java.util.ArrayList;
import java.util.List;

public class ErrorContext {
    private List messages = new ArrayList<>();
    private static final ThreadLocal<ErrorContext> LOCAL = new ThreadLocal();
    private ErrorContext() {}

    public static ErrorContext getInstance() {
        ErrorContext context = LOCAL.get();
        if (context == null) {
            context = new ErrorContext();
            LOCAL.set(context);
        }
        return context;
    }

    public ErrorContext message(String message) {
        this.messages.add(message);
        return this;
    }

    public ErrorContext reset() {
        messages.clear();
        LOCAL.remove();
        return this;
    }

    public String toString() {
        StringBuilder description = new StringBuilder();
        for (Object msg : messages) {
            description.append("### ");
            description.append(msg);
            description.append("\n");
        }
        return description.toString();
    }

    public static void main(String[] args) {
        ErrorContext ctxMain = ErrorContext.getInstance();
        ctxMain.message("Main Thread Message");
        System.out.println(ctxMain);
        ctxMain.reset();

        Runnable task1 = () -> {
            ErrorContext ctxTask1 = ErrorContext.getInstance();
            ctxTask1.message("Task1 Thread Message");
            System.out.println(ctxTask1);
            ctxTask1.reset();
        };

        Runnable task2 = () -> {
            ErrorContext ctxTask2 = ErrorContext.getInstance();
            ctxTask2.message("Task2 Thread Message");
            System.out.println(ctxTask2);
            ctxTask2.reset();
        };

        new Thread(task1).start();
        new Thread(task2).start();
    }
}
