package com.movie.api.controller;

import com.movie.api.model.entity.Film;
import com.movie.api.service.FilmService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

@RestController
@Api(tags = "电影接口")
@RequestMapping("/api/film")
public class FilmController {

    @Resource
    private FilmService filmService;

    /**
     * 保存电影信息
     * @param film 电影信息
     */
    @PostMapping("")
    public void save(@RequestBody Film film) {
        filmService.save(film);
    }

    /**
     * 获取电影列表
     * @param region 地区
     * @param type   类型
     * @return 电影列表
     */
    @GetMapping("")
    public List<Film> list(String region, String type) {
        if (region != null && type != null) {
            return filmService.findByRegionAndType(region, type);
        }
        return filmService.findAll();
    }

    /**
     * 根据请求路径中的参数limit获取指定数量的热门电影列表
     * @param limit 请求路径中的参数，限制返回的电影数量
     * @return 返回热门电影列表
     */
    @GetMapping("/hot/{limit}")
    @ApiOperation("获取热榜电影")
    public List<Film> listHots(@PathVariable Integer limit) {
        return filmService.findHots(limit);
    }

    /**
     * 根据电影名称搜索电影
     * @param name 电影名称
     * @return 符合条件的电影列表
     */
    @GetMapping("/name/{name}")
    public List<Film> search(@PathVariable String name) {
        return filmService.findLikeName(name);
    }

    /**
     * 根据电影 ID 获取电影信息
     * @param id 电影 ID
     * @return 电影信息
     */
    @GetMapping("/{id}")
    public Film findById(@PathVariable String id) {
        return filmService.findById(id);
    }

    /**
     * 更新电影信息
     * @param film 电影信息
     */
    @PutMapping("")
    public void update(@RequestBody Film film) {
        filmService.update(film);
    }

    /**
     * 根据电影 ID 删除电影信息
     * @param id 电影 ID
     */
    @DeleteMapping("/{id}")
    public void deleteById(@PathVariable String id) {
        filmService.deleteById(id);
    }

}

