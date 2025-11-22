package com.lqj.test.types.rule01.logic;

import com.lqj.test.types.rule02.factory.Rule02TradeRuleFactory;
import com.lqj.types.design.framework.link.model1.AbstractLogicLink;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

/**
 * @Author 李岐鉴
 * @Date 2025/11/22
 * @Description RuleLogic102 类
 */
@Service
@Slf4j
public class RuleLogic102 extends AbstractLogicLink<String, Rule02TradeRuleFactory.DynamicContext, String> {


    @Override
    public String apply(String requestParameter, Rule02TradeRuleFactory.DynamicContext dynamicContext) throws Exception {
        log.info("link model01 RuleLogic102");
        return "link model01 单实例链";
    }
}
