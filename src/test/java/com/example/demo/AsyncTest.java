package com.example.demo;

import com.example.demo.service.AsyncThreadTest;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Future;

/**
 * @program: DynamicDataDemo
 * @description:
 * @author: wdgde
 * @create: 2020-06-29 11:39
 **/
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = DemoApplication.class)
@EnableAsync
public class AsyncTest {

    @Autowired
    private AsyncThreadTest asyncThreadTest;

    @Test
    public void test() throws Exception {
        List<Future> results = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            Future future = asyncThreadTest.asyncTest(i);
            results.add(future);
        }
        //System.out.println("异步效果查看--------------1");
        for (int i = 0; i < 10; i++) {
            try {
                results.get(i).get();
            } catch (Exception e) {
                System.out.println("-------------校验某个结果异常");
                throw e;
            }

        }

    }
}