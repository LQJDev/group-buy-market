package com.lqj.domain.trade.model.entity;

import com.lqj.types.enums.ActivityStatusEnumVO;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

/**
 * @Author 李岐鉴
 * @Date 2025/12/5
 * @Description 拼团活动实体对象
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class GroupBuyActivityEntity {


    /* 活动ID */
    private Long activityId;

    /* 活动名称 */
    private String activityName;

    /* 折扣ID */
    private String discountId;

    /* 拼团方式 */
    private Integer groupType;

    /* 拼团次数限制 */
    private Integer takeLimitCount;

    /* 拼团目标 */
    private Integer target;

    /* 拼团时长（分钟）*/
    private Integer validTime;

    /* 拼团状态 （0创建，1生效，2过期，3废弃）*/
    private ActivityStatusEnumVO status;

    /* 活动开始时间 */
    private Date startTime;

    /* 活动结束时间 */
    private Date endTime;

    /* 人群标签规则标识 */
    private String tagId;

    /* 人群标签作用范围 */
    private String tagScope;


}
