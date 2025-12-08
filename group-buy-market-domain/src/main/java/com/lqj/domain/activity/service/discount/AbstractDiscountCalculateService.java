package com.lqj.domain.activity.service.discount;

import com.lqj.domain.activity.adapter.repository.IActivityRepository;
import com.lqj.domain.activity.model.valobj.DiscountTypeEnum;
import com.lqj.domain.activity.model.valobj.GroupBuyActivityDiscountVO;
import lombok.extern.slf4j.Slf4j;

import javax.annotation.Resource;
import java.math.BigDecimal;

/**
 * @Author 李岐鉴
 * @Date 2025/10/27
 * @Description AbstractDiscountCalculateService 类
 */
@Slf4j
public abstract class AbstractDiscountCalculateService implements IDiscountCalculateService{


    @Resource
    private IActivityRepository repository;

    @Override
    public BigDecimal calculate(String userId, BigDecimal originalPrice, GroupBuyActivityDiscountVO.GroupBuyDiscount groupBuyDiscount) {
        // 人群标签过滤
        if (DiscountTypeEnum.TAG.equals(groupBuyDiscount.getDiscountType())) {
            boolean isCrowRange = filterTagId(userId, groupBuyDiscount.getTagId());
            if (!isCrowRange) {
                log.info("折扣优惠计算拦截，用户不在优惠人群标签范围之内 userId: {}", userId);
                return originalPrice;
            }
        }
        // 折扣优惠计算
        return doCalculate(originalPrice, groupBuyDiscount);
    }

    protected abstract BigDecimal doCalculate(BigDecimal originalPrice, GroupBuyActivityDiscountVO.GroupBuyDiscount groupBuyDiscount);


    // 人群标签过滤
    private boolean filterTagId(String userId, String tagId) {
        return repository.isTagCrowRange(tagId, userId);
    }


}
