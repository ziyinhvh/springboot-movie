package com.movie.api.model.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * @author CY
 * @description 管理员
 * @date 2023-05-31
 */
@Data
@TableName("h_admin")
@NoArgsConstructor
@AllArgsConstructor
public class Admin implements Serializable {

    private String id;

    private String username;

    private String password;

    private String avatar;

}