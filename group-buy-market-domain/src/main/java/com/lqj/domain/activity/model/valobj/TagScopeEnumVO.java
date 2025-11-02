package com.lqj.domain.activity.model.valobj;

/**
 * @Author 李岐鉴
 * @Date 2025/11/2
 * @Description TagScopeEnumVO 类
 */

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public enum TagScopeEnumVO {

    VISIBLE(true, false, "是否可以看见拼团"),
    ENABLE(true, false, "是否可以参与拼团"),
    ;

    private Boolean allow;
    private Boolean refuse;
    private String desc;

}
