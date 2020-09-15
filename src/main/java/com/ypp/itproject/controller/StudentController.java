package com.ypp.itproject.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.ypp.itproject.exception.RestException;
import com.ypp.itproject.entity.Student;
import com.ypp.itproject.jwt.JwtUtil;
import com.ypp.itproject.jwt.annotation.CheckLogin;
import com.ypp.itproject.jwt.annotation.CheckPermission;
import com.ypp.itproject.vo.StudentAuthVo;
import com.ypp.itproject.vo.util.SuccessWapper;
import com.ypp.itproject.service.IStudentService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

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

    private static final Logger logger = LoggerFactory.getLogger(StudentController.class);

    IStudentService service;

    public StudentController(IStudentService service) {
        this.service = service;
    }

    @GetMapping("/login")
    Map<String, String> login() {
        //check password valid
        Map<String, String> map = new HashMap<>();
        String token = JwtUtil.issue(new StudentAuthVo(1, "paopao"));
        map.put("jwt", token);
        return map;
    }

    @CheckLogin
    @GetMapping("/auth")
    SuccessWapper auth() {
        StudentAuthVo studentAuthVo = (StudentAuthVo) JwtUtil.extract();
        logger.info(studentAuthVo.toString());
        return new SuccessWapper(true);
    }

    @CheckLogin
    @CheckPermission("student")
    @GetMapping("/perm1")
    SuccessWapper perm1() {
        return new SuccessWapper(true);
    }

    @CheckLogin
    @CheckPermission("user")
    @GetMapping("/perm2")
    SuccessWapper perm2() {
        return new SuccessWapper(true);
    }

    @GetMapping("/list")
    List<Student> getStudents() {
        return service.list(null);
    }

    @GetMapping("/{id}")
    Student getStudent(@PathVariable int id) throws RestException {
        Student student = service.getById(id);
        if (student == null)
            throw new RestException(0, "student not found");
        return student;
    }

    @PostMapping("/add")
    SuccessWapper addStudent(@RequestBody Student student) throws RestException {
        if (service.getOne(new QueryWrapper<Student>().eq("username", student.getUsername())) != null)
            throw new RestException(0, "student already exist");
        return new SuccessWapper(service.save(student));
    }

    @PostMapping("/delete")
    SuccessWapper deleteStudent(@RequestBody Student student) {
        return new SuccessWapper(service.removeById(student.getId()));
    }

    @PostMapping("/update")
    SuccessWapper updateStudent(@RequestBody Student student) {
        return new SuccessWapper(service.updateById(student));
    }
}
