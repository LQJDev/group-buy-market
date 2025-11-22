package com.lqj.test.types.rule01.logic;

import com.lqj.test.types.rule02.factory.Rule02TradeRuleFactory;
import com.lqj.types.design.framework.link.model1.AbstractLogicLink;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

/**
 * @Author 李岐鉴
 * @Date 2025/11/22
 * @Description RuleLogic101 类
 */
@Slf4j
@Service
public class RuleLogic101 extends AbstractLogicLink<String, Rule02TradeRuleFactory.DynamicContext, String> {


    @Override
    public String apply(String requestParameter, Rule02TradeRuleFactory.DynamicContext dynamicContext) throws Exception {
        log.info("link model01 RuleLogic101");

        return next(requestParameter, dynamicContext);
    }
}
