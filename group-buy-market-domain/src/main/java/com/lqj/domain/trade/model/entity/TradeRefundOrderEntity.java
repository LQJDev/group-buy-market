package com.lqj.domain.trade.model.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;

/**
 * @Author 李岐鉴
 * @Date 2026/2/1
 * @Description TradeRefundOrderEntity 类
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class TradeRefundOrderEntity {

    private String userId;

    private String teamId;

    private String orderId;

    private Long activityId;
}
