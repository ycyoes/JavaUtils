package com.ycyoes.demos.geetime.concurrent.practise.chapter43;

public final class VersionedRef<T> {
    final T value;
    final long version;

    //构造方法
    public VersionedRef(T value, long version) {
        this.value = value;
        this.version = version;
    }
}
