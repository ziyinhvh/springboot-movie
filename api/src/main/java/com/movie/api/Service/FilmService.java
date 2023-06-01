package com.movie.api.service;

import com.movie.api.model.entity.Film;

import java.util.List;

/**
 * 电影服务接口，定义电影相关的业务方法
 */
public interface FilmService {

    /**
     * 保存电影信息
     *
     * @param film 电影实体类对象
     */
    void save(Film film);

    /**
     * 根据电影ID删除电影信息
     *
     * @param id 电影ID
     */
    void deleteById(String id);

    /**
     * 获取所有电影信息
     *
     * @return 所有电影信息的列表
     */
    List findAll();

    /**
     * 根据地区和类型查询电影信息列表
     *
     * @param region 地区
     * @param type   类型
     * @return 符合条件的电影信息列表
     */
    List findByRegionAndType(String region, String type);

    /**
     * 获取热门电影信息列表
     *
     * @param limit 获取的电影数量
     * @return 热门电影信息列表
     */
//    List findHots(Integer limit);

    /**
     * 根据电影名模糊查询电影信息列表
     *
     * @param name 电影名
     * @return 符合条件的电影信息列表
     */
    List findLikeName(String name);

    /**
     * 根据电影ID获取电影信息
     *
     * @param id 电影ID
     * @return 电影实体类对象
     */
    Film findById(String id);

    /**
     * 更新电影信息
     *
     * @param film 电影实体类对象
     * @return 更新后的电影实体类对象
     */
    Film update(Film film);

}
