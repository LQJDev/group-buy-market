package com.lqj.domain.trade.model.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @Author 李岐鉴
 * @Date 2025/12/8
 * @Description 交易结算订单实体
 */

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class TradePaySettlementEntity {

    /**
     * 来源
     */
    private String source;

    /**
     * 渠道
     */
    private String channel;

    /**
     * 用户id
     */
    private String userId;

    /**
     * 拼团组队id
     */
    private String teamId;

    /**
     * 拼团活动id
     */
    private Long activityId;

    /**
     * 外部交易单号
     */
    private String outTradeNo;
}
