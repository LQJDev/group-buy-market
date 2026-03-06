package com.lqj.domain.trade.model.valobj;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * @Author 李岐鉴
 * @Date 2026/3/5
 * @Description OrderDelayMessageVO 类
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class OrderDelayMessageVO implements Serializable {

    private static final long serialVersionUID = 1L;
    /**
     * 用户ID
     */
    private String userId;

    /**
     * 外部交易单号
     */
    private String outTradeNo;

    /** 渠道 */
    private String source;

    /** 来源 */
    private String channel;


}
