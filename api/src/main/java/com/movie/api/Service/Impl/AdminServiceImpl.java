package com.movie.api.service.Impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.movie.api.mapper.AdminMapper;
import com.movie.api.model.entity.Admin;
import com.movie.api.service.AdminService;
import org.springframework.stereotype.Service;

@Service
public class AdminServiceImpl extends ServiceImpl<AdminMapper, Admin> implements AdminService {

}
