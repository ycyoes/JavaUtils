package com.ycyoes.demos.geetime.concurrent.practise.chapter43;

public class Account {
    //余额
    private TxnRef<Integer> balance;
    //构造方法
    public Account(int balance) {
        this.balance = new TxnRef<Integer>(balance);
    }

    //转账操作
    public void transfer(Account target, int amt) {
        STM.atomic((txn) -> {
            Integer from = balance.getValue(txn);
            balance.setValue(from - amt, txn);
            Integer to = target.balance.getValue(txn);
            target.balance.setValue(to + amt, txn);
            System.out.println(target.balance.getValue(txn));
        });
    }

    public static void main(String[] args) {
        Account account = new Account(2);
        account.transfer(new Account(3), 2);
    }
}
