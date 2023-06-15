package com.movie.api.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.movie.api.model.entity.Admin;
import org.apache.ibatis.annotations.Mapper;

/**
 * AdminMapper接口，继承了BaseMapper接口，用于操作Admin实体类对应的数据表。
 * 使用@Mapper注解标识为MyBatis的Mapper接口。
 */
@Mapper
public interface AdminMapper extends BaseMapper<Admin> {

}