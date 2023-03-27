package com.example.demo.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.demo.mapper.ClazzMapper;
import com.example.demo.pojo.Clazz;
import com.example.demo.service.ClazzService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class ClazzServiceImpl extends ServiceImpl<ClazzMapper,Clazz> implements ClazzService {

    @Override
    public IPage<Clazz> getClazzsByOpr(Page<Clazz> page, String name, String gradeName) {
        QueryWrapper<Clazz> queryWrapper = new QueryWrapper<>();
        queryWrapper.like(name!=null,"name",name);
        queryWrapper.like(gradeName!=null,"grade_name",gradeName);
        queryWrapper.orderByDesc("id");
        Page<Clazz> selectPage = baseMapper.selectPage(page, queryWrapper);
        return selectPage;
    }

    @Override
    public List<Clazz> getClazzs() {
        return baseMapper.selectList(null);
    }
}
