package com.movie.api.model.vo;

import com.movie.api.model.entity.Arrangement;
import com.movie.api.model.entity.Film;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

/**
 * 电影排片前端展示
 */
@Data
@AllArgsConstructor
public class ArrangementVO {

    private List<Arrangement> arrangements;

    private Film film;

}
