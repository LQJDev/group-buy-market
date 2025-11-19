package com.lqj.domain.trade.model.aggregate;

import com.lqj.domain.trade.model.entity.PayActivityEntity;
import com.lqj.domain.trade.model.entity.PayDiscountEntity;
import com.lqj.domain.trade.model.entity.UserEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @Author 李岐鉴
 * @Date 2025/11/10
 * @Description GroupBuyOrderAggregate 类
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class GroupBuyOrderAggregate {

    private UserEntity userEntity;

    private PayActivityEntity payActivityEntity;

    private PayDiscountEntity payDiscountEntity;
}
