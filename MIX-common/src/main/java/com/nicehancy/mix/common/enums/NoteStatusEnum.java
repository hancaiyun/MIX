package com.nicehancy.mix.common.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * 笔记状态枚举
 * <p>
 * <p/>
 *
 * @author hancaiyun
 * @since 2019/11/14 17:54
 **/
@Getter
@AllArgsConstructor
public enum NoteStatusEnum {

    /**
     * 可用
     */
    ENABLE("ENABLE", "可用"),

    /**
     * 不可用
     */
    DISABLE("DISABLE", "不可用"),

    /**
     * 锁定
     */
    LOCK("LOCK", "锁定"),

    ;

    private final String code;

    private final String desc;
}
