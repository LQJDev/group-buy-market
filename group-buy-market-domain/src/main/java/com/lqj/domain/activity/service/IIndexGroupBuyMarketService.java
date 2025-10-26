package com.lqj.domain.activity.service;

import com.lqj.domain.activity.model.entity.MarketProductEntity;
import com.lqj.domain.activity.model.entity.TrialBalanceEntity;

/**
 * @Author 李岐鉴
 * @Date 2025/10/25
 * @Description IIndexGroupBuyMarketService 类
 */
public interface IIndexGroupBuyMarketService {

    TrialBalanceEntity indexMarketTrial(MarketProductEntity marketProductEntity) throws Exception;
}
