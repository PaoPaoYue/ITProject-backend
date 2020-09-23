package com.ypp.itproject.service.impl;

import com.ypp.itproject.entity.Student;
import com.ypp.itproject.mapper.StudentMapper;
import com.ypp.itproject.service.IStudentService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author ethan
 * @since 2020-09-22
 */
@Service
public class StudentServiceImpl extends ServiceImpl<StudentMapper, Student> implements IStudentService {

}
