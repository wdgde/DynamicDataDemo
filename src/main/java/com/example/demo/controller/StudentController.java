package com.example.demo.controller;

import com.example.demo.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @program: DynamicDataDemo
 * @description:
 * @author: wdgde
 * @create: 2020-06-11 17:29
 **/
@RestController
public class StudentController {

    @Autowired
    private StudentService studentService;

    @RequestMapping("/student")
    public void insertController() {
        //studentService.insertService();
        studentService.allDataSource();
    }

    @RequestMapping("/student0")
    public void insertController0() {
        //studentService.insertService();
        studentService.chooseDateSource("0");
    }

    @RequestMapping("/student1")
    public void insertController1() {
        //studentService.insertService();
        studentService.chooseDateSource("1");
    }

}