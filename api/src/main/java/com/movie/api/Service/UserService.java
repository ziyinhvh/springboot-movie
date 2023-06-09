package com.movie.api.service;

import com.movie.api.model.dto.LoginDto;
import com.movie.api.model.entity.User;

import java.util.List;

public interface UserService {

    /**
     * 用户登录，接收一个 LoginDto 对象作为参数，抛出一个 Exception 异常
     * @param dto
     * @return
     * @throws Exception
     */
    User login(LoginDto dto) throws Exception;

    /**
     * 返回一个包含所有用户的列表
     * @return
     */
    List<User> findAll();

    /**
     * 根据用户 ID 查找用户，并返回该用户对象
     * @param id
     * @return
     */
    User findById(String id);

    /**
     * 更新用户信息，接收一个 User 对象作为参数，返回更新后的用户对象
     * @param user
     * @return
     */
    User update(User user);

    /**
     * 保存用户信息，接收一个 User 对象作为参数，抛出一个 Exception 异常
     * @param user
     * @return
     * @throws Exception
     */
    User save(User user) throws Exception;

    /**
     * 根据用户名查找用户，并返回该用户对象
     * @param username
     * @return
     */
    User findByUsername(String username);

    /**
     * 根据用户 ID 删除用户
     * @param id
     */
    void deleteById(String id);

}
