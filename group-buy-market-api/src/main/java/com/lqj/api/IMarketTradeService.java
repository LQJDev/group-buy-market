package com.lqj.api;

import com.lqj.api.dto.LockMarketPayOrderRequestDTO;
import com.lqj.api.dto.LockMarketPayOrderResponseDTO;
import com.lqj.api.response.Response;

/**
 * @Author 李岐鉴
 * @Date 2025/11/17
 * @Description IMarketTradeService 类
 */
public interface IMarketTradeService {

    Response<LockMarketPayOrderResponseDTO> lockMarketPayOrder(LockMarketPayOrderRequestDTO lockMarketPayOrderRequestDTO);

}
