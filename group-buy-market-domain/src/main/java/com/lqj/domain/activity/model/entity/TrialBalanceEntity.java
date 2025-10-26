package com.lqj.domain.activity.model.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.Date;

/**
 * @Author 李岐鉴
 * @Date 2025/10/25
 * @Description TrialBalanceEntity 类
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class TrialBalanceEntity {

    private String goodsId;

    private String goodsName;

    private BigDecimal originalPrice;

    private BigDecimal deductionPrice;

    private Integer targetCount;

    private Date startTime;

    private Date endTime;

    private Boolean isVisible;

    private Boolean isEnable;
}
