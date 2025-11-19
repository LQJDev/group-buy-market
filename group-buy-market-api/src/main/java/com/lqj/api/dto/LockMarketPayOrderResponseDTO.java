package com.lqj.api.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

/**
 * @Author 李岐鉴
 * @Date 2025/11/17
 * @Description LocMarketPayOrderResponseDTO 类
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class LockMarketPayOrderResponseDTO {

    // 预购订单ID
    private String orderId;

    // 折扣金额
    private BigDecimal deductionPrice;

    // 交易订单状态
    private Integer tradeOrderStatus;
}
