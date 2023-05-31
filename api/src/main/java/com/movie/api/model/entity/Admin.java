package com.movie.api.model.entity;

import lombok.Data;

import java.io.Serializable;

/**
 * @author CY
 * @description h_admin
 * @date 2023-05-31
 */
@Data
public class Admin implements Serializable {

    private String id;

    private String username;

    private String password;
}
