package com.zys.class_cost_sb.service;

import com.zys.class_cost_sb.pojo.Expend;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;
import java.util.Map;

/**
 * @Author hong
 * @Date 2019/12/17
 */
public interface ExpendService extends IService<Expend> {


    /**
     * 根据班费负责人id查询班费支付信息
     *
     * @param userId 班费负责人id
     * @return
     */
    List<Map<String, Object>> findAllByUserId(Integer userId);

    /**
     * 查询当前学生所在班级支出班费记录
     * @param id 学生id
     * @return
     */
    List<Map<String, Object>> findStuExpend(Integer id);
}
