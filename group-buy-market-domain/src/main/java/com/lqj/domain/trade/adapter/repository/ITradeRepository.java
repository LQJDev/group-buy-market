package com.lqj.domain.trade.adapter.repository;

import com.lqj.domain.trade.model.aggregate.GroupBuyOrderAggregate;
import com.lqj.domain.trade.model.entity.MarketPayOrderEntity;
import com.lqj.domain.trade.model.valobj.GroupBuyProgressVO;

/**
 * @Author 李岐鉴
 * @Date 2025/11/10
 * @Description ITradeRepository 类
 */
public interface ITradeRepository {

    MarketPayOrderEntity queryNoPayMarketPayOrderByOutTradeNo(String userId, String outTradeNo);

    GroupBuyProgressVO queryGroupBuyProgress(String teamId);

    MarketPayOrderEntity lockMarketPayOrder(GroupBuyOrderAggregate groupBuyOrderAggregate);
}
