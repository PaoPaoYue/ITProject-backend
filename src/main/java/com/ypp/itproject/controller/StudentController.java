package com.ypp.itproject.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.google.common.base.Charsets;
import com.google.common.hash.HashFunction;
import com.google.common.hash.Hashing;
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
 * 前端控制器
 * </p>
 *
 * @author ypp
 * @since 2020-08-31
 */
@RestController
@RequestMapping("/student")
public class StudentController {

    private static final Logger logger = LoggerFactory.getLogger(StudentController.class);

    private static final String usernamePattern = "^\\w{3,20}$";
    private static final String passwordPattern = "^(?=.*[A-Za-z])(?=.*\\d)\\w{8,20}$";

    private static final HashFunction hf = Hashing.sha256();

    private final IStudentService service;

    public StudentController(IStudentService service) {
        this.service = service;
    }

    @PostMapping("/register")
    SuccessWapper register(@RequestBody Student student) throws RestException {
        logger.debug(student.toString());
        String username = student.getUsername();
        String password = student.getPassword();
        if (username == null || password == null)
            throw new RestException(0, "no username or password provided");
        if (!username.matches(usernamePattern))
            throw new RestException(1, "username non-valid");
        if (service.getOne(new QueryWrapper<Student>().eq("username", student.getUsername())) != null)
            throw new RestException(2, "student already exist");
        if (!password.matches(passwordPattern))
            throw new RestException(3, "password non-valid");
        student.setPassword(hf.hashString(password, Charsets.UTF_8).toString());
        logger.debug(student.getPassword());
        return new SuccessWapper(service.save(student));
    }

    @PostMapping("/login")
    Map<String, String> login(@RequestBody Student student) throws RestException {
        logger.debug(student.toString());
        Student actual = service.getOne(new QueryWrapper<Student>().eq("username", student.getUsername()));
        if (actual == null)
            throw new RestException(0, "username not found");
        if (!actual.getPassword().equals(hf.hashString(student.getPassword(), Charsets.UTF_8).toString()))
            throw new RestException(1, "password incorrect");

        Map<String, String> map = new HashMap<>();
        String token = JwtUtil.issue(new StudentAuthVo(actual.getId(), actual.getUsername()));
        map.put("access-token", token);
        return map;
    }

    @GetMapping("/token")
    Map<String, String> token() {
        //check password valid
        Map<String, String> map = new HashMap<>();
        String token = JwtUtil.issue(new StudentAuthVo(1, "paopao"));
        map.put("access-token", token);
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

    @CheckLogin
    @GetMapping("/{id}")
    Student getStudent(@PathVariable int id) throws RestException {
        Student student = service.getById(id);
        if (student == null)
            throw new RestException(0, "student not found");
        student.setPassword(null);
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
