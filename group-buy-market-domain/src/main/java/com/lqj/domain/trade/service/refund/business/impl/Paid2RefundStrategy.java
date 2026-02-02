package com.lqj.domain.trade.service.refund.business.impl;

import com.alibaba.fastjson2.JSON;
import com.lqj.domain.trade.adapter.port.ITradePort;
import com.lqj.domain.trade.adapter.repository.ITradeRepository;
import com.lqj.domain.trade.model.aggregate.GroupBuyRefundAggregate;
import com.lqj.domain.trade.model.entity.NotifyTaskEntity;
import com.lqj.domain.trade.model.entity.TradeRefundOrderEntity;
import com.lqj.domain.trade.service.ITradeTaskService;
import com.lqj.domain.trade.service.refund.business.IRefundOrderStrategy;
import com.lqj.types.exception.AppException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Map;
import java.util.concurrent.ThreadPoolExecutor;

/**
 * @Author 李岐鉴
 * @Date 2026/2/1
 * @Description Paid2RefundStrategy 类
 */
@Slf4j
@Service("paid2RefundStrategy")
public class Paid2RefundStrategy implements IRefundOrderStrategy {

    @Resource
    private ThreadPoolExecutor threadPoolExecutor;

    @Resource
    private ITradeRepository repository;

    @Resource
    private ITradePort port;

    @Resource
    private ITradeTaskService tradeTaskService;

    @Override
    public void refundOrder(TradeRefundOrderEntity tradeRefundOrderEntity) {
        log.info("退单；已支付，未成团 userId:{} teamId:{} orderId:{}", tradeRefundOrderEntity.getUserId(), tradeRefundOrderEntity.getTeamId(), tradeRefundOrderEntity.getOrderId());
        NotifyTaskEntity notifyTaskEntity = repository.paid2Refund(GroupBuyRefundAggregate
                .buildPaid2RefundAggregate(tradeRefundOrderEntity, -1, -1)
                );

        if (null != notifyTaskEntity) {
            threadPoolExecutor.execute(() -> {
                Map<String, Integer> notifyResultMap = null;
                try {
                    notifyResultMap = tradeTaskService.execNotifyJob(notifyTaskEntity);
                    log.info("回调通知交易退单 result:{}", JSON.toJSONString(notifyResultMap));
                } catch (Exception e) {
                    log.error("回调通知交易退单失败 result:{}", JSON.toJSONString(notifyResultMap), e);
                    throw new AppException(e.getMessage());
                }
            });
        }
    }
}
