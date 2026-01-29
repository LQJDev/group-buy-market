package com.lqj.domain.activity.service.trial;

import cn.bugstack.wrench.design.framework.tree.AbstractMultiThreadStrategyRouter;
import com.lqj.domain.activity.adapter.repository.IActivityRepository;
import com.lqj.domain.activity.model.entity.TrialBalanceEntity;
import com.lqj.domain.activity.service.trial.factory.DefaultActivityStrategyFactory;


import javax.annotation.Resource;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeoutException;


/**
 * @Author 李岐鉴
 * @Date 2025/10/25
 * @Description AbstractGroupBuyMarketSupport 类
 */
public abstract class AbstractGroupBuyMarketSupport<MarketProductEntity, DynamicContext, TrialBalanceEntity> extends AbstractMultiThreadStrategyRouter<MarketProductEntity, DynamicContext, TrialBalanceEntity> {

    @Resource
    protected IActivityRepository repository;


    protected long timeout = 5000;
    
    @Override
    protected void multiThread(MarketProductEntity requestParameter, DynamicContext dynamicContext) throws ExecutionException, InterruptedException, TimeoutException {
        // 多线程处理
    }
}
