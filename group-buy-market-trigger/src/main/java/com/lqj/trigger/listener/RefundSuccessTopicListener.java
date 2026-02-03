package com.lqj.trigger.listener;

import com.alibaba.fastjson2.JSON;
import com.lqj.domain.trade.model.valobj.TeamRefundSuccess;
import com.lqj.domain.trade.service.ITradeRefundOrderService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.ExchangeTypes;
import org.springframework.amqp.rabbit.annotation.Exchange;
import org.springframework.amqp.rabbit.annotation.Queue;
import org.springframework.amqp.rabbit.annotation.QueueBinding;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

/**
 * @Author 李岐鉴
 * @Date 2026/2/2
 * @Description RefundSuccessTopicListener 类
 */
@Slf4j
@Component
public class RefundSuccessTopicListener {

    @Resource
    private ITradeRefundOrderService tradeRefundOrderService;

    @RabbitListener(
            bindings = @QueueBinding(
                    value = @Queue(value = "${spring.rabbitmq.config.producer.topic_team_refund.queue}"),
                    exchange = @Exchange(value = "${spring.rabbitmq.config.producer.exchange}", type = ExchangeTypes.TOPIC),
                    key = "${spring.rabbitmq.config.producer.topic_team_refund.routing_key}"
            )
    )
    public void listen(String message) {
        log.info("接收消息（退单成功）- 恢复拼团队伍锁单量:{}", message);
        TeamRefundSuccess teamRefundSuccess = JSON.parseObject(message, TeamRefundSuccess.class);
        try {
            tradeRefundOrderService.restoreTeamLockStock(teamRefundSuccess);
        } catch (Exception e) {
            log.info("接收消息（退单成功）- 恢复拼团队伍锁单量失败:{}", message, e);
            // 抛异常，mq消息会重试
            throw new RuntimeException(e);
        }

    }
}
