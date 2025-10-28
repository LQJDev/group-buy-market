package com.lqj.infrastructure.dao;

import com.lqj.infrastructure.dao.po.CrowdTags;
import com.lqj.infrastructure.dao.po.CrowdTagsJob;
import org.apache.ibatis.annotations.Mapper;

/**
 * @Author 李岐鉴
 * @Date 2025/10/27
 * @Description 人群标签
 */
@Mapper
public interface ICrowdTagsJobDao {
    CrowdTagsJob queryCrowdTagsJob(CrowdTagsJob crowdTagsJobReq);
}
