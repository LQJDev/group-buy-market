package com.lqj.infrastructure.adapter.port;

import com.lqj.domain.trade.adapter.port.ITradePort;
import com.lqj.domain.trade.model.entity.NotifyTaskEntity;
import com.lqj.infrastructure.gateway.GroupBuyNotifyService;
import com.lqj.infrastructure.redis.IRedisService;
import com.lqj.types.enums.NotifyTaskHTTPEnumVO;
import org.apache.commons.lang3.StringUtils;
import org.redisson.api.RLock;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.concurrent.TimeUnit;

/**
 * @Author 李岐鉴
 * @Date 2025/12/12
 * @Description TradePort 类
 */
@Service
public class TradePort implements ITradePort {

    @Resource
    private GroupBuyNotifyService groupBuyNotifyService;
    @Resource
    private IRedisService redisService;


    @Override
    public String groupBuyNotify(NotifyTaskEntity notifyTask) throws Exception {
        RLock lock = redisService.getLock(notifyTask.lockKey());
        try {
            //  group-buy-market 拼团服务端会被部署到多台应用服务器上，那么就会有很多任务一起执行。这个时候要进行抢占，避免被多次执行
            if (lock.tryLock(3, 0, TimeUnit.SECONDS)) {
                try {
                    // 校验notifyUrl: 无效则直接返回成功
                    if (StringUtils.isBlank(notifyTask.getNotifyUrl()) || "暂无".equals(notifyTask.getNotifyUrl())) {
                        return NotifyTaskHTTPEnumVO.SUCCESS.getCode();
                    }
                    // 执行具体的拼团通知逻辑
                    return groupBuyNotifyService.groupBuyNotify(notifyTask.getNotifyUrl(), notifyTask.getParameterJson());
                } finally {
                    // 释放锁: 确保当前线程持有锁的时候才释放
                    if (lock.isLocked() && lock.isHeldByCurrentThread()) {
                        lock.unlock();
                    }
                }
            }
            // 未抢到锁，返回NULL
            return NotifyTaskHTTPEnumVO.NULL.getCode();
        } catch (Exception e) {
            // 异常处理：中断当前线程，返回NULL标识
            Thread.currentThread().interrupt();
            return NotifyTaskHTTPEnumVO.NULL.getCode();
        }
    }
}
