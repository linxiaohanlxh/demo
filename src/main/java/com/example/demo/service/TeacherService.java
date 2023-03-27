package com.example.demo.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.example.demo.pojo.LoginForm;
import com.example.demo.pojo.Teacher;

public interface TeacherService extends IService<Teacher> {


    IPage<Teacher> getTeachers(Page<Teacher> page, String name, String clazzName);

    Teacher login(LoginForm loginForm);
}
