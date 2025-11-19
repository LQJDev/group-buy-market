package com.lqj.domain.trade.model.valobj;

import lombok.*;

/**
 * @Author 李岐鉴
 * @Date 2025/11/10
 * @Description GroupBuyProgressVO 类
 */
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class GroupBuyProgressVO {

    private Integer targetCount;

    private Integer completeCount;

    private Integer lockCount;
}
