package com.example.demo.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.demo.mapper.GradeMapper;
import com.example.demo.pojo.Grade;
import com.example.demo.service.GradeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


@Service
@Transactional
public class GradeServiceImpl extends ServiceImpl<GradeMapper,Grade> implements GradeService {

    @Autowired
    private GradeMapper gradeMapper;

    @Override
    public IPage<Grade> getGradeByOpr(Page<Grade> page, String gradeName) {
        QueryWrapper<Grade> queryWrapper = new QueryWrapper();
        queryWrapper.like(gradeName!=null,"name",gradeName);
        queryWrapper.orderByDesc("id");
        Page<Grade> page1 = baseMapper.selectPage(page,queryWrapper);
        return page1;
    }

    @Override
    public List<Grade> getGrades() {
        return baseMapper.selectList(null);
    }
}
