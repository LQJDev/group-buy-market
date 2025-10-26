package com.lqj.domain.activity.adapter.repository;

import com.lqj.domain.activity.model.valobj.GroupBuyActivityDiscountVO;
import com.lqj.domain.activity.model.valobj.SkuVO;
import org.springframework.stereotype.Repository;

/**
 * @Author 李岐鉴
 * @Date 2025/10/26
 * @Description IActivityRepository 类
 */
public interface IActivityRepository {

    GroupBuyActivityDiscountVO queryGroupBuyActivityDiscountVO(String source, String channel);

    SkuVO querySkuByGoodsId(String goodsId);

}
