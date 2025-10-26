package com.lqj.domain.activity.model.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @Author 李岐鉴
 * @Date 2025/10/25
 * @Description 商品实体
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class MarketProductEntity {

    private String userId;

    private String goodsId;

    private String source;

    private String channel;
}
