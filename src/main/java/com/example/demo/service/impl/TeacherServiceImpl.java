package com.example.demo.service.impl;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.demo.mapper.TeacherMapper;
import com.example.demo.pojo.LoginForm;
import com.example.demo.pojo.Teacher;
import com.example.demo.service.TeacherService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service("teaService")
@Transactional
public class TeacherServiceImpl extends ServiceImpl<TeacherMapper,Teacher> implements TeacherService {


    @Override
    public IPage<Teacher> getTeachers(Page<Teacher> page, String name, String clazzName) {
        QueryWrapper<Teacher> queryWrapper = new QueryWrapper<>();
        queryWrapper.like(name!=null,"name",name);
        queryWrapper.like(clazzName!=null,"clazz_name",clazzName);
        queryWrapper.orderByDesc("id");
        Page<Teacher> selectPage = baseMapper.selectPage(page, queryWrapper);
        return selectPage;
    }

    @Override
    public Teacher login(LoginForm loginForm) {
        return null;
    }
}
