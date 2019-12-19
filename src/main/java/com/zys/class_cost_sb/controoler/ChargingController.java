package com.zys.class_cost_sb.controoler;

import com.zys.class_cost_sb.model.response.CommonCode;
import com.zys.class_cost_sb.model.response.ResponseResult;
import com.zys.class_cost_sb.pojo.Charging;
import com.zys.class_cost_sb.pojo.User;
import com.zys.class_cost_sb.service.ChargingService;
import com.zys.class_cost_sb.service.ClassesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;
import java.util.Map;

/**
 * @Author hong
 * @Date 2019/12/18
 */
@Controller
@RequestMapping("/charging")
public class ChargingController extends BaseController{

    @Autowired
    private ChargingService chargingService;

    @Autowired
    private ClassesService classesService;

    @GetMapping("/findAll")
    public String findAll(Model model){

        // 从session中获取当前用户
        User user = (User) session.getAttribute("user");

        List<Map<String,Object>> chargings = chargingService.findAllByUserId(user.getId());

        model.addAttribute("chargings",chargings);

        return "manager/ccm/charging/index";
    }


    @GetMapping("/toEdit")
    public String toEdit(Integer id, Model model) {

        Charging charging = null;
        if (null != id) {
            charging = chargingService.getById(id);
        }else{
            // 防止前端报错
            charging = new Charging();
        }
        model.addAttribute("charging",charging);


        // 从session中获取当前用户
        User user = (User) session.getAttribute("user");

        // 查询班费负责人负责的班级
        List<Map<String,Object>> classes =  classesService.findAllByUserId(user.getId());

        model.addAttribute("classes",classes);

        return "manager/ccm/charging/edit";

    }


    @PostMapping("/save")
    @ResponseBody
    public ResponseResult save(Charging charging){

        // 从session中获取当前用户
        User user = (User) session.getAttribute("user");

        charging.setUserId(user.getId());

        chargingService.saveCharging(charging);

        return new ResponseResult(CommonCode.SUCCESS);
    }




}
