package com.lqj.domain.trade.service.lock.filter;

import cn.bugstack.wrench.design.framework.link.model2.handler.ILogicHandler;
import com.lqj.domain.trade.adapter.repository.ITradeRepository;
import com.lqj.domain.trade.model.entity.GroupBuyActivityEntity;
import com.lqj.domain.trade.model.entity.TradeLockRuleCommandEntity;
import com.lqj.domain.trade.model.entity.TradeLockRuleFilterBackEntity;
import com.lqj.domain.trade.service.lock.factory.TradeLockRuleFilterFactory;

import com.lqj.types.enums.ResponseCode;
import com.lqj.types.exception.AppException;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @Author 李岐鉴
 * @Date 2026/1/24
 * @Description TeamStockOccupyRuleFilter 类
 */
@Slf4j
@Service
public class TeamStockOccupyRuleFilter implements ILogicHandler<TradeLockRuleCommandEntity, TradeLockRuleFilterFactory.DynamicContext, TradeLockRuleFilterBackEntity> {

    @Resource
    private ITradeRepository repository;

    @Override
    public TradeLockRuleFilterBackEntity apply(TradeLockRuleCommandEntity requestParameter, TradeLockRuleFilterFactory.DynamicContext dynamicContext) throws Exception {
        log.info("交易规则过滤-组队库存校验{} activityId:{}", requestParameter.getUserId(), requestParameter.getActivityId());

        String teamId = requestParameter.getTeamId();
        if (StringUtils.isBlank(teamId)) {
            return TradeLockRuleFilterBackEntity.builder()
                    .userTakeOrderCount(dynamicContext.getUserTakeOrderCount())
                    .build();
        }
        GroupBuyActivityEntity groupBuyActivity = dynamicContext.getGroupBuyActivity();
        Integer target = groupBuyActivity.getTarget();
        Integer validTime = groupBuyActivity.getValidTime();
        String teamStockKey = dynamicContext.generateTeamStockKey(teamId);
        String recoveryTeamStockKey = dynamicContext.generateRecoveryTeamStockKey(teamId);
        boolean status = repository.occupyTeamStock(teamStockKey, recoveryTeamStockKey, target, validTime);
        if (!status) {
            log.warn("交易规则过滤-组队库存校验{} activityId:{} 抢占失败:{}", requestParameter.getUserId(), requestParameter.getActivityId(), teamStockKey);
            throw new AppException(ResponseCode.E0008);
        }

        return TradeLockRuleFilterBackEntity.builder()
                .userTakeOrderCount(dynamicContext.getUserTakeOrderCount())
                .recoveryTeamStockKey(recoveryTeamStockKey)
                .build();
    }
}
