package com.lqj.test.domain.tag;

import com.lqj.domain.tag.service.ITagService;
import com.lqj.infrastructure.redis.IRedisService;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.redisson.api.RBitSet;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;

/**
 * @Author 李岐鉴
 * @Date 2025/10/27
 * @Description ITagServiceTest 类
 */
@SpringBootTest
@RunWith(SpringRunner.class)
@Slf4j
public class ITagServiceTest {

    @Resource
    private ITagService tagService;

    @Resource
    private IRedisService redisService;


    @Test
    public void test_tag_job() {
        tagService.execTagBatchJob("RQ_KJHKL98UU78H66554GFDV", "10001");
    }

    @Test
    public void test_get_tag_bitmap() {
        RBitSet bitSet = redisService.getBitSet("RQ_KJHKL98UU78H66554GFDV");
        log.info("测试结果: {}", bitSet.get(redisService.getIndexFromUserId("xfg02")));
    }
}
