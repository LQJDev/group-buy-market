package com.lqj.infrastructure.dao;

import com.lqj.infrastructure.dao.po.SCSkuActivity;
import org.apache.ibatis.annotations.Mapper;

/**
 * @Author 李岐鉴
 * @Date 2025/10/28
 * @Description ISCSkuActivityDao 类
 */
@Mapper
public interface ISCSkuActivityDao {

    SCSkuActivity querySCSkuActivityBySCGoodsId(SCSkuActivity scSkuActivity);

}
