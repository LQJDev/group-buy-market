package com.lqj.infrastructure.event;

import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.MessageDeliveryMode;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * @Author 李岐鉴
 * @Date 2026/1/19
 * @Description EventPublisher 类
 */
@Slf4j
@Component
public class EventPublisher {

    @Autowired
    private RabbitTemplate rabbitTemplate;

    @Value("${spring.rabbitmq.config.producer.exchange}")
    private String exchangeName;

    public void publish(String routingKey, String message) {
        try {
            rabbitTemplate.convertAndSend(exchangeName, routingKey, message, m -> {
                m.getMessageProperties().setDeliveryMode(MessageDeliveryMode.PERSISTENT);
                return m;
            });
        } catch (Exception e) {
            log.error("发送MQ消息失败 team_success message:{}", message, e);
        }
    }

    /**
     * 延迟消息，使用 TTL + 死信队列
     * @param delayMillis 延迟时间，单位毫秒
     */
    public void publishDelay(Object message, long delayMillis) {
        try {
            // 这里直接发送到延迟队列名，不用交换机 routing
            rabbitTemplate.convertAndSend(
                    "", // 默认交换机发送到队列名即可
                    "order.delay.queue",
                    message,
                    m -> {
                        m.getMessageProperties().setDeliveryMode(MessageDeliveryMode.PERSISTENT);
                        // 单条消息 TTL 覆盖队列默认 TTL
                        m.getMessageProperties().setExpiration(String.valueOf(delayMillis));
                        return m;
                    });
        } catch (Exception e) {
            log.error("发送延迟MQ消息失败 message:{}", message, e);
        }
    }
}
