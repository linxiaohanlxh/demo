package com.example.demo.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.example.demo.pojo.LoginForm;
import com.example.demo.pojo.Student;

public interface StudentService extends IService<Student> {

    Student login(LoginForm loginForm);

    IPage<Student> getStudentByOpr(Page<Student> page, String name, String clazzName);
}
