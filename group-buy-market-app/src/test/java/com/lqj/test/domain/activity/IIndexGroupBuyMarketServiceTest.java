package com.lqj.test.domain.activity;

import com.alibaba.fastjson.JSON;
import com.lqj.domain.activity.model.entity.MarketProductEntity;
import com.lqj.domain.activity.model.entity.TrialBalanceEntity;
import com.lqj.domain.activity.service.IIndexGroupBuyMarketService;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;

/**
 * @Author 李岐鉴
 * @Date 2025/10/26
 * @Description IIndexGroupBuyMarketServiceTest 类
 */
@SpringBootTest
@RunWith(SpringRunner.class)
@Slf4j
public class IIndexGroupBuyMarketServiceTest {

    @Resource
    private IIndexGroupBuyMarketService iIndexGroupBuyMarketService;

    @Test
    public void test() throws Exception {
        MarketProductEntity marketProductEntity = new MarketProductEntity();
        marketProductEntity.setUserId("lqj");
        marketProductEntity.setSource("s01");
        marketProductEntity.setChannel("c01");
        marketProductEntity.setGoodsId("9890001");
        TrialBalanceEntity trialBalanceEntity = iIndexGroupBuyMarketService.indexMarketTrial(marketProductEntity);
        log.info("trialBalanceEntity: {}", JSON.toJSONString(marketProductEntity));
        log.info("trialBalanceEntity: {}", JSON.toJSONString(trialBalanceEntity));
    }
}
