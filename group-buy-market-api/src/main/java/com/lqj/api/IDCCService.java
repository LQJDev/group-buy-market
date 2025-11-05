package com.lqj.api;

import com.lqj.api.response.Response;

/**
 * @Author 李岐鉴
 * @Date 2025/11/3
 * @Description IDCCService 类
 */
public interface IDCCService {

    Response<Boolean> updateConfig(String key, String value);
}
