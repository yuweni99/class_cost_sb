package com.zys.class_cost_sb.controoler;

import com.zys.class_cost_sb.constant.SysConstant;
import com.zys.class_cost_sb.model.response.CommonCode;
import com.zys.class_cost_sb.model.response.ResponseResult;
import com.zys.class_cost_sb.pojo.Classes;
import com.zys.class_cost_sb.pojo.User;
import com.zys.class_cost_sb.service.ClassesService;
import com.zys.class_cost_sb.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * @Author hong
 * @Date 2019/12/18
 */
@Controller
@RequestMapping("/classes")
public class ClassesController extends BaseController {

    @Autowired
    private ClassesService classesService;

    @Autowired
    private UserService userService;

    @GetMapping("/findAll")
    public String findAll(Model model) {

        List<Map<String, Object>> classes = classesService.findAll();

        model.addAttribute("classes", classes);

        return "manager/sys/classes/index";
    }

    @GetMapping("/toEdit")
    public String toEdit(Integer id, Model model) {

        Classes classes = null;
        if (null != id) {
            classes = classesService.getById(id);
        }else{
            // 防止前端报错
            classes = new Classes();
        }
        model.addAttribute("classes",classes);

        // 查询班费负责人
        List<Map<String, Object>> users = userService.findAllByRoleType(SysConstant.CLASS_COST_MANAGER_ROLE);

        model.addAttribute("users",users);

        return "manager/sys/classes/edit";

    }

    @DeleteMapping("/del")
    @ResponseBody
    public ResponseResult del(Integer id){

        classesService.del(id);

        return new ResponseResult(CommonCode.SUCCESS);
    }

    @PostMapping("/save")
    @ResponseBody
    public ResponseResult save(Classes classes){

        if(null == classes.getId()){
            classes.setCreateTime(new Date());
        }
        classesService.saveOrUpdate(classes);

        return new ResponseResult(CommonCode.SUCCESS);
    }

    @GetMapping("/findClassCostManagerClassList")
    public String findClassCostManagerClassList(Model model){

        // 从session中获取当前用户
        User user = (User) session.getAttribute("user");

        List<Map<String, Object>> classes = classesService.findAllByUserId(user.getId());

        model.addAttribute("classes",classes);

        return "manager/sys/classes/classCostManagerClassList";
    }


}
