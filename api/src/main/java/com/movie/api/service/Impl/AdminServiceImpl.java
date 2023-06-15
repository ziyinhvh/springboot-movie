package com.movie.api.service.Impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.movie.api.constant.Roles;
import com.movie.api.mapper.AdminMapper;
import com.movie.api.model.dto.LoginDto;
import com.movie.api.model.entity.Admin;
import com.movie.api.service.AdminService;
import com.movie.api.utils.JwtTokenUtil;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;

/**
 * 管理员服务实现类，实现管理员相关的业务方法
 */
@Service
public class AdminServiceImpl implements AdminService {

    @Resource
    private AdminMapper adminMapper; // 注入管理员Mapper对象

    /**
     * 管理员登录方法，用于验证管理员登录信息是否正确
     *
     * @param loginDto 包含用户名和密码的登录信息数据传输对象
     * @return 登录成功后生成的token字符串
     * @throws Exception 登录失败时抛出异常
     */
    @Override
    public String login(LoginDto loginDto) throws Exception {
        // 创建QueryWrapper对象，用于构建查询条件
        QueryWrapper wrapper = new QueryWrapper<>();
        // 在查询条件中添加用户名和密码，使用in方法，可避免SQL注入攻击
        wrapper.in("username", loginDto.getUsername());
        wrapper.in("password", loginDto.getPassword());
        // 通过Mapper对象查询数据库，返回查询结果
        Admin admin = adminMapper.selectOne(wrapper);
        // 如果查询结果为空，抛出异常
        if (admin == null) throw new Exception("用户名密码错误");
        // 根据用户选择的“记住我”选项设置token的过期时间
        long exp = loginDto.isRemember() ? JwtTokenUtil.REMEMBER_EXPIRATION_TIME : JwtTokenUtil.EXPIRATION_TIME;
        // 创建ArrayList对象，用于存储用户角色信息
        ArrayList roles = new ArrayList<>();
        // 向角色列表中添加管理员角色信息
        roles.add(Roles.ROLE_ADMIN);

        //返回登录成功后生成的token字符串
        return JwtTokenUtil.createToken(loginDto.getUsername(), roles, exp);
    }
}
