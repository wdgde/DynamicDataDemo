package com.example.demo;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;

/**
 * @program: DynamicDataDemo
 * @description:
 * @author: wdgde
 * @create: 2020-06-29 10:31
 **/
public class ThreadPoolTest {

    @Test
    public void testException() {
        try {
            testExecutorServiceException();
        } catch (Exception e) {
            //e.printStackTrace();
            System.out.println("-----------------最外层抛出异常");
        }

    }

    public void testExecutorServiceException() throws Exception {
        Boolean flag = true;
        //线程池，一个线程，线程池内部的任务是异步串行执行
        ExecutorService executorService = Executors.newSingleThreadExecutor();
        //线程池，nThreads个线程
        //ExecutorService executorService = Executors.newFixedThreadPool(10);
        //results中存放任务执行的结果
        List<Future<Boolean>> results = new ArrayList<Future<Boolean>>();
        try {
            for (int i = 0; i < 10; i++) {
                Future<Boolean> future = executorService.submit(new ActSKUCacheCallable(i, flag));
                results.add(future);
            }
        } catch (Exception e) {
            //e.printStackTrace();
            throw e;
        }
        //校验到某个结果时抛出异常，该段程序停止运行，不再校验后面的结果，但是异步任务继续执行完成

        verifyResult(results);
    }

    public void verifyResult(List<Future<Boolean>> results) throws ExecutionException, InterruptedException {
        System.out.println("结果集总数：" + results.size());
        for (int i = 0; i < 10; i++) {
            System.out.println("------------------------执行结果校验");
            boolean flag1 = results.get(i).get();
            results.get(i).get();
            System.out.println("执行结果：" + flag1);
        }
    }

    private class ActSKUCacheCallable implements Callable<Boolean> {
        int i;
        Boolean flag;

        ActSKUCacheCallable(int i, Boolean flag) {
            this.i = i;
            this.flag = flag;
        }

        public Boolean call() throws Exception {
            try {
                if (i % 2 != 0) {
                    System.out.println("线程内抛出异常");
                    throw new Exception("try");
                }
                System.out.println("正常执行");
            } catch (Exception e) {
                System.out.println("线程内捕获异常");
                //flag = false;
                //抛出异常不影响线程池中其他任务的执行
                throw e;
                //return Boolean.FALSE;
            } finally {
                //System.out.println("finally 代码块" + flag);
            }

            //System.out.println("try代码块外面");
            return Boolean.TRUE;
        }
    }


}