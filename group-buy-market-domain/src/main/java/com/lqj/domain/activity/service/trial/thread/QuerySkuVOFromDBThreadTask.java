package com.lqj.domain.activity.service.trial.thread;

import com.lqj.domain.activity.adapter.repository.IActivityRepository;
import com.lqj.domain.activity.model.valobj.SkuVO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.concurrent.Callable;

/**
 * @Author 李岐鉴
 * @Date 2025/10/26
 * @Description QuerySkuVOFromDBThreadTask 类
 */
@Slf4j
public class QuerySkuVOFromDBThreadTask implements Callable<SkuVO> {

    private final String goodsId;

    private final IActivityRepository repository;

    public QuerySkuVOFromDBThreadTask(String goodsId, IActivityRepository repository) {
        this.goodsId = goodsId;
        this.repository = repository;
    }

    @Override
    public SkuVO call(){
        return repository.querySkuByGoodsId(goodsId);
    }
}
