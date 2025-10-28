package com.lqj.infrastructure.dao.po;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

/**
 * @Author 李岐鉴
 * @Date 2025/10/28
 * @Description SCSkuActivity 类
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class SCSkuActivity {

    /** 自增ID */
    private Long id;
    /** 渠道 */
    private String source;
    /** 来源 */
    private String channel;
    /** 活动ID */
    private Long activityId;
    /** 商品ID */
    private String goodsId;
    /** 创建时间 */
    private Date createTime;
    /** 更新时间 */
    private Date updateTime;

}
