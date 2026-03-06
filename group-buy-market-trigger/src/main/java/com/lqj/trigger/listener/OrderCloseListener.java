package com.lqj.trigger.listener;

import com.lqj.domain.activity.model.entity.UserGroupBuyOrderDetailEntity;
import com.lqj.domain.trade.model.entity.MarketPayOrderEntity;
import com.lqj.domain.trade.model.entity.TradeRefundCommandEntity;
import com.lqj.domain.trade.model.valobj.OrderDelayMessageVO;
import com.lqj.domain.trade.model.valobj.TradeOrderStatusEnumVO;
import com.lqj.domain.trade.service.ITradeLockOrderService;
import com.lqj.domain.trade.service.ITradeRefundOrderService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

@Slf4j
@Component
public class OrderCloseListener {


    private String teamStockKey = "group_buy_market_team_stock_key_";

    @Resource
    private ITradeLockOrderService tradeLockOrderService;

    @Resource
    private ITradeRefundOrderService tradeRefundOrderService;

    /**
     * 监听死信队列 order.close.queue
     */
    @RabbitListener(queues = "order.close.queue")
    public void handleCloseOrder(OrderDelayMessageVO orderDelayMessageVO) {
        log.info("死信队列监听到消息 message:{}", orderDelayMessageVO);
        String channel = orderDelayMessageVO.getChannel();
        String source = orderDelayMessageVO.getSource();
        String outTradeNo = orderDelayMessageVO.getOutTradeNo();
        String userId = orderDelayMessageVO.getUserId();
        UserGroupBuyOrderDetailEntity orderDetailEntity = UserGroupBuyOrderDetailEntity.builder()
                .userId(userId)
                .outTradeNo(outTradeNo)
                .channel(channel)
                .source(source).build();
        UserGroupBuyOrderDetailEntity orderDetailEntityRes = tradeRefundOrderService.queryTimeoutUnpaidOrderListByOrderDetail(orderDetailEntity);
        if (orderDetailEntityRes == null) {
            log.info("订单不存在，用户ID：{}，交易单号：{}", userId, outTradeNo);
            return;
        }
        try {
            // 构建退单命令
            TradeRefundCommandEntity refundCommand = TradeRefundCommandEntity.builder()
                    .userId(orderDetailEntityRes.getUserId())
                    .outTradeNo(orderDetailEntityRes.getOutTradeNo())
                    .source(orderDetailEntityRes.getSource())
                    .channel(orderDetailEntityRes.getChannel())
                    .build();
            tradeRefundOrderService.refundOrder(refundCommand);
            log.info("超时订单退单成功，用户ID：{}，交易单号：{}", orderDetailEntityRes.getUserId(), orderDetailEntityRes.getOutTradeNo());
        } catch (Exception e) {
            log.error("超时订单退单失败，用户ID：{}，交易单号：{}，错误信息：{}",
                    orderDetailEntityRes.getUserId(), orderDetailEntityRes.getOutTradeNo(), e.getMessage(), e);
        }
    }
}