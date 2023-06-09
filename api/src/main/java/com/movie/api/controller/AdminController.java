package com.movie.api.controller;

import com.movie.api.model.dto.LoginDto;
import com.movie.api.service.AdminService;
import io.swagger.annotations.Api;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.Map;


/**
 * @author CY
 * @description h_admin控制器
 * @date 2023-05-31
 */
@Slf4j
@RestController
@Api(tags = "管理员接口")
@RequestMapping("/api/admin")
public class AdminController {
    @Resource
    private AdminService adminService;

    @PostMapping("/login")
    public Map<String, String> login(@RequestBody LoginDto loginDto) throws Exception {
        HashMap<String, String> map = new HashMap<>();
        map.put("token", adminService.login(loginDto));
        return map;
    }

}
