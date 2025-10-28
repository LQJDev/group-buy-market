package com.lqj.domain.activity.model.valobj;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @Author 李岐鉴
 * @Date 2025/10/28
 * @Description SCSkuActivityVO 类
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class SCSkuActivityVO {
    /** 渠道 */
    private String source;
    /** 来源 */
    private String channel;
    /** 活动ID */
    private Long activityId;
    /** 商品ID */
    private String goodsId;
}
