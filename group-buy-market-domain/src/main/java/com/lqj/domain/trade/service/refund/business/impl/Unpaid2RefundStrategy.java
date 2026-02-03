package com.lqj.domain.trade.service.refund.business.impl;

import com.alibaba.fastjson2.JSON;
import com.lqj.domain.trade.adapter.repository.ITradeRepository;
import com.lqj.domain.trade.model.aggregate.GroupBuyRefundAggregate;
import com.lqj.domain.trade.model.entity.NotifyTaskEntity;
import com.lqj.domain.trade.model.entity.TradeRefundOrderEntity;
import com.lqj.domain.trade.service.ITradeTaskService;
import com.lqj.domain.trade.service.refund.business.IRefundOrderStrategy;
import com.lqj.types.exception.AppException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.HandlerMapping;

import javax.annotation.Resource;
import java.util.Map;
import java.util.concurrent.ThreadPoolExecutor;

/**
 * @Author 李岐鉴
 * @Date 2026/2/1
 * @Description Unpaid2RefundStrategy 类
 */
@Service("unpaid2RefundStrategy")
@Slf4j
public class Unpaid2RefundStrategy implements IRefundOrderStrategy {

    @Resource
    private ITradeRepository repository;

    @Resource
    private ThreadPoolExecutor threadPoolExecutor;

    @Resource
    private ITradeTaskService tradeTaskService;

    @Override
    public void refundOrder(TradeRefundOrderEntity tradeRefundOrderEntity) {
        log.info("退单；未支付，未成团 userId:{} teamId:{} orderId:{}", tradeRefundOrderEntity.getUserId(), tradeRefundOrderEntity.getTeamId(), tradeRefundOrderEntity.getOrderId());
        NotifyTaskEntity notifyTaskEntity = repository.unpaid2Refund(GroupBuyRefundAggregate.buildUnpaid2RefundAggregate(tradeRefundOrderEntity, -1));
        if (null != notifyTaskEntity) {
            threadPoolExecutor.execute(() -> {
                Map<String, Integer> notifyResultMap = null;
                try {
                    notifyResultMap = tradeTaskService.execNotifyJob(notifyTaskEntity);
                    log.info("回调通知交易退单(未支付，未成团) result:{}", JSON.toJSONString(notifyResultMap));
                } catch (Exception e) {
                    log.error("回调通知交易退单(未支付，未成团)失败 result:{}", JSON.toJSONString(notifyResultMap), e);
                    throw new AppException(e.getMessage());
                }
            });
        }
    }
}
