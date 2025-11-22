package com.lqj.types.design.framework.link.model2.chain;

/**
 * @Author 李岐鉴
 * @Date 2025/11/21
 * @Description 链接口
 */
public interface ILink<E> {

    boolean add(E e);

    boolean addFirst(E e);

    boolean addLast(E e);

    boolean remove(Object o);

    E get(int index);

    void printLinkList();
}
