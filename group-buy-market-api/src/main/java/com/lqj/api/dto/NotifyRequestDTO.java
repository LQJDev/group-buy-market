package com.lqj.api.dto;

import lombok.Data;

import java.util.List;

/**
 * @Author 李岐鉴
 * @Date 2025/12/11
 * @Description 回调请求对象
 */
@Data
public class NotifyRequestDTO {

    /**
     * 组队id
     */
    private String teamId;

    /**
     * 外部交易单号列表
     */
    private List<String> outTradeNoList;
}
