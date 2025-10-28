package com.lqj.domain.tag.adapter.repository;

import com.lqj.domain.tag.model.entity.CrowdTagsJobEntity;

/**
 * @Author 李岐鉴
 * @Date 2025/10/27
 * @Description 人群标签仓储接口
 */
public interface ITagRepository {

    CrowdTagsJobEntity queryCrowdTagsJobEntity(String tagId, String batchId);

    void addCrowdTagsUserId(String tagId, String userId);

    void updateCrowdTagsStatistics(String tagId, int count);
}
