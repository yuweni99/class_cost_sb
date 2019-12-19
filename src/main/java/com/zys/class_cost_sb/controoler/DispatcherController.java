package com.zys.class_cost_sb.controoler;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

/**
 * @Author hong
 * @Date 2019/12/9
 * 分发器
 */
@Controller
public class DispatcherController {


    /**
     * 分发错误页面
     * @param path
     * @return
     */
    @GetMapping("/error/{path}")
    public String errorDispatcher(@PathVariable String path){
        return "error/" + path;
    }


    @GetMapping("/manager/home")
    public String homeDispatcher(){
        return "manager/home";
    }
}
