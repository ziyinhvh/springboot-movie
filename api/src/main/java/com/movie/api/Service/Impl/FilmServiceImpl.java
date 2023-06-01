package com.movie.api.service.Impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.movie.api.mapper.FilmMapper;
import com.movie.api.model.entity.Film;
import com.movie.api.service.FilmService;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * 电影服务实现类，实现电影相关的业务方法
 */
@Service
@CacheConfig(cacheNames = "film")
public class FilmServiceImpl implements FilmService {

    @Resource
    private FilmMapper filmMapper;

    /**
     * 保存电影信息
     * @param film 电影实体类对象
     */
    @Override
    public void save(Film film) {
        film.setHot(0);
        filmMapper.insert(film);
    }

    /**
     * 根据电影ID删除电影信息
     * @param id 电影ID
     */
    @CacheEvict
    @Override
    public void deleteById(String id) {
        filmMapper.deleteById(id);
    }

    /**
     * 获取所有电影信息
     * @return 所有电影信息的列表
     */
    @Cacheable
    @Override
    public List findAll() {
        return filmMapper.selectList(null);
    }

    /**
     * 根据地区和类型查询电影信息列表
     * @param region 地区
     * @param type 类型
     * @return 符合条件的电影信息列表
     */
    @Override
    public List findByRegionAndType(String region, String type) {
        QueryWrapper wrapper = new QueryWrapper<>();
        if (!region.equals("全部")) {
            wrapper.in("region", region);
        }
        if (!type.equals("全部")) {
            wrapper.in("type", type);
        }
        return filmMapper.selectList(wrapper);
    }

    /**
     * 获取热门电影信息列表
     * @param limit 获取的电影数量
     * @return 热门电影信息列表
     */
//    @Override
//    public List findHots(Integer limit) {
//        QueryWrapper wrapper = new QueryWrapper<>();
//        wrapper.orderByDesc("hot").last("limit " + limit);
//        return filmMapper.selectList(wrapper);
//    }

    /**
     * 根据电影名模糊查询电影信息列表
     * @param name 电影名
     * @return 符合条件的电影信息列表
     */
    @Cacheable
    @Override
    public List findLikeName(String name) {
        QueryWrapper wrapper = new QueryWrapper<>();
        wrapper.like("name", name);
        return filmMapper.selectList(wrapper);
    }

    /**
     * 根据电影ID获取电影信息
     * @param id 电影ID
     * @return 电影实体类对象
     */
    @Cacheable
    @Override
    public Film findById(String id) {
        return filmMapper.selectById(id);
    }

    /**
     * 更新电影信息
     * @param film 电影实体类对象
     * @return 更新后的电影实体类对象
     */
    @CacheEvict
    @Override
    public Film update(Film film) {
        filmMapper.updateById(film);
        return film;
    }

}
