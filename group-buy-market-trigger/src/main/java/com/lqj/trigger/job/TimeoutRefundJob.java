package com.lqj.trigger.job;

import com.lqj.domain.activity.model.entity.UserGroupBuyOrderDetailEntity;
import com.lqj.domain.trade.model.entity.TradeRefundCommandEntity;
import com.lqj.domain.trade.service.ITradeRefundOrderService;
import lombok.extern.slf4j.Slf4j;
import org.redisson.api.RLock;
import org.redisson.api.RedissonClient;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * @Author 李岐鉴
 * @Date 2026/2/4
 * @Description TimeoutRefundJob 类
 */
//@Service
@Slf4j
public class TimeoutRefundJob {


    @Resource
    private RedissonClient redissonClient;

    @Resource
    private ITradeRefundOrderService tradeRefundOrderService;


    @Scheduled(cron = "0 */1 * * * ?")
    public void exec() {

        RLock lock = redissonClient.getLock("group_buy_market_timeout_refund_job_exec");

        try {
            boolean isLocked = lock.tryLock(3, 60, TimeUnit.SECONDS);
            if (!isLocked) {
                log.info("超时退单定时任务，获取锁失败，跳过本次执行");
                return;
            }
            log.info("超时退单定时任务开始执行");
            List<UserGroupBuyOrderDetailEntity> timeoutOrderList = tradeRefundOrderService.queryTimeoutUnpaidOrderList();
            if (timeoutOrderList == null || timeoutOrderList.isEmpty()) {
                log.info("超时退单定时任务，未发现超时未支付订单");
                return;
            }

            log.info("超时退单定时任务，发现超时未支付订单数量：{}", timeoutOrderList.size());

            int successCount = 0;
            int failCount = 0;

            // 遍历处理每个超时订单
            for (UserGroupBuyOrderDetailEntity orderDetail : timeoutOrderList) {
                try {
                    // 构建退单命令
                    TradeRefundCommandEntity refundCommand = TradeRefundCommandEntity.builder()
                            .userId(orderDetail.getUserId())
                            .outTradeNo(orderDetail.getOutTradeNo())
                            .source(orderDetail.getSource())
                            .channel(orderDetail.getChannel())
                            .build();

                    // 执行退单
                    tradeRefundOrderService.refundOrder(refundCommand);
                    successCount++;

                    log.info("超时订单退单成功，用户ID：{}，交易单号：{}", orderDetail.getUserId(), orderDetail.getOutTradeNo());

                } catch (Exception e) {
                    failCount++;
                    log.error("超时订单退单失败，用户ID：{}，交易单号：{}，错误信息：{}",
                            orderDetail.getUserId(), orderDetail.getOutTradeNo(), e.getMessage(), e);
                }
            }

            log.info("超时退单定时任务执行完成，成功：{}，失败：{}", successCount, failCount);

        } catch (Exception e) {
            log.error("超时退单定时任务执行异常", e);
        } finally {
            if (lock.isLocked() && lock.isHeldByCurrentThread()) {
                lock.unlock();
            }
        }

    }
}
