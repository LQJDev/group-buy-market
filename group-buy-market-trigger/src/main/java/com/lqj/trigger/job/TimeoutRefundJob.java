package com.lqj.trigger.job;

import com.lqj.domain.activity.model.entity.UserGroupBuyOrderDetailEntity;
import com.lqj.domain.trade.model.entity.TradeRefundCommandEntity;
import com.lqj.domain.trade.service.ITradeRefundOrderService;
import lombok.extern.slf4j.Slf4j;
import org.redisson.api.RScoredSortedSet;
import org.redisson.api.RedissonClient;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Collection;

/**
 * @Author 李岐鉴
 * @Date 2026/3/7
 * @Description 单机版 ZSet 超时退单任务（高效分页扫描，无分布式锁）
 */
@Service
@Slf4j
public class TimeoutRefundJob {

    @Resource
    private RedissonClient redissonClient;

    @Resource
    private ITradeRefundOrderService tradeRefundOrderService;

    private static final String ZSET_KEY = "market:pay:order:delay";

    @Scheduled(cron = "0 */1 * * * ?") // 每分钟执行一次
    public void exec() {
        try {
            log.info("单机超时退单任务开始执行");

            RScoredSortedSet<Object> delaySet = redissonClient.getScoredSortedSet(ZSET_KEY);
            long now = System.currentTimeMillis();
            int batchSize = 100;

            while (true) {
                // Redis分页查询 score <= now 的订单
                Collection<Object> expiredOrders = delaySet.valueRange(0, true, now, true, 0, batchSize);

                if (expiredOrders.isEmpty()) {
                    break;
                }

                log.info("处理本批超时订单数量：{}", expiredOrders.size());

                for (Object obj : expiredOrders) {
                    String outTradeNo = (String) obj;

                    // 查询订单详情
                    UserGroupBuyOrderDetailEntity orderDetail =
                            tradeRefundOrderService.queryTimeoutUnpaidOrderListByOrderDetail(
                                    UserGroupBuyOrderDetailEntity.builder().outTradeNo(outTradeNo).build()
                            );

                    if (orderDetail == null) {
                        // 已支付或不存在，直接删除 ZSet 防止脏数据累积
                        delaySet.remove(outTradeNo);
                        log.info("ZSet移除已支付/不存在订单：{}", outTradeNo);
                        continue;
                    }

                    try {
                        TradeRefundCommandEntity refundCommand = TradeRefundCommandEntity.builder()
                                .userId(orderDetail.getUserId())
                                .outTradeNo(orderDetail.getOutTradeNo())
                                .source(orderDetail.getSource())
                                .channel(orderDetail.getChannel())
                                .build();

                        // 执行幂等退单
                        tradeRefundOrderService.refundOrder(refundCommand);

                        // 成功退单，删除 ZSet
                        delaySet.remove(outTradeNo);
                        log.info("超时订单退单成功，用户ID：{}，交易单号：{}", orderDetail.getUserId(), orderDetail.getOutTradeNo());

                    } catch (Exception e) {
                        log.error("超时订单退单失败，交易单号：{}，错误：{}", outTradeNo, e.getMessage(), e);
                    }
                }
            }

            log.info("单机超时退单任务执行完成");

        } catch (Exception e) {
            log.error("单机超时退单任务执行异常", e);
        }
    }
}