package com.lqj.trigger.http;

import cn.bugstack.wrench.dynamic.config.center.domain.model.valobj.AttributeVO;
import com.lqj.api.IDCCService;
import com.lqj.api.response.Response;
import com.lqj.types.enums.ResponseCode;
import lombok.extern.slf4j.Slf4j;
import org.redisson.api.RTopic;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * @Author 李岐鉴
 * @Date 2025/11/3
 * @Description DCCService 类
 */
@RestController
@Slf4j
@CrossOrigin("*")
@RequestMapping("/api/v1/gbm/dcc/")
public class DCCController implements IDCCService {


    @Resource(name = "dynamicConfigCenterRedisTopic")
    private RTopic dccTopic;

    @RequestMapping(value = "update_config", method = RequestMethod.GET)
    @Override
    public Response<Boolean> updateConfig(@RequestParam String key, @RequestParam String value) {
        try {
            log.info("DCC 动态配置值变更 key:{} value:{}", key, value);
            dccTopic.publish(new AttributeVO(key, value));
            return Response.<Boolean>builder()
                    .code(ResponseCode.SUCCESS.getCode())
                    .info(ResponseCode.SUCCESS.getInfo())
                    .build();
        } catch (Exception e) {
            log.error("DCC 动态配置值变更失败 key:{} value:{}", key, value, e);
            return Response.<Boolean>builder()
                    .code(ResponseCode.UN_ERROR.getCode())
                    .info(ResponseCode.UN_ERROR.getInfo())
                    .build();
        }
    }
}
