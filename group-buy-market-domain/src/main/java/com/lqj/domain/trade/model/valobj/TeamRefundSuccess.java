package com.lqj.domain.trade.model.valobj;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @Author 李岐鉴
 * @Date 2026/2/3
 * @Description TeamRefundSuccess 类
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class TeamRefundSuccess {

    /**
     * 退单类型
     */
    private String type;

    /**
     * 用户ID
     */
    private String userId;

    /**
     * 拼单组队ID
     */
    private String teamId;

    /**
     * 活动ID
     */
    private Long activityId;

    /**
     * 预购订单ID
     */
    private String orderId;


}
