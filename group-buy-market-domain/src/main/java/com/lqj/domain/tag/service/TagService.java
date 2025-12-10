package com.lqj.domain.tag.service;

import com.lqj.domain.tag.adapter.repository.ITagRepository;
import com.lqj.domain.tag.model.entity.CrowdTagsJobEntity;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
 * @Author 李岐鉴
 * @Date 2025/10/27
 * @Description 人群标签服务
 */
@Slf4j
@Service
public class TagService implements ITagService{

    @Resource
    private ITagRepository repository;

    @Override
    public void execTagBatchJob(String tagId, String batchId) {
        log.info("人群标签批次任务 tagId:{} batchId:{}", tagId, batchId);

        // 1、查询批次任务
        CrowdTagsJobEntity crowdTagsJobEntity = repository.queryCrowdTagsJobEntity(tagId, batchId);

        // 2、采集用户数据 - 这部分需要采集用户的消费类数据，后续有用户发起拼团再处理

        // 3、数据写入记录
        List<String> userIdList = new ArrayList<String>(){
            {
                add("xfg04");
                add("xfg05");
                add("xfg06");
                add("xfg07");
                add("xfg08");
                add("xfg09");
            }
        };
        for (String userId : userIdList) {
            repository.addCrowdTagsUserId(tagId, userId);
        }

        // 更新人群标签统计量
        repository.updateCrowdTagsStatistics(tagId, userIdList.size());
    }
}
