package com.lqj.domain.trade.service.refund.business.impl;

import com.alibaba.fastjson2.JSON;
import com.lqj.domain.trade.adapter.repository.ITradeRepository;
import com.lqj.domain.trade.model.aggregate.GroupBuyRefundAggregate;
import com.lqj.domain.trade.model.entity.NotifyTaskEntity;
import com.lqj.domain.trade.model.entity.TradeRefundOrderEntity;
import com.lqj.domain.trade.model.valobj.TeamRefundSuccess;
import com.lqj.domain.trade.service.ITradeTaskService;
import com.lqj.domain.trade.service.lock.factory.TradeLockRuleFilterFactory;
import com.lqj.domain.trade.service.refund.business.AbstractRefundOrderStrategy;
import com.lqj.domain.trade.service.refund.business.IRefundOrderStrategy;
import com.lqj.types.exception.AppException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.HandlerMapping;
import org.springframework.web.servlet.resource.AbstractVersionStrategy;

import java.util.Map;
import java.util.concurrent.ThreadPoolExecutor;

/**
 * @Author 李岐鉴
 * @Date 2026/2/1
 * @Description Unpaid2RefundStrategy 类
 */
@Service("unpaid2RefundStrategy")
@Slf4j
public class Unpaid2RefundStrategy extends AbstractRefundOrderStrategy {
    @Override
    public void refundOrder(TradeRefundOrderEntity tradeRefundOrderEntity) {
        log.info("退单；未支付，未成团 userId:{} teamId:{} orderId:{}", tradeRefundOrderEntity.getUserId(), tradeRefundOrderEntity.getTeamId(), tradeRefundOrderEntity.getOrderId());
        // 1. 退单；未支付，未成团
        NotifyTaskEntity notifyTaskEntity = repository.unpaid2Refund(GroupBuyRefundAggregate.buildUnpaid2RefundAggregate(tradeRefundOrderEntity, -1));
        // 2. 发送MQ消息 - 发送MQ，恢复锁单库存量使用
        sendRefundNotifyMessage(notifyTaskEntity, "未支付，未成团");
    }

    @Override
    public void reverseStock(TeamRefundSuccess teamRefundSuccess) throws Exception {
        doReverseStock(teamRefundSuccess, "未支付，未成团，但有锁单记录，要恢复锁单库存");
    }



}
