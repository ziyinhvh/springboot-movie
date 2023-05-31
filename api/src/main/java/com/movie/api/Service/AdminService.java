package com.movie.api.service;

import com.movie.api.model.dto.LoginDto;

/**
 * @author CY
 * @description admin服务层
 * @date 2023-05-31
 */
public interface AdminService {
    String login(LoginDto loginDto) throws Exception;
}