package com.lqj.domain.trade.service;

import com.lqj.domain.trade.model.entity.TradeRefundBehaviorEntity;
import com.lqj.domain.trade.model.entity.TradeRefundCommandEntity;

/**
 * @Author 李岐鉴
 * @Date 2026/2/1
 * @Description ITradeRefundOrderService 类
 */
public interface ITradeRefundOrderService {

    TradeRefundBehaviorEntity refundOrder(TradeRefundCommandEntity tradeRefundCommandEntity);
}
