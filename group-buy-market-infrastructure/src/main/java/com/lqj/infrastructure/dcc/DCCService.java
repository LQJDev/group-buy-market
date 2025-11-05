package com.lqj.infrastructure.dcc;

import com.lqj.types.annotations.DCCValue;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

/**
 * @Author 李岐鉴
 * @Date 2025/11/2
 * @Description 动态配置服务
 */
@Service
@Slf4j
public class DCCService {

    @DCCValue("downgradeSwitch:0")
    private String downgradeSwitch;

    @DCCValue("cutRange:100")
    private String cutRange;

    public boolean isDowngradeSwitch() {
        log.info("downgradeSwitch:{}", downgradeSwitch);
        return "1".equals(downgradeSwitch);
    }

    public boolean isCutRange(String userId) {
        log.info("cutRange:{}", cutRange);
        // 计算哈希码的绝对值
        int hashCode = Math.abs(userId.hashCode());
        // 获取最后两位数字
        int lastTwoDigits = hashCode % 100;
        // 判断是否在切量范围内
        if (lastTwoDigits <= Integer.parseInt(cutRange)) {
            return true;
        }
        return false;
    }

}
