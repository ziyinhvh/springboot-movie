package com.movie.api.model.dto;

import lombok.Data;

/**
 * @author CY
 * @description 登录dto
 * @date 2023-05-31
 */
@Data
public class LoginDto {

    private String username;

    private String password;

    private boolean remember;

}
