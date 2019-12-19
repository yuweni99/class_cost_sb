package com.zys.class_cost_sb.model.request;

import lombok.Data;

/**
 * @Author hong
 * @Date 2019/12/19
 */
@Data
public class ChargingHistoryRequest {

    /**
     * 缴费状态
     */
    private Integer status;

    /**
     * 需缴费id
     */
    private Integer chargingId;

}
