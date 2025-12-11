package com.lqj.domain.trade.model.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @Author 李岐鉴
 * @Date 2025/11/24
 * @Description TradeRuleCommandEntity 类
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class TradeLockRuleCommandEntity {

    private String userId;

    private Long activityId;

}
