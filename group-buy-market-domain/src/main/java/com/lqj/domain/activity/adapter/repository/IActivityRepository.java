package com.lqj.domain.activity.adapter.repository;

import com.lqj.domain.activity.model.valobj.GroupBuyActivityDiscountVO;
import com.lqj.domain.activity.model.valobj.SCSkuActivityVO;
import com.lqj.domain.activity.model.valobj.SkuVO;
import org.springframework.stereotype.Repository;

/**
 * @Author 李岐鉴
 * @Date 2025/10/26
 * @Description IActivityRepository 类
 */
public interface IActivityRepository {

    GroupBuyActivityDiscountVO queryGroupBuyActivityDiscountVO(Long activityId);

    SkuVO querySkuByGoodsId(String goodsId);

    SCSkuActivityVO querySCSkuActivityVOBySCGoodsId(String source, String channel, String goodsId);

    boolean isTagCrowRange(String tagId, String userId);

    boolean downgradeSwitch();

    boolean cutRange(String userId);
}
