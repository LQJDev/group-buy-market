package com.lqj.domain.trade.service;

import com.lqj.domain.trade.model.entity.MarketPayOrderEntity;
import com.lqj.domain.trade.model.entity.PayActivityEntity;
import com.lqj.domain.trade.model.entity.PayDiscountEntity;
import com.lqj.domain.trade.model.entity.UserEntity;
import com.lqj.domain.trade.model.valobj.GroupBuyProgressVO;

/**
 * @Author 李岐鉴
 * @Date 2025/11/10
 * @Description ITradeOrderService 类
 */
public interface ITradeOrderService {

    MarketPayOrderEntity queryNoPayMarketPayOrderByOutTradeNo(String userId, String outTradeNo);

    GroupBuyProgressVO queryGroupBuyProgress(String teamId);

    MarketPayOrderEntity lockMarketPayOrder(UserEntity userEntity, PayActivityEntity payActivityEntity, PayDiscountEntity payDiscountEntity);


}
