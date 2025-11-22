package com.lqj.types.design.framework.link.model1;

/**
 * @Author 李岐鉴
 * @Date 2025/11/21
 * @Description 责任链装配
 */
public interface ILogicChainArmory<T, D, R> {

    ILogicLink<T, D, R> next();

    ILogicLink<T, D, R> appendNext(ILogicLink<T, D, R> next);
}
