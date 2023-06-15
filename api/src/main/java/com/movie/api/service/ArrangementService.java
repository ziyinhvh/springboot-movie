package com.movie.api.service;

import com.movie.api.model.entity.Arrangement;
import com.movie.api.model.vo.ArrangementVO;

import java.util.List;

public interface ArrangementService {

    // 保存电影排片
    void save(Arrangement arrangement);

    // 查询所有排片
    List<Arrangement> findAll();

    // 根据电影ID查询排片
    ArrangementVO findByFilmId(String fid);

    // 根据排片查询已选座位号
    List<Integer> getSeatsHaveSelected(String id);

    // 根据ID查询电影排片
    Arrangement findById(String id);

    // 根据ID删除电影排片
    void deleteById(String id);

    // 修改电影排片
    Arrangement Update(Arrangement arrangement);

}
