package com.zys.class_cost_sb.exception;

import com.alibaba.fastjson.JSON;
import com.zys.class_cost_sb.model.response.CommonCode;
import com.zys.class_cost_sb.model.response.ResponseResult;
import com.zys.class_cost_sb.model.response.ResultCode;
import com.zys.class_cost_sb.utils.WebUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


/**
 * @author lib
 * @qq 446630668
 * @date 2019/3/5
 * 全局异常捕获类
 */
@ControllerAdvice
public class ExceptionCatch {

    /**
     * 日志
     */
    private final Logger LOGGER = LoggerFactory.getLogger(this.getClass());

    /**
     * 自定义异常处理
     * @param customException
     * @return
     */
    @ExceptionHandler({CustomException.class})
    public void customException(CustomException customException,
                                HttpServletRequest request,
                                HttpServletResponse response) throws IOException {

        //获取到错误信息
        ResultCode resultCode = customException.getResultCode();
        //记录日志
        LOGGER.error("catch Exception Message is:" + resultCode.message());

        customException.printStackTrace();

        // 查看请求是否为ajax
        if(WebUtil.isAjax(request)){
            response.setContentType("application/json;charset=UTF-8");
            ResponseResult responseResult = new ResponseResult(resultCode);
            response.getWriter().println(JSON.toJSONString(responseResult));
        }else{
            response.sendRedirect("/error/500");
        }

    }


    @ExceptionHandler(Exception.class)
    public void exception(Exception e, HttpServletRequest request,HttpServletResponse response) throws IOException {
        e.printStackTrace();

        if(WebUtil.isAjax(request)){
            response.setContentType("application/json;charset=UTF-8");
            ResponseResult responseResult = new ResponseResult(CommonCode.SYSTEM_ERROR);
            response.getWriter().println(JSON.toJSONString(responseResult));
        }else{
            response.sendRedirect("/error/500");
        }

    }



}
