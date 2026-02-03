package com.lqj.domain.trade.model.aggregate;

import com.lqj.domain.trade.model.entity.TradeRefundOrderEntity;
import com.lqj.domain.trade.model.valobj.GroupBuyProgressVO;
import com.lqj.types.enums.GroupBuyOrderEnumVO;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @Author 李岐鉴
 * @Date 2026/2/1
 * @Description GroupBuyRefundAggregate 类
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class GroupBuyRefundAggregate {

    private TradeRefundOrderEntity tradeRefundOrderEntity;

    private GroupBuyProgressVO groupBuyProgress;

    private GroupBuyOrderEnumVO groupBuyOrderEnumVO;

    public static GroupBuyRefundAggregate buildUnpaid2RefundAggregate(TradeRefundOrderEntity tradeRefundOrderEntity, Integer lockCount) {
        GroupBuyRefundAggregate groupBuyRefundAggregate = new GroupBuyRefundAggregate();
        groupBuyRefundAggregate.setTradeRefundOrderEntity(tradeRefundOrderEntity);
        groupBuyRefundAggregate.setGroupBuyProgress(
                GroupBuyProgressVO.builder()
                        .lockCount(lockCount)
                        .build());
        return groupBuyRefundAggregate;
    }

    public static GroupBuyRefundAggregate buildPaid2RefundAggregate(TradeRefundOrderEntity tradeRefundOrderEntity, Integer lockCount, Integer completeCount) {
        GroupBuyRefundAggregate groupBuyRefundAggregate = new GroupBuyRefundAggregate();
        groupBuyRefundAggregate.setTradeRefundOrderEntity(tradeRefundOrderEntity);
        groupBuyRefundAggregate.setGroupBuyProgress(
                GroupBuyProgressVO.builder()
                        .lockCount(lockCount)
                        .completeCount(completeCount)
                        .build());
        return groupBuyRefundAggregate;
    }


    public static GroupBuyRefundAggregate buildPaidTeam2RefundAggregate(
            TradeRefundOrderEntity tradeRefundOrderEntity, Integer lockCount,
            Integer completeCount, GroupBuyOrderEnumVO groupBuyOrderEnumVO) {
        GroupBuyRefundAggregate groupBuyRefundAggregate = new GroupBuyRefundAggregate();
        groupBuyRefundAggregate.setTradeRefundOrderEntity(tradeRefundOrderEntity);
        groupBuyRefundAggregate.setGroupBuyProgress(
                GroupBuyProgressVO.builder()
                        .lockCount(lockCount)
                        .completeCount(completeCount)
                        .build());
        groupBuyRefundAggregate.setGroupBuyOrderEnumVO(groupBuyOrderEnumVO);

        return groupBuyRefundAggregate;

    }


}
