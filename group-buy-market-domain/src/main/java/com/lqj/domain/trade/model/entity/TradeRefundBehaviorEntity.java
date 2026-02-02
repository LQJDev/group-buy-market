package com.lqj.domain.trade.model.entity;

import lombok.*;

/**
 * @Author 李岐鉴
 * @Date 2026/2/1
 * @Description TradeRefundBehaviorEntity 类
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class TradeRefundBehaviorEntity {
    /**
     * 用户ID
     */
    private String userId;

    /**
     * 订单ID
     */
    private String orderId;

    /**
     * 组队ID
     */
    private String teamId;

    /**
     * 行为枚举
     */
    private TradeRefundBehaviorEnum tradeRefundBehaviorEnum;

    @Getter
    @AllArgsConstructor
    @NoArgsConstructor
    public enum TradeRefundBehaviorEnum {

        SUCCESS("success", "成功"),
        REPEAT("repeat", "重复"),
        FAIL("fail", "失败"),
        ;

        private String code;
        private String info;
    }
}
