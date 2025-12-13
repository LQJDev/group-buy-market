package com.lqj.types.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 * @Author 李岐鉴
 * @Date 2025/12/12
 * @Description NotifyTaskHTTPEnumVO 类
 */
@Getter
@AllArgsConstructor
@NoArgsConstructor
public enum NotifyTaskHTTPEnumVO {

    SUCCESS("success", "成功"),
    ERROR("error", "失败"),
    NULL(null, "空执行"),;

    private String code;
    private String info;

}
