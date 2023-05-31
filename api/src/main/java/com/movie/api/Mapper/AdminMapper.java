package com.movie.api.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.movie.api.model.entity.Admin;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

/**
 * @author CY
 * @description adminMapper
 * @date 2023-05-31
 */
@Mapper
public interface AdminMapper extends BaseMapper<Admin> {

}