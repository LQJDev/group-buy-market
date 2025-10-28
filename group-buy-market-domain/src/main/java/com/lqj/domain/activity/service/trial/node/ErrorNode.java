package com.lqj.domain.activity.service.trial.node;

import com.alibaba.fastjson.JSON;
import com.lqj.domain.activity.model.entity.MarketProductEntity;
import com.lqj.domain.activity.model.entity.TrialBalanceEntity;
import com.lqj.domain.activity.service.trial.AbstractGroupBuyMarketSupport;
import com.lqj.domain.activity.service.trial.factory.DefaultActivityStrategyFactory;
import com.lqj.types.design.framework.tree.StrategyHandler;
import com.lqj.types.enums.ResponseCode;
import com.lqj.types.exception.AppException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

/**
 * @Author 李岐鉴
 * @Date 2025/10/28
 * @Description 异常节点处理；无营销、流程降级、超时调用等，都可以路由到 ErrorNode 节点统一处理
 */
@Service
@Slf4j
public class ErrorNode extends AbstractGroupBuyMarketSupport<MarketProductEntity, DefaultActivityStrategyFactory.DynamicContext, TrialBalanceEntity> {


    @Override
    protected TrialBalanceEntity doApply(MarketProductEntity requestParameter, DefaultActivityStrategyFactory.DynamicContext dynamicContext) throws Exception {
       log.info("拼团商品查询试算服务-ErrorNode userId:{} requestParameter:{}", requestParameter.getUserId(), JSON.toJSONString(requestParameter));
       if (null == dynamicContext.getGroupBuyActivityDiscountVO() || null == dynamicContext.getSkuVO()) {
           log.info("商品无拼团营销配置 {}", requestParameter.getGoodsId());
           throw new AppException(ResponseCode.E0002.getCode(), ResponseCode.E0002.getInfo());
       }
       return TrialBalanceEntity.builder().build();
    }

    @Override
    public StrategyHandler<MarketProductEntity, DefaultActivityStrategyFactory.DynamicContext, TrialBalanceEntity> get(MarketProductEntity requestParameter, DefaultActivityStrategyFactory.DynamicContext dynamicContext) throws Exception {
        return defaultStrategyHandler;
    }
}
