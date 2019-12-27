package com.zys.class_cost_sb.controoler;

import com.zys.class_cost_sb.constant.SysConstant;
import com.zys.class_cost_sb.exception.ExceptionCast;
import com.zys.class_cost_sb.model.request.Pwd;
import com.zys.class_cost_sb.model.request.UserExt;
import com.zys.class_cost_sb.model.response.CommonCode;
import com.zys.class_cost_sb.model.response.ResponseResult;
import com.zys.class_cost_sb.model.response.UserCode;
import com.zys.class_cost_sb.pojo.Classes;
import com.zys.class_cost_sb.pojo.User;
import com.zys.class_cost_sb.service.ClassesService;
import com.zys.class_cost_sb.service.UserService;
import com.zys.class_cost_sb.utils.IdentifyingCodeUtils;
import com.zys.class_cost_sb.utils.Md5Utils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * @Author hong
 * @Date 2019/12/17
 */
@Controller
public class UserController extends BaseController{


    @Autowired
    private UserService userService;

    @Autowired
    private ClassesService classesService;


    @PostMapping("/user/login")
    @ResponseBody
    public ResponseResult login(UserExt userExt){

        // 对比验证码
        boolean isIdentical = IdentifyingCodeUtils.contrastIdentifyingCode(session, userExt.getIdentifyingCode());

        // 验证码不一致
        if(!isIdentical){
            ExceptionCast.cast(UserCode.IDENTIFYING_CODE_NOT_IDENTICAL);
        }

        User user = userService.login(userExt);

        session.setAttribute("user",user);

        return new ResponseResult(CommonCode.SUCCESS);
    }

    @GetMapping("/login")
    public String toLogin(){
        return "login";
    }

    @GetMapping("/register")
    public String toRegister(Model model){

        // 查询所有班级
        List<Classes> classes = classesService.list();

        model.addAttribute("classes",classes);

        return "register";
    }


    @PostMapping("/user/register")
    @ResponseBody
    public ResponseResult register(UserExt userExt){

        // 对比验证码
        boolean isIdentical = IdentifyingCodeUtils.contrastIdentifyingCode(session, userExt.getIdentifyingCode());

        // 验证码不一致
        if(!isIdentical){
            ExceptionCast.cast(UserCode.IDENTIFYING_CODE_NOT_IDENTICAL);
        }

        // 注册
        userService.register(userExt);

        return new ResponseResult(CommonCode.SUCCESS);
    }


    @GetMapping("/user/findClassConstManager")
    public String findClassConstManager(Model model){

        // 查询所有的系统管理员
        List<Map<String, Object>> users = userService.findAllByRoleType(SysConstant.CLASS_COST_MANAGER_ROLE);

        model.addAttribute("users",users);
        return "manager/sys/user/classCostManager/index";
    }


    @GetMapping("/user/toEdit")
    public String toEdit(Integer id, Model model) {

        User user = null;
        if (null != id) {
            user = userService.getById(id);
        }else{
            // 防止前端报错
            user = new User();
        }
        model.addAttribute("user",user);

        return "manager/sys/user/classCostManager/edit";

    }

    @DeleteMapping("/user/delClassConstManager")
    @ResponseBody
    public ResponseResult delClassConstManager(Integer id){

        // 删除系统管理员
        userService.delClassConstManager(id);

        return new ResponseResult(CommonCode.SUCCESS);
    }


    @PostMapping("/user/save")
    @ResponseBody
    public ResponseResult save(User user){

        if(null == user.getId()){
            user.setCreateTime(new Date());
            user.setRoleType(SysConstant.CLASS_COST_MANAGER_ROLE);
            String defaultPassword = Md5Utils.getMd5(SysConstant.PASSWORD_SALT + SysConstant.PASSWORD_SALT);
            user.setPassword(defaultPassword);
        }

        userService.saveOrUpdate(user);

        return new ResponseResult(CommonCode.SUCCESS);
    }


    @GetMapping("/user/toUpdatePwd")
    public String toUpdatePwd(){
        return "manager/common/sysSetting/updatePwd";
    }

    @PostMapping("/user/updatePwd")
    @ResponseBody
    public ResponseResult updatePwd(Pwd pwd){

        // 从session中获取当前用户
        User user = (User) session.getAttribute("user");

        userService.updatePwd(pwd,user.getId());

        return new ResponseResult(CommonCode.SUCCESS);
    }

    /**
     * 查询本班级注册审核人员
     * @return
     */
    @GetMapping("/user/findStuByStatusAndClassId")
    public String findStuByStatusAndClassId(Model model,Integer status,Integer classId){

        // 从session中获取当前用户
        User user = (User) session.getAttribute("user");

        // 校验当前用户是否存在此班级
        Integer countClassesNumber = classesService.countByUserIdAndClassId(user.getId(),classId);

        if(countClassesNumber <= 0){
            ExceptionCast.cast(CommonCode.ILLEGAL_OPERATION);
        }


        List<Map<String,Object>> users = userService.findStuByStatusAndClassId(classId,status,SysConstant.STUDENT_ROLE);

        model.addAttribute("users",users);
        model.addAttribute("status",status);

        return "manager/stu/user/index";
    }

    @GetMapping("/user/active")
    @ResponseBody
    public ResponseResult active(Integer id){
        userService.active(id);
        return new ResponseResult(CommonCode.SUCCESS);
    }

    @GetMapping("/user/resetPwd")
    @ResponseBody
    public ResponseResult resetPwd(Integer id){
        userService.resetPwd(id);
        return new ResponseResult(UserCode.RESET_PWD_SUCCESS);
    }


    /**
     *
     * @param model
     * @return
     */
    @GetMapping("/user/classFellow")
    public String findClassFellow(Model model){

        // 从session中获取当前用户
        User user = (User) session.getAttribute("user");

        List<Map<String,Object>> users =  userService.findClassFellow(user.getId());

        model.addAttribute("users",users);

        return "manager/stu/user/classFellow";
    }

    @GetMapping("/user/logout")
    public String logout(){
        session.invalidate();

        return "redirect:/login";
    }
}
