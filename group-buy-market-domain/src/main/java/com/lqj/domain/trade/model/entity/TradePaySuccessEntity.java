package com.lqj.domain.trade.model.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @Author 李岐鉴
 * @Date 2025/12/8
 * @Description 交易支付订单实体对象
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class TradePaySuccessEntity {

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
     *
     */
    private String outTradeNo;
}
