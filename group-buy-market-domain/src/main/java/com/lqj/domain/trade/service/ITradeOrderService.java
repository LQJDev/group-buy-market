package com.lqj.domain.trade.service;

import com.lqj.domain.trade.model.entity.MarketPayOrderEntity;
import com.lqj.domain.trade.model.entity.PayActivityEntity;
import com.lqj.domain.trade.model.entity.PayDiscountEntity;
import com.lqj.domain.trade.model.entity.UserEntity;
import com.lqj.domain.trade.model.valobj.GroupBuyProgressVO;

/**
 * @Author 李岐鉴
 * @Date 2025/11/10
 * @Description 交易订单服务接口
 */
public interface ITradeOrderService {

    /**
     * 查询未支付订单
     * @param userId 用户id
     * @param outTradeNo 外部唯一单号
     * @return 拼团，预购订单营销实体对象
     */
    MarketPayOrderEntity queryNoPayMarketPayOrderByOutTradeNo(String userId, String outTradeNo);


    /**
     * 查询拼团进度
     * @param teamId 拼团id
     * @return 拼团进度
     */
    GroupBuyProgressVO queryGroupBuyProgress(String teamId);


    /**
     * 锁定，营销预支付订单，商品下单前，预购锁定
     * @param userEntity 用户实体
     * @param payActivityEntity 拼团，支付活动实体对象
     * @param payDiscountEntity 拼团，支付优惠实体对象
     * @return 拼团，预购订单营销实体对象
     */
    MarketPayOrderEntity lockMarketPayOrder(UserEntity userEntity, PayActivityEntity payActivityEntity, PayDiscountEntity payDiscountEntity) throws Exception;


}
