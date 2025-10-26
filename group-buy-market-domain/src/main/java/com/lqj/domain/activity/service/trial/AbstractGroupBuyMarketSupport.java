package com.lqj.domain.activity.service.trial;

import com.lqj.domain.activity.adapter.repository.IActivityRepository;
import com.lqj.domain.activity.model.entity.TrialBalanceEntity;
import com.lqj.domain.activity.service.trial.factory.DefaultActivityStrategyFactory;
import com.lqj.types.design.framework.tree.AbstractMultiThreadStrategyRouter;

import javax.annotation.Resource;


/**
 * @Author 李岐鉴
 * @Date 2025/10/25
 * @Description AbstractGroupBuyMarketSupport 类
 */
public abstract class AbstractGroupBuyMarketSupport<MarketProductEntity, DynamicContext, TrialBalanceEntity> extends AbstractMultiThreadStrategyRouter<MarketProductEntity, DynamicContext, TrialBalanceEntity> {

    @Resource
    protected IActivityRepository repository;


    protected long timeout = 500;
    
    @Override
    protected void multiThread(MarketProductEntity requestParameter, DynamicContext dynamicContext) throws Exception {
        // 多线程处理
    }
}
