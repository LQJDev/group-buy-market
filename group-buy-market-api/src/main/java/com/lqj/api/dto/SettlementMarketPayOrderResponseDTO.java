package com.lqj.api.dto;

import lombok.Data;

/**
 * @Author 李岐鉴
 * @Date 2025/12/13
 * @Description SettlementMarketPayOrderResponseDTO 类
 */
@Data
public class SettlementMarketPayOrderResponseDTO {

    /** 用户ID */
    private String userId;
    /** 拼单组队ID */
    private String teamId;
    /** 活动ID */
    private Long activityId;
    /** 外部交易单号 */
    private String outTradeNo;
}
