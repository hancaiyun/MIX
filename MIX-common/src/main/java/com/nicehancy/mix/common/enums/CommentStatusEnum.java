package com.nicehancy.mix.common.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * <p>
 *     评论状态枚举类
 * <p/>
 *
 * @author hancaiyun
 * @since 2020/11/6 14:39
 **/
@Getter
@AllArgsConstructor
public enum CommentStatusEnum {

    /**
     * 可用
     */
    ENABLE("ENABLE", "可用"),

    /**
     * 不可用
     */
    DISABLE("DISABLE", "不可用"),
    ;

    private final String code;

    private final String desc;
}
