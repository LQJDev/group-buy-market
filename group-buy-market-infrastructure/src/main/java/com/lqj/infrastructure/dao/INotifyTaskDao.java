package com.lqj.infrastructure.dao;

import com.lqj.infrastructure.dao.po.NotifyTask;
import org.apache.ibatis.annotations.Mapper;

/**
 * @Author 李岐鉴
 * @Date 2025/12/9
 * @Description INotifyTaskDao 类
 */
@Mapper
public interface INotifyTaskDao {

    void insert(NotifyTask notifyTask);

}
