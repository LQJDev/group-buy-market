package com.lqj.domain.activity.model.valobj;

import lombok.*;

import java.math.BigDecimal;

/**
 * @Author 李岐鉴
 * @Date 2025/10/26
 * @Description SkuVO 类
 */
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class SkuVO {
    /** 自增 */
    private Long id;

    /** 来源 */
    private String source;

    /** 渠道 */
    private String channel;

    /** 商品ID */
    private String goodsId;

    /** 商品名称 */
    private String goodsName;

    /** 原始价格 */
    private BigDecimal originalPrice;

}
