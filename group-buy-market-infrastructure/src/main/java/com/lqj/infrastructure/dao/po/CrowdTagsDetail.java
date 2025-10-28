package com.lqj.infrastructure.dao.po;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

/**
 * @Author 李岐鉴
 * @Date 2025/10/27
 * @Description CrowdTagsDetail 类
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CrowdTagsDetail {
    /** 自增ID */
    private Long id;
    /** 人群ID */
    private String tagId;
    /** 用户ID */
    private String userId;
    /** 创建时间 */
    private Date createTime;
    /** 更新时间 */
    private Date updateTime;
}
