package com.lqj.domain.tag.service;

/**
 * @Author 李岐鉴
 * @Date 2025/10/27
 * @Description ITagService 类
 */
public interface ITagService {
    /**
     * 执行人群标签批次任务
     *
     * @param tagId   人群ID
     * @param batchId 批次ID
     */
    void execTagBatchJob(String tagId, String batchId);
}
