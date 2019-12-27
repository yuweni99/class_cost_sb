package com.zys.class_cost_sb.controoler;

import com.zys.class_cost_sb.constant.SysConstant;
import com.zys.class_cost_sb.exception.ExceptionCast;
import com.zys.class_cost_sb.model.request.ChargingHistoryRequest;
import com.zys.class_cost_sb.model.response.CommonCode;
import com.zys.class_cost_sb.pojo.User;
import com.zys.class_cost_sb.service.ChargingHistoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;
import java.util.Map;

/**
 * @Author hong
 * @Date 2019/12/19
 */
@Controller
@RequestMapping("chargingHistory")
public class ChargingHistoryController extends BaseController {

    @Autowired
    private ChargingHistoryService chargingHistoryService;

    /**
     * 根据状态查询缴费收缴情况
     * @param status
     * @param model
     * @return
     */
    @GetMapping("/findAll")
    public String findAll(ChargingHistoryRequest chargingHistoryRequest, Model model){

        Integer status = chargingHistoryRequest.getStatus();

        if(!(status.equals(SysConstant.PAID_FEE_STATUS) || status.equals(SysConstant.UNPAID_FEE_STATUS))){
            ExceptionCast.cast(CommonCode.ILLEGAL_OPERATION);
        }
        // 查询数据
        List<Map<String,Object>> chargingHistories = chargingHistoryService.findAllByStatus(chargingHistoryRequest);

        model.addAttribute("chargingHistories",chargingHistories);
        model.addAttribute("status",chargingHistoryRequest.getStatus());

        return "manager/ccm/chargingHistory/index";
    }


    /**
     * 获取学生缴费记录
     * @param model
     * @return
     */
    @GetMapping("/findStuChargingHistory")
    public String findStuChargingHistory( Model model){

        // 从session中获取当前用户
        User user = (User) session.getAttribute("user");

        // 查询数据
        List<Map<String,Object>> chargingHistories = chargingHistoryService.findStuChargingHistory(user.getId());

        model.addAttribute("chargingHistories",chargingHistories);

        return "manager/stu/chargingHistory/index";
    }

}
