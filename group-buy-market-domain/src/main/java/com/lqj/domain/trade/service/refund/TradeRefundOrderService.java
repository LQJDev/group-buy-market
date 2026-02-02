package com.lqj.domain.trade.service.refund;

import com.lqj.domain.trade.adapter.repository.ITradeRepository;
import com.lqj.domain.trade.model.entity.*;
import com.lqj.domain.trade.model.valobj.RefundTypeEnumVO;
import com.lqj.domain.trade.model.valobj.TradeOrderStatusEnumVO;
import com.lqj.domain.trade.service.ITradeRefundOrderService;
import com.lqj.domain.trade.service.refund.business.IRefundOrderStrategy;
import org.springframework.stereotype.Service;

import java.util.Map;

/**
 * @Author 李岐鉴
 * @Date 2026/2/1
 * @Description TradeRefundOrderService 类
 */
@Service
public class TradeRefundOrderService implements ITradeRefundOrderService {

    private final ITradeRepository repository;

    private final Map<String, IRefundOrderStrategy> refundOrderStrategyMap;

    public TradeRefundOrderService(ITradeRepository repository, Map<String, IRefundOrderStrategy> refundOrderStrategyMap) {
        this.repository = repository;
        this.refundOrderStrategyMap = refundOrderStrategyMap;
    }

    @Override
    public TradeRefundBehaviorEntity refundOrder(TradeRefundCommandEntity tradeRefundCommandEntity) {
        MarketPayOrderEntity marketPayOrderEntity = repository.queryMarketPayOrderEntityByOutTradeNo(tradeRefundCommandEntity.getUserId(), tradeRefundCommandEntity.getOutTradeNo());
        TradeOrderStatusEnumVO tradeOrderStatusEnumVO = marketPayOrderEntity.getTradeOrderStatusEnumVO();
        String orderId = marketPayOrderEntity.getOrderId();
        String teamId = marketPayOrderEntity.getTeamId();
        if (TradeOrderStatusEnumVO.CLOSE.equals(tradeOrderStatusEnumVO)) {
            return TradeRefundBehaviorEntity.builder()
                    .orderId(orderId)
                    .teamId(teamId)
                    .userId(tradeRefundCommandEntity.getUserId())
                    .tradeRefundBehaviorEnum(TradeRefundBehaviorEntity.TradeRefundBehaviorEnum.REPEAT)
                    .build();
        }

        GroupBuyTeamEntity groupBuyTeamEntity = repository.queryGroupBuyTeamByTeamId(teamId);
        RefundTypeEnumVO refundTypeEnumVO = RefundTypeEnumVO.getRefundStrategy(groupBuyTeamEntity.getStatus(), tradeOrderStatusEnumVO);
        IRefundOrderStrategy iRefundOrderStrategy = refundOrderStrategyMap.get(refundTypeEnumVO.getStrategy());
        iRefundOrderStrategy.refundOrder(TradeRefundOrderEntity.builder()
                .orderId(orderId)
                .teamId(teamId)
                .userId(tradeRefundCommandEntity.getUserId())
                .build());

        return TradeRefundBehaviorEntity.builder()
                .userId(tradeRefundCommandEntity.getUserId())
                .orderId(orderId)
                .teamId(teamId)
                .tradeRefundBehaviorEnum(TradeRefundBehaviorEntity.TradeRefundBehaviorEnum.SUCCESS)
                .build();
    }
}
