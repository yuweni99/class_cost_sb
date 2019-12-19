package com.zys.class_cost_sb.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zys.class_cost_sb.mapper.ExpendMapper;
import com.zys.class_cost_sb.pojo.Expend;
import com.zys.class_cost_sb.service.ExpendService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
/**
 * @Author hong
 * @Date 2019/12/17
 */
@Service
public class ExpendServiceImpl extends ServiceImpl<ExpendMapper, Expend> implements ExpendService{

    @Override
    public List<Map<String, Object>> findAllByUserId(Integer userId) {
        return ((ExpendMapper) baseMapper).findAllByUserId(userId);
    }

    @Override
    public List<Map<String, Object>> findStuExpend(Integer id) {
        return ((ExpendMapper) baseMapper).findStuExpend(id);
    }
}
