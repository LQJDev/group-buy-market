package com.lqj.domain.trade.service.refund.business;

import com.lqj.domain.trade.model.entity.TradeRefundOrderEntity;

/**
 * @Author 李岐鉴
 * @Date 2026/2/1
 * @Description IRefundOrderStrategy 类
 */
public interface IRefundOrderStrategy {

    void refundOrder(TradeRefundOrderEntity tradeRefundOrderEntity);
}
