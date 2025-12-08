package com.lqj.domain.activity.service.trial.factory;

import com.lqj.domain.activity.model.entity.MarketProductEntity;
import com.lqj.domain.activity.model.entity.TrialBalanceEntity;
import com.lqj.domain.activity.model.valobj.GroupBuyActivityDiscountVO;
import com.lqj.domain.activity.model.valobj.SkuVO;
import com.lqj.domain.activity.service.trial.node.RootNode;
import com.lqj.types.design.framework.tree.StrategyHandler;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.math.BigDecimal;

/**
 * @Author 李岐鉴
 * @Date 2025/10/25
 * @Description 活动策略工厂
 */
@Service
public class DefaultActivityStrategyFactory {

    @Resource
    private final RootNode rootNode;

    public DefaultActivityStrategyFactory(RootNode rootNode) {
        this.rootNode = rootNode;
    }

    public StrategyHandler<MarketProductEntity, DynamicContext, TrialBalanceEntity> strategyHandler() {
        return rootNode;
    }

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    @Builder
    public static class DynamicContext {
        // 拼团活动信息
        private GroupBuyActivityDiscountVO groupBuyActivityDiscountVO;

        // 商品信息
        private SkuVO skuVO;

        // 折扣信息
        private BigDecimal deductionPrice;

        // 支付信息
        private BigDecimal payPrice;

        // 活动可见性限制
        private boolean visible;

        // 活动参与限制
        private boolean enable;

    }
}
