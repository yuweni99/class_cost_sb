package com.zys.class_cost_sb.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zys.class_cost_sb.constant.SysConstant;
import com.zys.class_cost_sb.exception.ExceptionCast;
import com.zys.class_cost_sb.mapper.ClassesMapper;
import com.zys.class_cost_sb.mapper.UserMapper;
import com.zys.class_cost_sb.model.response.ClassesCode;
import com.zys.class_cost_sb.pojo.Classes;
import com.zys.class_cost_sb.service.ClassesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * @Author hong
 * @Date 2019/12/17
 */
@Service
public class ClassesServiceImpl extends ServiceImpl<ClassesMapper, Classes> implements ClassesService {

    @Autowired
    private UserMapper userMapper;

    @Override
    public List<Map<String, Object>> findAll() {
        return ((ClassesMapper) baseMapper).findAll();
    }

    @Override
    public void del(Integer id) {
        // 查询当前班级是否存在学生
        Integer stuNumber =  userMapper.countStuNumberByClassId(id, SysConstant.STUDENT_ROLE);

        if(stuNumber > 0){
            ExceptionCast.cast(ClassesCode.CLASSES_EXISTS_STU);
        }

        // 删除班级
        baseMapper.deleteById(id);

    }

    @Override
    public List<Map<String, Object>> findAllByUserId(Integer userId) {
        return ((ClassesMapper) baseMapper).findAllByUserId(userId);
    }

    @Override
    public Integer countByUserIdAndClassId(Integer userId, Integer classId) {
        return ((ClassesMapper) baseMapper).countByUserIdAndClassId(userId,classId);
    }
}
