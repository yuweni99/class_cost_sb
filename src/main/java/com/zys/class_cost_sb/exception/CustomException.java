package com.zys.class_cost_sb.exception;


import com.zys.class_cost_sb.model.response.ResultCode;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author lib
 * @qq 446630668
 * @date 2019/3/5
 * 自定义异常类
 */
@Data
@NoArgsConstructor
public class CustomException extends RuntimeException {

    private ResultCode resultCode;

    public CustomException(ResultCode resultCode){
        this.resultCode = resultCode;
    }


}
