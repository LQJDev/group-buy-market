package com.lqj.domain.trade.service.refund.filter;

import cn.bugstack.wrench.design.framework.link.model2.handler.ILogicHandler;
import com.lqj.domain.trade.model.entity.*;
import com.lqj.domain.trade.model.valobj.RefundTypeEnumVO;
import com.lqj.domain.trade.model.valobj.TradeOrderStatusEnumVO;
import com.lqj.domain.trade.service.refund.business.IRefundOrderStrategy;
import com.lqj.domain.trade.service.refund.factory.TradeRefundRuleFilterFactory;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Map;

/**
 * @Author 李岐鉴
 * @Date 2026/2/4
 * @Description RefundOrderNodeFilter 类
 */
@Slf4j
@Service
public class RefundOrderNodeFilter implements ILogicHandler<TradeRefundCommandEntity, TradeRefundRuleFilterFactory.DynamicContext, TradeRefundBehaviorEntity> {

    @Resource
    private Map<String, IRefundOrderStrategy> refundOrderStrategyMap;

    @Override
    public TradeRefundBehaviorEntity apply(TradeRefundCommandEntity tradeRefundCommandEntity, TradeRefundRuleFilterFactory.DynamicContext dynamicContext) throws Exception {
        GroupBuyTeamEntity groupBuyTeamEntity = dynamicContext.getGroupBuyTeamEntity();
        MarketPayOrderEntity marketPayOrderEntity = dynamicContext.getMarketPayOrderEntity();
        TradeOrderStatusEnumVO tradeOrderStatusEnumVO = marketPayOrderEntity.getTradeOrderStatusEnumVO();

        RefundTypeEnumVO refundTypeEnumVO = RefundTypeEnumVO.getRefundStrategy(groupBuyTeamEntity.getStatus(), tradeOrderStatusEnumVO);
        IRefundOrderStrategy iRefundOrderStrategy = refundOrderStrategyMap.get(refundTypeEnumVO.getStrategy());
        iRefundOrderStrategy.refundOrder(TradeRefundOrderEntity.builder()
                .orderId(marketPayOrderEntity.getOrderId())
                .teamId(marketPayOrderEntity.getTeamId())
                .activityId(groupBuyTeamEntity.getActivityId())
                .userId(tradeRefundCommandEntity.getUserId())
                .build());

        return TradeRefundBehaviorEntity.builder()
                .userId(tradeRefundCommandEntity.getUserId())
                .orderId(marketPayOrderEntity.getOrderId())
                .teamId(marketPayOrderEntity.getTeamId())
                .tradeRefundBehaviorEnum(TradeRefundBehaviorEntity.TradeRefundBehaviorEnum.SUCCESS)
                .build();
    }
}
