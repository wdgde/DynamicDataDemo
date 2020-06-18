package com.example.demo.service;

import com.example.demo.bean.Hikari;
import com.example.demo.bean.Student;
import com.example.demo.config.DataSourceContextHolder;
import com.example.demo.config.DefaultDataSource;
import com.example.demo.mapper.StudentMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @program: DynamicDataDemo
 * @description:
 * @author: wdgde
 * @create: 2020-06-11 17:31
 **/

@Service
public class StudentService {

    @Autowired
    private StudentMapper studentMapper;

    @Autowired
    private DefaultDataSource defaultDataSource;

    public void insertService() {
        Student student = new Student();
        student.setName("xxx");
        student.setAge("20");
        studentMapper.insertDb(student);
    }


    public void chooseDateSource(String str) {
        //str必需与targetDataSources.put的key一致，如果找不到当前数据库连接，就会使用默认连接，默认源也要设置
        DataSourceContextHolder.setDataSource(str);
        insertService();
        DataSourceContextHolder.clear();
    }

    public void allDataSource() {
        List<Hikari> hikari = defaultDataSource.getHikari();
        for (int i = 0; i < 3; i++) {
            DataSourceContextHolder.setDataSource(i + "");
            insertService();
            DataSourceContextHolder.clear();
            System.out.println("数据库" + hikari.get(i).getJdbcUrl() + "更新完成！");
        }
    }
}