package com.lqj.domain.trade.model.valobj;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @Author 李岐鉴
 * @Date 2026/1/20
 * @Description NotifyConfigVO 类
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class NotifyConfigVO {

    private String notifyUrl;

    private String notifyMQ;

    private NotifyTypeEnumVO notifyType;
}
