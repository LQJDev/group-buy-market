package com.lqj.domain.trade.model.entity;

import com.lqj.domain.trade.model.valobj.TradeOrderStatusEnumVO;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

/**
 * @Author 李岐鉴
 * @Date 2025/11/10
 * @Description 拼团，预购订单营销实体对象
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class MarketPayOrderEntity {

    /** 拼团组队id */
    private String teamId;

    /** 订单id */
    private String orderId;

    /** 折扣金额 */
    private BigDecimal deductionPrice;

    /** 订单状态 */
    private TradeOrderStatusEnumVO tradeOrderStatusEnumVO;
}
