package com.lqj.domain.activity.service.trial.node;

import com.lqj.domain.activity.model.entity.MarketProductEntity;
import com.lqj.domain.activity.model.entity.TrialBalanceEntity;
import com.lqj.domain.activity.model.valobj.GroupBuyActivityDiscountVO;
import com.lqj.domain.activity.model.valobj.SkuVO;
import com.lqj.domain.activity.service.trial.AbstractGroupBuyMarketSupport;
import com.lqj.domain.activity.service.trial.factory.DefaultActivityStrategyFactory;
import com.lqj.types.design.framework.tree.StrategyHandler;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.math.BigDecimal;

/**
 * @Author 李岐鉴
 * @Date 2025/10/26
 * @Description EndNode 类
 */
@Service
@Slf4j
public class EndNode extends AbstractGroupBuyMarketSupport<MarketProductEntity, DefaultActivityStrategyFactory.DynamicContext, TrialBalanceEntity> {


    @Override
    protected TrialBalanceEntity doApply(MarketProductEntity requestParameter, DefaultActivityStrategyFactory.DynamicContext dynamicContext) throws Exception {
        GroupBuyActivityDiscountVO groupBuyActivityDiscountVO = dynamicContext.getGroupBuyActivityDiscountVO();
        SkuVO skuVO = dynamicContext.getSkuVO();
        BigDecimal deductionPrice = dynamicContext.getDeductionPrice();
        return TrialBalanceEntity.builder()
                .goodsId(skuVO.getGoodsId())
                .goodsName(skuVO.getGoodsName())
                .originalPrice(skuVO.getOriginalPrice())
                .deductionPrice(deductionPrice)
                .targetCount(groupBuyActivityDiscountVO.getTakeLimitCount())
                .startTime(groupBuyActivityDiscountVO.getStartTime())
                .endTime(groupBuyActivityDiscountVO.getEndTime())
                .isVisible(dynamicContext.isVisible())
                .isEnable(dynamicContext.isEnable())
                .groupBuyActivityDiscountVO(groupBuyActivityDiscountVO)
                .build();
    }


    @Override
    public StrategyHandler<MarketProductEntity, DefaultActivityStrategyFactory.DynamicContext, TrialBalanceEntity> get(MarketProductEntity requestParameter, DefaultActivityStrategyFactory.DynamicContext dynamicContext) throws Exception {
        return defaultStrategyHandler;
    }
}
