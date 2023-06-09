package com.movie.api.service;

import com.movie.api.model.entity.Film;

import java.util.List;

/**
 * FilmService接口定义了对电影信息进行操作的方法。
 */
public interface FilmService {

    /**
     * 将电影信息保存到数据库中。
     *
     * @param film 要保存的电影信息。
     */
    void save(Film film);

    /**
     * 根据电影ID从数据库中删除电影信息。
     *
     * @param id 要删除的电影ID。
     */
    void deleteById(String id);

    /**
     * 查询数据库中所有电影信息。
     *
     * @return 包含所有电影信息的列表。
     */
    List<Film> findAll();

    /**
     * 根据地区和类型查询电影信息。
     *
     * @param region 地区。
     * @param type   类型。
     * @return 符合条件的电影信息列表。
     */
    List<Film> findByRegionAndType(String region, String type);

    /**
     * 获取热门电影信息。
     *
     * @param limit 最多返回的电影数量。
     * @return 包含热门电影信息的列表。
     */
    List<Film> findHots(Integer limit);

    /**
     * 根据电影名模糊查询电影信息。
     *
     * @param name 电影名。
     * @return 符合条件的电影信息列表。
     */
    List<Film> findLikeName(String name);

    /**
     * 根据电影ID查询电影信息。
     *
     * @param id 电影ID。
     * @return 包含电影信息的Film对象。
     */
    Film findById(String id);

    /**
     * 更新电影信息。
     *
     * @param film 包含要更新的电影信息的Film对象。
     * @return 更新后的电影信息。
     */
    Film update(Film film);

}
