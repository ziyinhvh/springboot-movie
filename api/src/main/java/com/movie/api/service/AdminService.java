package com.movie.api.service;

import com.movie.api.model.dto.LoginDto;


/**
 * 管理员服务接口，定义管理员相关的业务方法
 */
public interface AdminService {

    /**
     * 管理员登录方法，用于验证管理员登录信息是否正确
     *
     * @param loginDto 包含用户名和密码的登录信息数据传输对象
     * @return 登录成功后生成的token字符串
     * @throws Exception 登录失败时抛出异常
     */
    String login(LoginDto loginDto) throws Exception;

}