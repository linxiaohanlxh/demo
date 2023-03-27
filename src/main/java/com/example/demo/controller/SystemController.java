package com.example.demo.controller;


import com.example.demo.pojo.Admin;
import com.example.demo.pojo.LoginForm;
import com.example.demo.pojo.Student;
import com.example.demo.pojo.Teacher;
import com.example.demo.service.AdminService;
import com.example.demo.service.StudentService;
import com.example.demo.service.TeacherService;
import com.example.demo.util.CreateVerifiCodeImage;
import com.example.demo.util.JwtHelper;
import com.example.demo.util.Result;
import com.example.demo.util.ResultCodeEnum;
import com.google.common.annotations.VisibleForTesting;
import io.swagger.models.auth.In;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.*;
import java.util.function.BiFunction;

@RestController
@RequestMapping("/sms/system")
public class SystemController {

    @Autowired
    private AdminService adminService;
    @Autowired
    private TeacherService teacherService;
    @Autowired
    private StudentService studentService;

    @RequestMapping(value = "/getVerifiCodeImage", method = RequestMethod.GET)
    public void getVerifiCodeImage(HttpServletRequest request, HttpServletResponse response) {
        //获取图片
        BufferedImage verifiCodeImage =
                CreateVerifiCodeImage.getVerifiCodeImage();
        //获取图片上的验证码
        String verifiCode = new String(CreateVerifiCodeImage.getVerifiCode());

        HttpSession session = request.getSession();
        session.setAttribute("verifiCode", verifiCode);

        try {
            ImageIO.write(verifiCodeImage, "JPEG", response.getOutputStream());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public Result login(@RequestBody LoginForm loginForm, HttpServletRequest request) {
        //验证码校验
        HttpSession session = request.getSession();
        String verifiCode = (String) session.getAttribute("verifiCode");
        if ("".equals(verifiCode) || null == verifiCode) {
            return Result.fail().message("验证码失效，请刷新后重试");
        }
        if (!verifiCode.equals(loginForm.getVerifiCode())) {
            return Result.fail().message("验证码有误");
        }
        //从session域中移除现有验证码
        session.removeAttribute("verifiCode");
        //分用户类型进行校验
        //准备一个map用来存放响应的数据
        Map<String, Object> map = new HashMap<>();
        Integer userType = loginForm.getUserType();
        switch (userType) {
            case 1:
                try {
                    Admin admin = adminService.login(loginForm);
                    if (null != admin) {
                        // 用户的类型和id转换成密文，以token形式向客户端反馈
                        String token = JwtHelper.createToken(admin.getId().longValue(), 1);
                        map.put("token", token);
                    } else {
                        throw new RuntimeException("用户名或者密码有误");
                    }
                    return Result.ok(map);
                } catch (RuntimeException e) {
                    e.printStackTrace();
                    return Result.fail().message(e.getMessage());
                }
            case 2:
                try {
                    Student student = studentService.login(loginForm);
                    if (null != student) {
                        // 用户的类型和id转换成密文，以token形式向客户端反馈
                        String token = JwtHelper.createToken(student.getId().longValue(), 2);
                        map.put("token", token);
                    } else {
                        throw new RuntimeException("用户名或者密码有误");
                    }
                    return Result.ok(map);
                } catch (RuntimeException e) {
                    e.printStackTrace();
                    return Result.fail().message(e.getMessage());
                }
            case 3:
                try {
                    Teacher teacher = teacherService.login(loginForm);
                    if (null != teacher) {
                        // 用户的类型和id转换成密文，以token形式向客户端反馈
                        String token = JwtHelper.createToken(teacher.getId().longValue(), 3);
                        map.put("token", token);
                    } else {
                        throw new RuntimeException("用户名或者密码有误");
                    }
                    return Result.ok(map);
                } catch (RuntimeException e) {
                    e.printStackTrace();
                    return Result.fail().message(e.getMessage());
                }
        }
        return Result.fail().message("无此用户，请注册");
    }

    @RequestMapping(value = "/getInfo", method = RequestMethod.GET)
    public Result getInfo(@RequestHeader("token") String token) {
        //判断token是否过期
        boolean expiration = JwtHelper.isExpiration(token);
        if (expiration) {
            return Result.build(null, ResultCodeEnum.TOKEN_ERROR);
        }
        //从token中解析出用户名、id、用户类型
        Long userId = JwtHelper.getUserId(token);
        Integer userType = JwtHelper.getUserType(token);
        Map<String, Object> map = new HashMap<>();
        switch (userType) {
            case 1:
                Admin admin = adminService.getAdminById(userId);
                map.put("userType", 1);
                map.put("user", admin);
                break;
        }
        return Result.ok(map);
    }
}
