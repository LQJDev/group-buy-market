package com.lqj.domain.trade.service.refund.business.impl;

import com.alibaba.fastjson2.JSON;
import com.lqj.domain.trade.adapter.port.ITradePort;
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
public class Paid2RefundStrategy extends AbstractRefundOrderStrategy {

    @Override
    public void refundOrder(TradeRefundOrderEntity tradeRefundOrderEntity) {
        log.info("退单；已支付，未成团 userId:{} teamId:{} orderId:{}", tradeRefundOrderEntity.getUserId(), tradeRefundOrderEntity.getTeamId(), tradeRefundOrderEntity.getOrderId());
        NotifyTaskEntity notifyTaskEntity = repository.paid2Refund(GroupBuyRefundAggregate
                .buildPaid2RefundAggregate(tradeRefundOrderEntity, -1, -1)
                );
        sendRefundNotifyMessage(notifyTaskEntity, "已支付，未成团");
    }

    @Override
    public void reverseStock(TeamRefundSuccess teamRefundSuccess) throws Exception {
        doReverseStock(teamRefundSuccess, "已支付，未成团，但有锁单记录，要恢复锁单库存");
    }
}
