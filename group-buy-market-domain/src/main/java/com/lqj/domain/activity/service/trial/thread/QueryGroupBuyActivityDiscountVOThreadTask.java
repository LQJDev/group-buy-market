package com.lqj.domain.activity.service.trial.thread;

import com.lqj.domain.activity.adapter.repository.IActivityRepository;
import com.lqj.domain.activity.model.valobj.GroupBuyActivityDiscountVO;
import com.lqj.domain.activity.model.valobj.SCSkuActivityVO;
import com.lqj.domain.activity.model.valobj.SkuVO;

import javax.annotation.Resource;
import java.util.concurrent.Callable;

/**
 * @Author 李岐鉴
 * @Date 2025/10/26
 * @Description 查询营销配置任务
 */
public class QueryGroupBuyActivityDiscountVOThreadTask implements Callable<GroupBuyActivityDiscountVO> {


    private final String source;

    private final String channel;

    private final IActivityRepository activityRepository;

    private final String goodsId;

    public QueryGroupBuyActivityDiscountVOThreadTask(String source, String channel, String goodsId,
                                                     IActivityRepository activityRepository) {
        this.source = source;
        this.channel = channel;
        this.activityRepository = activityRepository;
        this.goodsId = goodsId;
    }

    @Override
    public GroupBuyActivityDiscountVO call() {
        SCSkuActivityVO scSkuActivityVORsp = activityRepository.querySCSkuActivityVOBySCGoodsId(source, channel, goodsId);
        if (null == scSkuActivityVORsp) {
            return null;
        }
        return activityRepository.queryGroupBuyActivityDiscountVO(scSkuActivityVORsp.getActivityId());
    }
}
