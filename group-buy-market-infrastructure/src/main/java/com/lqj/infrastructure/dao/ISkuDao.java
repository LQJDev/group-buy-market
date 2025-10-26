package com.lqj.infrastructure.dao;

import com.lqj.infrastructure.dao.po.Sku;
import org.apache.ibatis.annotations.Mapper;

/**
 * @Author 李岐鉴
 * @Date 2025/10/26
 * @Description ISkuDao 类
 */
@Mapper
public interface ISkuDao {
    Sku querySkuByGoodsId(String goodsId);
}
