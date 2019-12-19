package com.zys.class_cost_sb.controoler;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.ModelAttribute;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * @Author hong
 * @Date 2019/12/9
 */
public class BaseController {

    /**
     * 日志
     */
    protected Logger logger = LoggerFactory.getLogger(this.getClass());

    protected HttpServletRequest request;

    protected HttpServletResponse response;

    protected HttpSession session;

    @ModelAttribute
    public void init(HttpServletRequest request,
                     HttpServletResponse response,
                     HttpSession session){
        this.request = request;
        this.response = response;
        this.session = session;
    }

}
