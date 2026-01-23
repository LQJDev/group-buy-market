package com.lqj.api.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @Author 李岐鉴
 * @Date 2025/11/17
 * @Description LocMarketPayOrderRequestDTO 类
 */
@Data
public class LockMarketPayOrderRequestDTO {

    // 用户ID
    private String userId;
    // 拼团组队ID
    private String teamId;
    // 活动ID
    private Long activityId;
    // 商品ID
    private String goodsId;
    // 渠道
    private String source;
    // 来源
    private String channel;
    // 外部交易单号
    private String outTradeNo;
    // 回调地址
    private NotifyConfigVO notifyConfigVO;


    public void setNotifyUrl(String url) {
        NotifyConfigVO notifyConfigVO = new NotifyConfigVO();
        notifyConfigVO.setNotifyUrl(url);
        notifyConfigVO.setNotifyType("HTTP");
        this.notifyConfigVO = notifyConfigVO;
    }

    public void setNotifyMQ() {
        NotifyConfigVO notifyConfigVO = new NotifyConfigVO();
        notifyConfigVO.setNotifyType("MQ");
        this.notifyConfigVO = notifyConfigVO;
    }

    @Data
    public static class NotifyConfigVO {
        // 回调类型
        private String notifyType;
        // 回调消息
        private String notifyMQ;
        // 回调地址
        private String notifyUrl;

    }
}
