package com.lqj.domain.activity.model.entity;

import com.lqj.domain.activity.model.valobj.GroupBuyActivityDiscountVO;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.Date;

/**
 * @Author 李岐鉴
 * @Date 2025/10/25
 * @Description 试算结果实体对象（给用户展示拼团可获得的优惠信息）
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

    private BigDecimal payPrice;

    private Integer targetCount;

    private Date startTime;

    private Date endTime;

    private Boolean isVisible;

    private Boolean isEnable;

    private GroupBuyActivityDiscountVO groupBuyActivityDiscountVO;
}
