package com.lqj.types.design.framework.link.model2;

import com.lqj.types.design.framework.link.model2.chain.BusinessLinkedList;
import com.lqj.types.design.framework.link.model2.handler.ILogicHandler;

/**
 * @Author 李岐鉴
 * @Date 2025/11/22
 * @Description 链路装配
 */
public class LinkArmory<T, D, R> {

    private final BusinessLinkedList<T, D, R> logicLink;

    public LinkArmory(String linkName, ILogicHandler<T, D, R>... logicHandlers) {
        logicLink = new BusinessLinkedList<>(linkName);
        for (ILogicHandler<T, D, R> logicHandler : logicHandlers) {
            logicLink.add(logicHandler);
        }
    }

    public BusinessLinkedList<T, D, R> getLogicLink() {
        return logicLink;
    }

}
