package com.lqj.config;

import org.springframework.amqp.core.*;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.HashMap;
import java.util.Map;

//@Configuration
public class RabbitMQConfig {

    @Value("${spring.rabbitmq.config.producer.exchange}")
    private String exchangeName;

    /**
     * 专属交换机
     */
    @Bean
    public TopicExchange topicExchange() {
        return new TopicExchange(exchangeName, true, false);
    }

    /**
     * 绑定队列到交换机
     */
    @Bean
    public Binding topicTeamSuccessBinding(
            @Value("${spring.rabbitmq.config.producer.topic_team_success.routing_key}") String routingKey,
            @Value("${spring.rabbitmq.config.producer.topic_team_success.queue}") String queue) {
        return BindingBuilder.bind(new Queue(queue, true))
                .to(topicExchange())
                .with(routingKey);
    }

    /**
     * 延时队列
     * @return
     */
    @Bean
    public Queue orderDelayQueue() {
        Map<String, Object> args = new HashMap<>();
        // 消息 TTL
        args.put("x-message-ttl", 1800000);

        // 死信交换机
        args.put("x-dead-letter-exchange", "order.dlx");

        // 死信 routingKey
        args.put("x-dead-letter-routing-key", "order.close");

        return new Queue("order.delay.queue", true, false, false, args);
    }

    /**
     * 死信交换机
     * @return
     */
    @Bean
    public DirectExchange deadLetterExchange() {
        return new DirectExchange("order.dlx");
    }

    @Bean
    public Queue orderCloseQueue() {
        return new Queue("order.close.queue", true);
    }

    @Bean
    public Binding orderCloseBinding() {
        return BindingBuilder.bind(orderCloseQueue())
                .to(deadLetterExchange())
                .with("order.close");
    }

    @Bean
    public RabbitTemplate rabbitTemplate(ConnectionFactory connectionFactory) {
        RabbitTemplate template = new RabbitTemplate(connectionFactory);
        template.setMessageConverter(new Jackson2JsonMessageConverter());
        return template;
    }

}
