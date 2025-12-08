package com.lqj.domain.trade.model.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

/**
 * @Author 李岐鉴
 * @Date 2025/11/10
 * @Description PayDiscountEntity 类
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class PayDiscountEntity {

    /** 渠道 */
    private String source;
    /** 来源 */
    private String channel;
    /** 商品ID */
    private String goodsId;
    /** 商品名称 */
    private String goodsName;
    /** 原始价格 */
    private BigDecimal originalPrice;
    /** 折扣金额 */
    private BigDecimal deductionPrice;
    /** 实际支付金额 */
    private BigDecimal payPrice;
    /** 外部交易单号-确保外部调用唯一幂等 */
    private String outTradeNo;
}
