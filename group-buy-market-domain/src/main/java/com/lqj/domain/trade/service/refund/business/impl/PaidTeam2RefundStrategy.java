package com.lqj.domain.trade.service.refund.business.impl;

import com.alibaba.fastjson2.JSON;
import com.lqj.domain.trade.adapter.repository.ITradeRepository;
import com.lqj.domain.trade.model.aggregate.GroupBuyRefundAggregate;
import com.lqj.domain.trade.model.entity.GroupBuyTeamEntity;
import com.lqj.domain.trade.model.entity.NotifyTaskEntity;
import com.lqj.domain.trade.model.entity.TradeRefundOrderEntity;
import com.lqj.domain.trade.model.valobj.TeamRefundSuccess;
import com.lqj.domain.trade.service.ITradeTaskService;
import com.lqj.domain.trade.service.lock.factory.TradeLockRuleFilterFactory;
import com.lqj.domain.trade.service.refund.business.AbstractRefundOrderStrategy;
import com.lqj.domain.trade.service.refund.business.IRefundOrderStrategy;
import com.lqj.types.enums.GroupBuyOrderEnumVO;
import com.lqj.types.exception.AppException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Map;
import java.util.concurrent.ThreadPoolExecutor;

/**
 * @Author 李岐鉴
 * @Date 2026/2/1
 * @Description PaidTeam2RefundStrategy 类
 */
@Slf4j
@Service("paidTeam2RefundStrategy")
public class PaidTeam2RefundStrategy extends AbstractRefundOrderStrategy {

    @Override
    public void refundOrder(TradeRefundOrderEntity tradeRefundOrderEntity) {
        log.info("退单；已支付，已成团 userId:{} teamId:{} orderId:{}", tradeRefundOrderEntity.getUserId(), tradeRefundOrderEntity.getTeamId(), tradeRefundOrderEntity.getOrderId());
        GroupBuyTeamEntity groupBuyTeamEntity = repository.queryGroupBuyTeamByTeamId(tradeRefundOrderEntity.getTeamId());
        Integer completeCount = groupBuyTeamEntity.getCompleteCount();
        GroupBuyOrderEnumVO groupBuyOrderEnumVO = 1 == completeCount ? GroupBuyOrderEnumVO.FAIL : GroupBuyOrderEnumVO.COMPLETE_FAIL;
        NotifyTaskEntity notifyTaskEntity = repository.paidTeam2Refund(GroupBuyRefundAggregate
                .buildPaidTeam2RefundAggregate(tradeRefundOrderEntity, -1, -1, groupBuyOrderEnumVO));

        sendRefundNotifyMessage(notifyTaskEntity, "已支付，已成团");
    }

    @Override
    public void reverseStock(TeamRefundSuccess teamRefundSuccess) throws Exception {
        log.info("退单；恢复锁单量 - 已支付，已成团 恢复库存 userId:{} teamId:{} orderId:{}", teamRefundSuccess.getUserId(), teamRefundSuccess.getTeamId(), teamRefundSuccess.getOrderId());
    }
}
