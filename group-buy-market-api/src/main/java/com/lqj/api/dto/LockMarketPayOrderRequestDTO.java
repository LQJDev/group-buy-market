package com.lqj.api.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @Author 李岐鉴
 * @Date 2025/11/17
 * @Description LocMarketPayOrderRequestDTO 类
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class LockMarketPayOrderRequestDTO {

    // 用户ID
    private String userId;
    // 拼团组队ID
    private String teamId;
    // 活动ID
    private Long activityId;
    // 商品ID
    private String goodsId;
    // 渠道
    private String source;
    // 来源
    private String channel;
    // 外部交易单号
    private String outTradeNo;
    // 回调地址
    private String notifyUrl;
}
