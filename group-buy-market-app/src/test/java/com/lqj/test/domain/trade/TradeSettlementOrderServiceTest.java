package com.lqj.test.domain.trade;


import com.alibaba.fastjson2.JSON;
import com.lqj.domain.trade.model.entity.TradePaySettlementEntity;
import com.lqj.domain.trade.model.entity.TradePaySuccessEntity;
import com.lqj.domain.trade.service.ITradeSettlementOrderService;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;
import java.util.Date;

/**
 * @author 李岐鉴
 * @description 拼团交易结算服务测试
 * @create 2025-12-09
 */
@Slf4j
@RunWith(SpringRunner.class)
@SpringBootTest
public class TradeSettlementOrderServiceTest {

    @Resource
    private ITradeSettlementOrderService tradeSettlementOrderService;

    @Test
    public void test_settlementMarketPayOrder() throws Exception {
        TradePaySuccessEntity tradePaySuccessEntity = new TradePaySuccessEntity();
        tradePaySuccessEntity.setSource("s02");
        tradePaySuccessEntity.setChannel("c02");
        tradePaySuccessEntity.setUserId("xfg05");
        tradePaySuccessEntity.setOutTradeNo("994542208701");
        tradePaySuccessEntity.setOutTradeTime(new Date());
        TradePaySettlementEntity tradePaySettlementEntity = tradeSettlementOrderService.settlementMarketPayOrder(tradePaySuccessEntity);
        log.info("请求参数:{}", JSON.toJSONString(tradePaySuccessEntity));
        log.info("测试结果:{}", JSON.toJSONString(tradePaySettlementEntity));
    }

}
