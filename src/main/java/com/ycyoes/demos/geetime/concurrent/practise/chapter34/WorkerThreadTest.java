package com.ycyoes.demos.geetime.concurrent.practise.chapter34;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * 用线程池实现的 echo 服务端
 *
 * @author ycyoes
 * @date 2022-05-16 21:39
 */
public class WorkerThreadTest {
    public static void main(String[] args) throws IOException {
        ExecutorService es = Executors.newFixedThreadPool(500);
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
