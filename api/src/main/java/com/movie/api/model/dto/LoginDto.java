package com.movie.api.model.dto;

import lombok.Data;


/**
 * 登录信息数据传输对象，用于封装用户登录信息
 */
@Data
public class LoginDto {

    private String username; // 用户名

    private String password; // 密码

    private boolean remember; // 是否记住我

}