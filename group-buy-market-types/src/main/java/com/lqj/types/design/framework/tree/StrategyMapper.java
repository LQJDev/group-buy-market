package com.lqj.types.design.framework.tree;

/**
 * @Author 李岐鉴
 * @Date 2025/10/25
 * @Description 策略映射器
 */
public interface StrategyMapper<T, D, R> {

    StrategyHandler<T, D, R> get(T requestParameter, D dynamicContext) throws Exception;

}
