package com.lqj.api;

import com.lqj.api.dto.GoodsMarketRequestDTO;
import com.lqj.api.dto.GoodsMarketResponseDTO;
import com.lqj.api.response.Response;

/**
 * @Author 李岐鉴
 * @Date 2025/12/13
 * @Description IMarketIndexService 类
 */
public interface IMarketIndexService {

    /**
     * 查询拼团营销配置
     *
     * @param goodsMarketRequestDTO 营销商品信息
     * @return 营销配置信息
     */
    Response<GoodsMarketResponseDTO> queryGroupBuyMarketConfig(GoodsMarketRequestDTO goodsMarketRequestDTO);
}
