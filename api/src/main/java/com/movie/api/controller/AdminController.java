package com.movie.api.controller;

import com.movie.api.model.dto.LoginDto;
import com.movie.api.service.AdminService;
import io.swagger.annotations.Api;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.Map;
@Slf4j // 使用 lombok 提供的注解，自动生成日志对象，避免手动创建日志对象的繁琐操作
@RestController // 声明该类是一个 RESTful 风格的控制器
@Api(tags = "管理员接口") // 使用 Swagger 提供的注解，用于生成 API 文档时对该类进行分类
@RequestMapping("/api/admin") // 声明该类中的所有请求的路径都以 "/api/admin" 开头
public class AdminController {
    @Resource // 使用 Spring 提供的注解，自动注入 AdminService 对象
    private AdminService adminService;

    @PostMapping("/login") // 声明该方法处理 POST 请求，请求路径为 "/api/admin/login"
    public Map<String, String> login(@RequestBody LoginDto loginDto) throws Exception {
        // 使用 HashMap 存储返回结果
        HashMap<String, String> map = new HashMap<>();
        // 调用 AdminService 的 login 方法，获取登录 token
        map.put("token", adminService.login(loginDto));
        // 返回结果
        return map;
    }
}
