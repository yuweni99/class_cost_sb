package com.zys.class_cost_sb.controoler;

import com.zys.class_cost_sb.model.response.CommonCode;
import com.zys.class_cost_sb.model.response.ResponseResult;
import com.zys.class_cost_sb.pojo.Expend;
import com.zys.class_cost_sb.pojo.User;
import com.zys.class_cost_sb.service.ClassesService;
import com.zys.class_cost_sb.service.ExpendService;
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
@RequestMapping("/expend")
public class ExpendController extends BaseController {

    @Autowired
    private ExpendService expendService;

    @Autowired
    private ClassesService classesService;

    /**
     * 查询所有的支出记录(单个班级)
     * @param model
     * @return
     */
    @GetMapping("/findAll")
    public String findAll(Model model){

        // 从session中获取当前用户
        User user = (User) session.getAttribute("user");

        List<Map<String,Object>> expends = expendService.findAllByUserId(user.getId());

        model.addAttribute("expends",expends);

        return "manager/ccm/expend/index";

    }

    /**
     * 获取学生所在班级班费支出记录
     * @return
     */
    @GetMapping("/findStuExpend")
    public String findStuExpend(Model model){

        // 从session中获取当前用户
        User user = (User) session.getAttribute("user");

        List<Map<String,Object>> expends = expendService.findStuExpend(user.getId());

        model.addAttribute("expends",expends);

        return "manager/stu/expend/index";
    }





    @GetMapping("/toEdit")
    public String toEdit(Integer id, Model model) {

        Expend expend = null;
        if (null != id) {
            expend = expendService.getById(id);
        }else{
            // 防止前端报错
            expend = new Expend();
        }

        model.addAttribute("expend",expend);

        // 从session中获取当前用户
        User user = (User) session.getAttribute("user");

        // 查询班费负责人负责的班级
        List<Map<String,Object>> classes =  classesService.findAllByUserId(user.getId());

        model.addAttribute("classes",classes);


        return "manager/ccm/expend/edit";

    }


    @DeleteMapping("/del")
    @ResponseBody
    public ResponseResult del(Integer id){
        expendService.removeById(id);
        return new ResponseResult(CommonCode.SUCCESS);
    }

    @PostMapping("/save")
    @ResponseBody
    public ResponseResult save(Expend expend){

        // 从session中获取当前用户
        User user = (User) session.getAttribute("user");

        if(null == expend.getId()){
            expend.setUserId(user.getId());
            expend.setCreateTime(new Date());
        }

        expendService.saveOrUpdate(expend);

        return new ResponseResult(CommonCode.SUCCESS);
    }

}
