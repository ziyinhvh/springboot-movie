package com.movie.api.model.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;


/**
 * 管理员实体类，用于封装管理员信息
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@TableName("t_admin") // 用于指定该实体类对应的数据库表名
public class Admin implements Serializable {

    private String id; // 管理员id，主键

    private String username; // 管理员用户名，唯一

    private String password; // 管理员密码

    private String avatar; // 管理员头像

}