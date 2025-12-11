package com.lqj.domain.trade.model.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

/**
 * @Author 李岐鉴
 * @Date 2025/12/10
 * @Description 拼团交易结算规则命令
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class TradeSettlementRuleCommandEntity {

    /**
     * 订单来源
     */
    private String source;

    /**
     * 订单渠道
     */
    private String channel;

    /**
     * 用户ID
     */
    private String userId;

    /**
     * 外部订单号
     */
    private String outTradeNo;

    /**
     * 外部交易时间
     */
    private Date outTradeTime;
}
