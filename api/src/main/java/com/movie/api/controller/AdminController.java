package com.movie.api.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.movie.api.common.R;
import com.movie.api.model.entity.Admin;
import com.movie.api.service.AdminService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.DigestUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

/**
 * @author CY
 * @description h_admin控制器
 * @date 2023-05-31
 */
@Slf4j
@RestController
@RequestMapping("/api/admin")
public class AdminController {
    @Autowired
    private AdminService adminService;

    @PostMapping("/login")
    public R<Admin> login(HttpServletRequest request, @RequestBody Admin admin) {
        //将页面提交的密码进行md5加密处理
        String password = admin.getPassword();
        password = DigestUtils.md5DigestAsHex(password.getBytes());
        //根据页面提交的用户名查询数据库
        LambdaQueryWrapper<Admin> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(Admin::getUsername, admin.getUsername());
        Admin admin1 = adminService.getOne(queryWrapper);

        //未查询到则返回登录失败结果
        if (admin1 == null) {
            return R.error("登录失败");
        }

        //密码错误则返回登录失败结果
        if (!admin1.getPassword().equals(password)) {
            return R.error("登录失败");
        }

        //登录成功，将员工id存入Session并返回登录成功结果
        request.getSession().setAttribute("employee", admin1.getId());
        return R.success(admin1);
    }
}
