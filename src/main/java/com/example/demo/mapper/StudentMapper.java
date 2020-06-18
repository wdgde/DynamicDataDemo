package com.example.demo.mapper;

import com.example.demo.bean.Student;
import org.springframework.stereotype.Repository;

/**
 * @author CMSZ
 */
@Repository
public interface StudentMapper {
    /**
     * @param student
     */
    void insertDb(Student student);
}
