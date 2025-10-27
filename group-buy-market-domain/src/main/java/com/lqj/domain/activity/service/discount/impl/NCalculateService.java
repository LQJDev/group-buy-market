package com.lqj.domain.activity.service.discount.impl;

import com.lqj.domain.activity.model.valobj.GroupBuyActivityDiscountVO;
import com.lqj.domain.activity.service.discount.AbstractDiscountCalculateService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

/**
 * @Author 李岐鉴
 * @Date 2025/10/27
 * @Description NCalculateService 类
 */
@Service("NC")
@Slf4j
public class NCalculateService extends AbstractDiscountCalculateService {


    @Override
    protected BigDecimal doCalculate(BigDecimal originalPrice, GroupBuyActivityDiscountVO.GroupBuyDiscount groupBuyDiscount) {
        log.info("优惠策略折扣计算:{}", groupBuyDiscount.getDiscountType().getCode());
        // 折扣表达式 - 直接为优惠后的金额
        String marketExpr = groupBuyDiscount.getMarketExpr();
        // n元购
        return new BigDecimal(marketExpr);
    }
}
