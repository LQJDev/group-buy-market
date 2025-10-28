package com.lqj.infrastructure.dao;

import com.lqj.infrastructure.dao.po.CrowdTagsDetail;
import org.apache.ibatis.annotations.Mapper;

/**
 * @Author 李岐鉴
 * @Date 2025/10/27
 * @Description ICrowdTagsDetail 类
 */
@Mapper
public interface ICrowdTagsDetailDao {
    void addCrowdTagsUserId(CrowdTagsDetail crowdTagsDetailReq);
}
