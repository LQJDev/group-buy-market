package com.lqj.domain.activity.service.trial.node;

import com.alibaba.fastjson.JSON;
import com.lqj.domain.activity.model.entity.MarketProductEntity;
import com.lqj.domain.activity.model.entity.TrialBalanceEntity;
import com.lqj.domain.activity.model.valobj.GroupBuyActivityDiscountVO;
import com.lqj.domain.activity.model.valobj.SkuVO;
import com.lqj.domain.activity.service.trial.AbstractGroupBuyMarketSupport;
import com.lqj.domain.activity.service.trial.factory.DefaultActivityStrategyFactory;
import com.lqj.domain.activity.service.trial.thread.QueryGroupBuyActivityDiscountVOThreadTask;
import com.lqj.domain.activity.service.trial.thread.QuerySkuVOFromDBThreadTask;
import com.lqj.types.design.framework.tree.StrategyHandler;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.concurrent.FutureTask;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * @Author 李岐鉴
 * @Date 2025/10/26
 * @Description MarketNode 类
 */
@Service
@Slf4j
public class MarketNode extends AbstractGroupBuyMarketSupport<MarketProductEntity, DefaultActivityStrategyFactory.DynamicContext, TrialBalanceEntity> {

    @Resource
    private ThreadPoolExecutor threadPoolExecutor;

    @Resource
    private EndNode endNode;

    @Override
    protected void multiThread(MarketProductEntity requestParameter, DefaultActivityStrategyFactory.DynamicContext dynamicContext) throws Exception {
        QuerySkuVOFromDBThreadTask querySkuVOFromDBThreadTask = new
                QuerySkuVOFromDBThreadTask(requestParameter.getGoodsId(), repository);

        QueryGroupBuyActivityDiscountVOThreadTask queryGroupBuyActivityDiscountVOThreadTask = new
                QueryGroupBuyActivityDiscountVOThreadTask(requestParameter.getSource(), requestParameter.getChannel(), repository);

        FutureTask<SkuVO> skuVOFutureTask = new FutureTask<>(querySkuVOFromDBThreadTask);
        FutureTask<GroupBuyActivityDiscountVO> groupBuyActivityDiscountVOFutureTask =
                new FutureTask<>(queryGroupBuyActivityDiscountVOThreadTask);

        threadPoolExecutor.execute(skuVOFutureTask);
        threadPoolExecutor.execute(groupBuyActivityDiscountVOFutureTask);
        GroupBuyActivityDiscountVO groupBuyActivityDiscountVO = groupBuyActivityDiscountVOFutureTask.get(timeout, TimeUnit.MINUTES);
        dynamicContext.setGroupBuyActivityDiscountVO(groupBuyActivityDiscountVO);
        SkuVO skuVO = skuVOFutureTask.get(timeout, TimeUnit.MINUTES);
        dynamicContext.setSkuVO(skuVO);
        log.info("拼团商品查询试算服务-MarketNode userId:{} 异步线程加载数据「GroupBuyActivityDiscountVO、SkuVO」完成", requestParameter.getUserId());
    }

    @Override
    protected TrialBalanceEntity doApply(MarketProductEntity requestParameter, DefaultActivityStrategyFactory.DynamicContext dynamicContext) throws Exception {
        log.info("拼团商品查询试算服务-MarketNode userId:{} requestParameter:{}", requestParameter.getUserId(), JSON.toJSONString(requestParameter));
        return router(requestParameter, dynamicContext);
    }

    @Override
    public StrategyHandler<MarketProductEntity, DefaultActivityStrategyFactory.DynamicContext, TrialBalanceEntity> get(MarketProductEntity requestParameter, DefaultActivityStrategyFactory.DynamicContext dynamicContext) throws Exception {
        return endNode;
    }
}
