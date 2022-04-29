package com.ycyoes.demos.geetime.concurrent.practise.chapter25;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;

/**
 * CompletionService使用示例
 *
 * @author ycyoes
 * @date 2022-04-28 21:39
 */
public class CompletionServiceTest {
    /**
     * Dubbo 中有一种叫做 Forking 的集群模式，这种集群模式下，支持并行地调用多个查询服务，只要有一个成功返回结果，整个服务就可以返回了。
     * 例如你需要提供一个地址转坐标的服务，为了保证该服务的高可用和性能，你可以并行地调用 3 个地图服务商的 API，然后只要有 1 个正确返回了结果 r，
     * 那么地址转坐标这个服务就可以直接返回 r 了。这种集群模式可以容忍 2 个地图服务商服务异常，但缺点是消耗的资源偏多。
     *
     * 利用 CompletionService 可以快速实现 Forking 这种集群模式，比如下面的示例代码就展示了具体是如何实现的。
     * 首先我们创建了一个线程池 executor 、一个 CompletionService 对象 cs 和一个Future类型的列表 futures，
     * 每次通过调用 CompletionService 的 submit() 方法提交一个异步任务，会返回一个 Future 对象，我们把这些 Future 对象保存在列表 futures 中。
     * 通过调用 cs.take().get()，我们能够拿到最快返回的任务执行结果，只要我们拿到一个正确返回的结果，就可以取消所有任务并且返回最终结果了。
     * @param args
     */
    public static void main(String[] args) {
        //创建线程池
        ExecutorService executorService = Executors.newFixedThreadPool(3);
        //创建CompletionService
        CompletionService<Integer> cs = new ExecutorCompletionService<>(executorService);
        //用于保存Future对象
        List<Future<Integer>> futures = new ArrayList<>(3);
        //提交异步任务，并保存future到futures
        futures.add(cs.submit(() -> geoCoderByS1()));
        futures.add(cs.submit(() -> geoCoderByS2()));
        futures.add(cs.submit(() -> geoCoderByS3()));

        //获取最快返回的任务执行结果
        Integer r = 0;
        try {
            //只要有一个成功返回，则break
            for (int i = 0; i < 3; i++) {
                r = cs.take().get();
                //简单地通过判空来检查是否成功返回
                if (r != null) {
                    break;
                }
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        } finally {
            //取消所有任务
            for(Future<Integer> f : futures) {
                f.cancel(true);
            }
        }
        System.out.println(r);
    }

    static int geoCoderByS1() throws InterruptedException {

        System.out.println("----------坐标转换1-----------");
        Thread.sleep(2);
        return 1;
    }

    static int geoCoderByS2() {
        System.out.println("----------坐标转换2-----------");
        return 2;
    }

    static int geoCoderByS3() {
        System.out.println("----------坐标转换3-----------");
        return 3;
    }
}
