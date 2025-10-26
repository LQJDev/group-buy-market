package com.lqj.types.design.framework.tree;

/**
 * @Author 李岐鉴
 * @Date 2025/10/25
 * @Description 策略处理器
 */
public interface StrategyHandler<T, D, R> {

    StrategyHandler DEFAULT = (T, D) -> null;

    R apply(T requestParameter, D dynamicContext) throws Exception;
}
