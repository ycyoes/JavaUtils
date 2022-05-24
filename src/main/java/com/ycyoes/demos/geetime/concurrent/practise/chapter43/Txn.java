package com.ycyoes.demos.geetime.concurrent.practise.chapter43;

public interface Txn {
    <T> T get(TxnRef<T> ref);
    <T> void set(TxnRef<T> ref, T value);
}
