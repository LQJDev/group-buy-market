package com.lqj.domain.activity.service;

import com.lqj.domain.activity.model.entity.MarketProductEntity;
import com.lqj.domain.activity.model.entity.TrialBalanceEntity;
import com.lqj.domain.activity.model.entity.UserGroupBuyOrderDetailEntity;
import com.lqj.domain.activity.model.valobj.TeamStatisticVO;

import java.util.List;

/**
 * @Author 李岐鉴
 * @Date 2025/10/25
 * @Description IIndexGroupBuyMarketService 类
 */
public interface IIndexGroupBuyMarketService {

    TrialBalanceEntity indexMarketTrial(MarketProductEntity marketProductEntity) throws Exception;

    /**
     * 查询进行中的用户团购订单详情列表
     * @param activityId 活动ID
     * @param userId 用户ID
     * @param ownerCount 个人数量
     * @param randomCount 随机数量
     * @return 用户拼团明细数量
     */
    List<UserGroupBuyOrderDetailEntity> queryInProgressUserGroupBuyOrderDetailList(Long activityId, String userId, Integer ownerCount, int randomCount);


    /**
     * 活动拼团队伍总结
     *
     * @param activityId 活动ID
     * @return 队伍统计
     */
    TeamStatisticVO queryTeamStatisticByActivityId(Long activityId);

}
