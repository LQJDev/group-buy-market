package com.lqj.domain.activity.service.discount;

import com.lqj.domain.activity.model.valobj.GroupBuyActivityDiscountVO;

import java.math.BigDecimal;

/**
 * @Author 李岐鉴
 * @Date 2025/10/27
 * @Description 折扣计算接口
 */
public interface IDiscountCalculateService {

    BigDecimal calculate(String userId, BigDecimal originalPrice, GroupBuyActivityDiscountVO.GroupBuyDiscount groupBuyDiscount);

}
