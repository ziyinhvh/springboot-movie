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

@Service
@CacheConfig(cacheNames = "film")
public class FilmServiceImpl implements FilmService {

    @Resource
    private FilmMapper filmMapper;

    @Override
    public void save(Film film) {
        film.setHot(0);
        filmMapper.insert(film);
    }

    @CacheEvict(value = {"filmsByName", "filmById", "allFilms"}, allEntries = true)
    @Override
    public void deleteById(String id) {
        filmMapper.deleteById(id);
    }

    @Cacheable(value = "allFilms")
    @Override
    public List<Film> findAll() {
        return filmMapper.selectList(null);
    }


    @Override
    public List<Film> findByRegionAndType(String region, String type) {
        QueryWrapper<Film> wrapper = new QueryWrapper<>();
        if (!region.equals("全部")) {
            wrapper.in("region", region);
        }
        if (!type.equals("全部")) {
            wrapper.in("type", type);
        }
        return filmMapper.selectList(wrapper);
    }

    @Override
    public List<Film> findHots(Integer limit) {
        QueryWrapper<Film> wrapper = new QueryWrapper<>();
        wrapper.orderByDesc("hot").last("limit " + limit);
        return filmMapper.selectList(wrapper);
    }

    @Cacheable(value = "filmsByName", key = "#name")
    @Override
    public List<Film> findLikeName(String name) {
        QueryWrapper<Film> wrapper = new QueryWrapper<>();
        wrapper.like("name", name);
        return filmMapper.selectList(wrapper);
    }

    @Cacheable(value = "filmById", key = "#id")
    @Override
    public Film findById(String id) {
        return filmMapper.selectById(id);
    }

    @CacheEvict(value = {"filmsByName", "filmById"}, key = "#film.id", allEntries = true)
    @Override
    public Film update(Film film) {
        filmMapper.updateById(film);
        return film;
    }


}
