package com.movie.api.service.Impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.movie.api.mapper.ArrangementMapper;
import com.movie.api.mapper.FilmMapper;
import com.movie.api.mapper.OrderMapper;
import com.movie.api.model.entity.Arrangement;
import com.movie.api.model.entity.Order;
import com.movie.api.model.vo.ArrangementVO;
import com.movie.api.service.ArrangementService;
import com.movie.api.utils.DataTimeUtil;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

@Service
public class ArrangementServiceImpl implements ArrangementService {

    // 注入 ArrangementMapper 对象，用于访问数据库中的 Arrangement 表
    @Resource
    private ArrangementMapper arrangementMapper;

    // 注入 FilmMapper 对象，用于访问数据库中的 Film 表
    @Resource
    private FilmMapper filmMapper;

    // 注入 OrderMapper 对象，用于访问数据库中的 Order 表
    @Resource
    private OrderMapper orderMapper;

    @Override
    public void save(Arrangement arrangement) {
        arrangement.setBoxOffice(0); // 将票房值设置为 0
        arrangement.setCreateAt(DataTimeUtil.getNowTimeString()); // 将创建时间设置为当前时间
        arrangementMapper.insert(arrangement); // 将 Arrangement 对象插入到数据库中
    }

    // 查询所有排片
    @Override
    public List<Arrangement> findAll() {
        return arrangementMapper.selectList(null);
    }

    // 根据电影ID查询排片
    @Override
    public ArrangementVO findByFilmId(String fid) {
        // 查询与特定电影 ID 相关联的所有 Arrangement 对象
        List<Arrangement> list = arrangementMapper.selectList(new QueryWrapper<Arrangement>().in("fid", fid));
        // 返回一个 ArrangementVO 对象，其中包含 Arrangement 对象列表和与该电影 ID 相关联的 Film 对象
        return new ArrangementVO(list, filmMapper.selectById(fid));
    }

    // 根据排片查询已选座位号
    @Override
    public List<Integer> getSeatsHaveSelected(String id) {
        // 查询与特定 Arrangement ID 相关联的所有 Order 对象
        List<Order> orders = orderMapper.selectList(new QueryWrapper<Order>().in("aid", id));
        // 创建一个 ArrayList 对象，用于存储所有已选择的座位号
        List<Integer> seats = new ArrayList<>();
        for (Order o : orders) {
            // 将该对象的座位号字符串按照 "号" 进行分割
            String[] split = o.getSeats().split("号");
            // 对于分割后得到的每个字符串
            for (String s : split) {
                seats.add(Integer.parseInt(s)); // 将其转换为整数并添加到 seats 列表中
            }
        }
        return seats; // 返回座位号列表
    }

    // 根据ID查询电影排片
    @Override
    public Arrangement findById(String id) {
        return arrangementMapper.selectById(id);
    }

    // 根据ID删除电影排片
    @Override
    public void deleteById(String id) {
        arrangementMapper.deleteById(id);
    }

    // 修改电影排片
    @Override
    public Arrangement Update(Arrangement arrangement) {
        arrangementMapper.updateById(arrangement);
        return arrangement;
    }

}
