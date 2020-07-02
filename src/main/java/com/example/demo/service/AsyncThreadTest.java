package com.example.demo.service;

import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.AsyncResult;
import org.springframework.stereotype.Service;

import java.util.concurrent.Future;

/**
 * @program: DynamicDataDemo
 * @description:
 * @author: wdgde
 * @create: 2020-06-29 11:07
 **/

@Service
public class AsyncThreadTest {

    @Async
    public Future asyncTest(int i) throws Exception {
        //System.out.println("异步效果查看--------------2");
        try {
            if (i % 2 != 0) {
                System.out.println("线程内抛出异常");
                throw new Exception("发生异常!!!");
            }
            System.out.println("正常执行");
        } catch (Exception e) {
            System.out.println("线程内捕获异常");
            //抛出异常  才能在future.get中捕获异常
            throw e;
            //return new AsyncResult(e);
        }
        return new AsyncResult("s");
    }
}