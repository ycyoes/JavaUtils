package com.ycyoes.demos.geetime.concurrent.practise.chapter36;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.concurrent.*;

public class Logger {
    //任务队列
    BlockingQueue<LogMsg> bq = new ArrayBlockingQueue<LogMsg>(5);
    //flush批量
    static final int batchSize = 500;
    //只需要一个线程写日志
    ExecutorService es = Executors.newFixedThreadPool(1);
    //启动写日志线程
    void start() throws IOException {
        File file = File.createTempFile("foo", ".log");
        final FileWriter writer = new FileWriter(file);
        this.es.execute(() -> {
            try {
                //未刷盘日志数量
                int curIdx = 0;
                long preFT = System.currentTimeMillis();
                while (true) {
                    LogMsg log = bq.poll(5, TimeUnit.SECONDS);
                    //写日志
                    if (log != null) {
                        writer.write(log.toString());
                        ++curIdx;
                    }
                    //如果不存在未刷盘数据，则无需刷盘
                    if (curIdx <= 0) {
                        continue;
                    }

                    //根据规则刷盘
                    if (log != null && log.level == LEVEL.ERROR || curIdx == batchSize || System.currentTimeMillis() - preFT > 5000) {
                        writer.flush();
                        curIdx = 0;
                        preFT = System.currentTimeMillis();
                    }
                }

            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                try {
                    writer.flush();
                    writer.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }

            }

        });
    }

    void info(String msg) throws InterruptedException {
        bq.put(new LogMsg(LEVEL.INFO, msg));
    }

    void error(String msg) throws InterruptedException {
        bq.put(new LogMsg(LEVEL.ERROR, msg));
    }

}

enum LEVEL {
    INFO, ERROR
}

class LogMsg {
    LEVEL level;
    String msg;
    LogMsg(LEVEL lvl, String msg) {}
    public String toString() { return "";}
}
