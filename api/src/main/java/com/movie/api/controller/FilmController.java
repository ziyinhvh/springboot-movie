package com.movie.api.controller;

import com.movie.api.model.entity.Film;
import com.movie.api.service.FilmService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

@RestController
@RequestMapping("/api/film")
public class FilmController {

    @Resource
    private FilmService filmService;

    @PostMapping("")
    public void save(@RequestBody Film film) {
        filmService.save(film);
    }

    @GetMapping("")
    public List<Film> list(String region, String type) {
        if (region != null && type != null) {
            return filmService.findByRegionAndType(region, type);
        }
        return filmService.findAll();
    }

//    @GetMapping("/hot/{limit}")
//    public List<Film> listHots(@PathVariable Integer limit) {
//        return filmService.findHots(limit);
//    }

    @GetMapping("/name/{name}")
    public List<Film> search(@PathVariable String name) {
        return filmService.findLikeName(name);
    }

    @GetMapping("/{id}")
    public Film findById(@PathVariable String id) {
        return filmService.findById(id);
    }

    @PutMapping("")
    public void update(@RequestBody Film film) {
        filmService.update(film);
    }

    @DeleteMapping("/{id}")
    public void deleteById(@PathVariable String id) {
        filmService.deleteById(id);
    }

}
