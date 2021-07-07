package com.ycyoes.test.designpattern.singleton;

public class Client {
    public static void main(String[] args) {
        Singleton.getInstance();
    }

    static class Singleton {
        private static final Singleton instance = new Singleton();
        private Singleton() {}
        public static Singleton getInstance() {
            return instance;
        }

    }
}


