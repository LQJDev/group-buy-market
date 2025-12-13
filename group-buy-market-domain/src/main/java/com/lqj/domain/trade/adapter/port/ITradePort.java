package com.lqj.domain.trade.adapter.port;

import com.lqj.domain.trade.model.entity.NotifyTaskEntity;

/**
 * @Author 李岐鉴
 * @Date 2025/12/12
 * @Description 交易接口服务接口
 */
public interface ITradePort {

    String groupBuyNotify(NotifyTaskEntity notifyTask) throws Exception;

}
