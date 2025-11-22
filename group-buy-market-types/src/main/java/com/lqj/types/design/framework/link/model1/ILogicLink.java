package com.lqj.types.design.framework.link.model1;

/**
 * @Author 李岐鉴
 * @Date 2025/11/21
 * @Description 略规则责任链接口
 */
public interface ILogicLink<T, D, R> extends ILogicChainArmory<T, D, R> {

    R apply(T requestParameter, D dynamicContext) throws Exception;

}
