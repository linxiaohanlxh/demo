package com.example.demo.service.impl;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.demo.mapper.AdminMapper;
import com.example.demo.pojo.Admin;
import com.example.demo.pojo.LoginForm;
import com.example.demo.service.AdminService;
import com.example.demo.util.MD5;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service("adminServiceImpl")
@Transactional
public class AdminServiceImpl extends ServiceImpl<AdminMapper,Admin> implements AdminService {

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private AdminMapper adminMapper;


    @Override
    public Admin login(LoginForm loginForm) {
        QueryWrapper<Admin> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("name",loginForm.getUsername());
        queryWrapper.eq("password",MD5.encrypt(loginForm.getPassword()));
        Admin admin = baseMapper.selectOne(queryWrapper);
        return admin;
    }

    @Override
    public Admin getAdminById(Long userId) {
        QueryWrapper<Admin> queryWrapper =  new QueryWrapper<>();
        queryWrapper.eq("id",userId);
        Admin admin = baseMapper.selectOne(queryWrapper);
        return admin;
    }
}
