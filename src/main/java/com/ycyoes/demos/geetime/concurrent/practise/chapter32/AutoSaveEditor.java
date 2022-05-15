package com.ycyoes.demos.geetime.concurrent.practise.chapter32;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

/**
 * Balking模式：再谈线程安全的单例模式
 *
 * @author ycyoes
 * @date 2022-05-15 10:09
 */
public class AutoSaveEditor {
    //文件是否被修改过
    boolean changed = false;
    //定时任务线程池
    ScheduledExecutorService ses = Executors.newSingleThreadScheduledExecutor();
    //定时执行自动保存
    void startAutoSave() {
        ses.scheduleAtFixedRate(() -> {
            autoSave();
        }, 5, 5, TimeUnit.SECONDS);
    }

    //自动存盘操作
    void autoSave() {
        synchronized (this) {
            if (!changed) {
                return;
            }
            changed = false;
        }

//        this.execSave();
    }

    //编辑操作
    void edit() {
        change();
    }

    //改变状态
    void change() {
        synchronized (this) {
            changed = true;
        }
    }

}
