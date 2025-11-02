package com.lqj.domain.activity.service.trial.node;

import com.lqj.domain.activity.adapter.repository.IActivityRepository;
import com.lqj.domain.activity.model.entity.MarketProductEntity;
import com.lqj.domain.activity.model.entity.TrialBalanceEntity;
import com.lqj.domain.activity.model.valobj.GroupBuyActivityDiscountVO;
import com.lqj.domain.activity.service.trial.AbstractGroupBuyMarketSupport;
import com.lqj.domain.activity.service.trial.factory.DefaultActivityStrategyFactory;
import com.lqj.types.design.framework.tree.StrategyHandler;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @Author 李岐鉴
 * @Date 2025/11/2
 * @Description TagNode 类
 */
@Slf4j
@Service
public class TagNode extends AbstractGroupBuyMarketSupport<MarketProductEntity, DefaultActivityStrategyFactory.DynamicContext, TrialBalanceEntity> {

    @Resource
    private EndNode endNode;

    @Resource
    private IActivityRepository repository;



    @Override
    protected TrialBalanceEntity doApply(MarketProductEntity requestParameter, DefaultActivityStrategyFactory.DynamicContext dynamicContext) throws Exception {
        GroupBuyActivityDiscountVO groupBuyActivityDiscountVO = dynamicContext.getGroupBuyActivityDiscountVO();
        String tagId = groupBuyActivityDiscountVO.getTagId();
        boolean visible = groupBuyActivityDiscountVO.isVisible();
        boolean enable = groupBuyActivityDiscountVO.isEnable();

        // 人群标签为空，则走默认值
        if (StringUtils.isBlank(tagId)) {
            dynamicContext.setVisible(true);
            dynamicContext.setEnable(true);
            return router(requestParameter, dynamicContext);
        }
        boolean isWithin = repository.isTagCrowRange(tagId, requestParameter.getUserId());
        dynamicContext.setVisible(visible || isWithin);
        dynamicContext.setEnable(enable || isWithin);
        return router(requestParameter, dynamicContext);
    }

    @Override
    public StrategyHandler<MarketProductEntity, DefaultActivityStrategyFactory.DynamicContext, TrialBalanceEntity> get(MarketProductEntity requestParameter, DefaultActivityStrategyFactory.DynamicContext dynamicContext) throws Exception {
        return endNode;
    }
}
