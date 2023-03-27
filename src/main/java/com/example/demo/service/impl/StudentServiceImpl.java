package com.example.demo.service.impl;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.demo.mapper.StudentMapper;
import com.example.demo.pojo.LoginForm;
import com.example.demo.pojo.Student;
import com.example.demo.service.StudentService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**

 */
@Service("stuService")
@Transactional
public class StudentServiceImpl extends ServiceImpl<StudentMapper,Student> implements StudentService {

    @Override
    public Student login(LoginForm loginForm) {
        return null;
    }

    @Override
    public IPage<Student> getStudentByOpr(Page<Student> page, String name, String clazzName) {
        QueryWrapper<Student> queryWrapper = new QueryWrapper<>();
        queryWrapper.like(name!=null,"name",name);
        queryWrapper.like(clazzName!=null,"clazz_name",clazzName);
        queryWrapper.orderByDesc("id");
        Page<Student> selectPage = baseMapper.selectPage(page, queryWrapper);
        return selectPage;
    }
}
