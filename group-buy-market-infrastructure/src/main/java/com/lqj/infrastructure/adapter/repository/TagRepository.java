package com.lqj.infrastructure.adapter.repository;

import com.lqj.domain.tag.adapter.repository.ITagRepository;
import com.lqj.domain.tag.model.entity.CrowdTagsJobEntity;
import com.lqj.infrastructure.dao.ICrowdTagsDao;
import com.lqj.infrastructure.dao.ICrowdTagsDetailDao;
import com.lqj.infrastructure.dao.ICrowdTagsJobDao;
import com.lqj.infrastructure.dao.po.CrowdTags;
import com.lqj.infrastructure.dao.po.CrowdTagsDetail;
import com.lqj.infrastructure.dao.po.CrowdTagsJob;
import com.lqj.infrastructure.redis.IRedisService;
import org.redisson.api.RBitSet;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @Author 李岐鉴
 * @Date 2025/10/27
 * @Description TagRepository 类
 */
@Repository
public class TagRepository implements ITagRepository {

    @Resource
    private ICrowdTagsDao crowdTagsDao;

    @Resource
    private ICrowdTagsJobDao crowdTagsJobDao;

    @Resource
    private ICrowdTagsDetailDao crowdTagsDetailDao;

    @Resource
    private IRedisService redisService;

    @Override
    public CrowdTagsJobEntity queryCrowdTagsJobEntity(String tagId, String batchId) {
        CrowdTagsJob crowdTagsJob = new CrowdTagsJob();
        crowdTagsJob.setTagId(tagId);
        crowdTagsJob.setBatchId(batchId);
        crowdTagsJob = crowdTagsJobDao.queryCrowdTagsJob(crowdTagsJob);
        return CrowdTagsJobEntity.builder()
                .tagType(crowdTagsJob.getTagType())
                .tagRule(crowdTagsJob.getTagRule())
                .statEndTime(crowdTagsJob.getStatEndTime())
                .statStartTime(crowdTagsJob.getStatStartTime())
                .build();
    }

    @Override
    public void addCrowdTagsUserId(String tagId, String userId) {
        CrowdTagsDetail crowdTagsDetail = new CrowdTagsDetail();
        crowdTagsDetail.setTagId(tagId);
        crowdTagsDetail.setUserId(userId);
        crowdTagsDetailDao.addCrowdTagsUserId(crowdTagsDetail);
        try {
            RBitSet bitSet = redisService.getBitSet(tagId);
            bitSet.set(redisService.getIndexFromUserId(userId));
        } catch (DuplicateKeyException  ignored) {
        }
    }

    @Override
    public void updateCrowdTagsStatistics(String tagId, int count) {
        CrowdTags crowdTags = new CrowdTags();
        crowdTags.setTagId(tagId);
        crowdTags.setStatistics(count);
        crowdTagsDao.updateCrowdTagsStatistics(crowdTags);
    }
}
