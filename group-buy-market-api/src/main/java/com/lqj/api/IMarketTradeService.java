package com.lqj.api;

import com.lqj.api.dto.*;
import com.lqj.api.response.Response;

/**
 * @Author 李岐鉴
 * @Date 2025/11/17
 * @Description IMarketTradeService 类
 */
public interface IMarketTradeService {

    /**
     * 锁定营销支付订单
     *
     * @param lockMarketPayOrderRequestDTO 锁定商品信息
     * @return 锁定结果信息
     */
    Response<LockMarketPayOrderResponseDTO> lockMarketPayOrder(LockMarketPayOrderRequestDTO lockMarketPayOrderRequestDTO);

    /**
     * 营销结算
     *
     * @param requestDTO 结算商品信息
     * @return 结算结果信息
     */
    Response<SettlementMarketPayOrderResponseDTO> settlementMarketPayOrder(SettlementMarketPayOrderRequestDTO requestDTO);


    /**
     * 营销拼团退单
     *
     * @param requestDTO 退单请求信息
     * @return 退单结果信息
     */
    Response<RefundMarketPayOrderResponseDTO> refundMarketPayOrder(RefundMarketPayOrderRequestDTO requestDTO);

}
