package com.ypp.itproject.controller;


import com.ypp.itproject.entity.Student;
import com.ypp.itproject.service.IStudentService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author ypp
 * @since 2020-08-31
 */
@RestController
@RequestMapping("/student")
public class StudentController {

    IStudentService service;

    public StudentController(IStudentService service) {
        this.service = service;
    }

    @GetMapping("/list")
    List<Student> getStudents() {
        return  service.list(null);
    }

    @GetMapping("/{id}")
    Student getStudent(@PathVariable int id) {
        return service.getById(id);
    }
}
