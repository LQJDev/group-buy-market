package com.lqj.domain.trade.model.entity;

import com.lqj.types.enums.GroupBuyOrderEnumVO;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

/**
 * @Author 李岐鉴
 * @Date 2025/12/10
 * @Description 拼团交易结算规则反馈
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class TradeSettlementRuleFilterBackEntity {

    /**
     * 拼单组队id
     */
    private String teamId;

    /**
     * 拼单活动id
     */
    private Long activityId;

    /**
     * 拼单目标数量
     */
    private Integer targetCount;

    /**
     * 拼单完成数量
     */
    private Integer completeCount;

    /**
     * 锁单数量
     */
    private Integer lockCount;

    /**
     * 拼单状态
     */
    private GroupBuyOrderEnumVO status;

    /**
     * 拼单开始时间
     */
    private Date validStartTime;

    /**
     * 拼单结束时间
     */
    private Date validEndTime;
}
