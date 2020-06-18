package com.example.demo;

import com.example.demo.service.StudentService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * @program: DynamicDataDemo
 * @description:
 * @author: wdgde
 * @create: 2020-06-12 11:08
 **/
@RunWith(SpringRunner.class)
@SpringBootTest(classes = {StudentService.class})
public class GetConfigTest {

    @Autowired
    private StudentService studentService;

    @Test
    public void printConfig() {
//        System.out.println("------------------------------");
//        dataSourceConfig.print();
//        List<Hikari> hikari = dataSourceConfig.getHikari();
//        if (hikari.size() == 0) {
//            System.out.println("=======================");
//        }
//        for (int i = 0; i < hikari.size(); i++) {
//            System.out.println(hikari.get(i).toString());
//        }
//        studentService.printInfo();
    }
}