package com.lqj.domain.trade.service;

import com.lqj.domain.trade.model.entity.TradePaySettlementEntity;
import com.lqj.domain.trade.model.entity.TradePaySuccessEntity;

/**
 * @Author 李岐鉴
 * @Date 2025/12/8
 * @Description 拼团交易结算服务接口
 */
public interface ITradeSettlementOrderService {

    /**
     * 拼团交易结算
     * @param tradePaySuccessEntity 交易支付订单实体对象
     * @return 交易结算订单实体
     */
    TradePaySettlementEntity settlementMarketPayOrder(TradePaySuccessEntity tradePaySuccessEntity) throws Exception;

}
