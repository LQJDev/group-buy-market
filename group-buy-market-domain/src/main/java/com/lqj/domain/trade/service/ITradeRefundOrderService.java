package com.lqj.domain.trade.service;

import com.lqj.domain.trade.model.entity.TradeRefundBehaviorEntity;
import com.lqj.domain.trade.model.entity.TradeRefundCommandEntity;
import com.lqj.domain.trade.model.valobj.TeamRefundSuccess;

/**
 * @Author 李岐鉴
 * @Date 2026/2/1
 * @Description ITradeRefundOrderService 类
 */
public interface ITradeRefundOrderService {

    TradeRefundBehaviorEntity refundOrder(TradeRefundCommandEntity tradeRefundCommandEntity) throws Exception;

    void restoreTeamLockStock(TeamRefundSuccess teamRefundSuccess) throws Exception;
}
