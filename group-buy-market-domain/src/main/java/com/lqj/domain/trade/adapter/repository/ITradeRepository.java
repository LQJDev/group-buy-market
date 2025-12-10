package com.lqj.domain.trade.adapter.repository;

import com.lqj.domain.trade.model.aggregate.GroupBuyOrderAggregate;
import com.lqj.domain.trade.model.aggregate.GroupBuyTeamSettlementAggregate;
import com.lqj.domain.trade.model.entity.GroupBuyActivityEntity;
import com.lqj.domain.trade.model.entity.GroupBuyTeamEntity;
import com.lqj.domain.trade.model.entity.MarketPayOrderEntity;
import com.lqj.domain.trade.model.valobj.GroupBuyProgressVO;

/**
 * @Author 李岐鉴
 * @Date 2025/11/10
 * @Description ITradeRepository 类
 */
public interface ITradeRepository {

    MarketPayOrderEntity queryMarketPayOrderEntityByOutTradeNo(String userId, String outTradeNo);

    GroupBuyProgressVO queryGroupBuyProgress(String teamId);

    MarketPayOrderEntity lockMarketPayOrder(GroupBuyOrderAggregate groupBuyOrderAggregate);

    GroupBuyActivityEntity queryGroupBuyActivityEntityByActivityId(Long activityId);

    Integer queryOrderCountByActivityId(Long activityId, String userId);

    GroupBuyTeamEntity queryGroupBuyTeamByTeamId(String teamId);

    void settlementMarketPayOrder(GroupBuyTeamSettlementAggregate groupBuyTeamSettlementAggregate);


}
