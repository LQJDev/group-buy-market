package com.lqj.domain.trade.model.valobj;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.checkerframework.checker.units.qual.A;

/**
 * @Author 李岐鉴
 * @Date 2026/1/20
 * @Description NotifyTypeEnumVO 类
 */
@Getter
@AllArgsConstructor
@NoArgsConstructor
public enum NotifyTypeEnumVO {

    HTTP("HTTP", "HTTP 回调"),
    MQ("MQ", "MQ 消息通知"),
    MQ_DELAY("MQ_DELAY", "MQ 延时消息通知"),
    ;

    private String code;
    private String info;
}
