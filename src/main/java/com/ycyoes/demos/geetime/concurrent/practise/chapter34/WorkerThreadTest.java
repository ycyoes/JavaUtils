package com.ycyoes.demos.geetime.concurrent.practise.chapter34;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.concurrent.*;

/**
 * 用线程池实现的 echo 服务端
 *
 * @author ycyoes
 * @date 2022-05-16 21:39
 */
public class WorkerThreadTest {
    public static void main(String[] args) throws IOException {
//        ExecutorService es = Executors.newFixedThreadPool(500);
        ExecutorService es = new ThreadPoolExecutor(50, 500, 60L, TimeUnit.SECONDS,
                //注意要创建有界队列
                new LinkedBlockingQueue<>(2000),
                //建议根据业务需求实现ThreadFactory
                r -> {
                    return new Thread(r, "echo-" + r.hashCode());
                },
                //建议根据业务需要实现RejectedExecutionHandler
                new ThreadPoolExecutor.CallerRunsPolicy());
        final ServerSocketChannel ssc = ServerSocketChannel.open().bind(new InetSocketAddress(8080));
        //处理请求
        try {
            while (true) {
                //接收请求
                SocketChannel sc = ssc.accept();
                //将请求处理任务提交给线程池
                es.execute(() -> {
                    try {
                        //读Socket
                        ByteBuffer rb = ByteBuffer.allocate(1024);
                        sc.read(rb);
                        //模拟处理请求
                        Thread.sleep(200);

                        //写Socket
                        ByteBuffer wb = (ByteBuffer) rb.flip();
                        sc.write(wb);

                        //关闭Socket
                        sc.close();

                    } catch (IOException e) {
                        e.printStackTrace();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                });
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                ssc.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
            es.shutdown();
        }
    }
}
