package com.ycyoes.demos.geetime.concurrent.practise.chapter43;

@FunctionalInterface
public interface TxnRunnable {
    void run(Txn txn);
}
