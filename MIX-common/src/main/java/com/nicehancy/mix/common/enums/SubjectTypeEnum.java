package com.nicehancy.mix.common.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * <p>
 *     主体类型枚举
 *     评论主体
 * <p/>
 *
 * @author hancaiyun
 * @since 2020/11/6 15:00
 **/
@Getter
@AllArgsConstructor
public enum  SubjectTypeEnum {

    /**
     * 笔记
     */
    NOTE("NOTE", "笔记"),

    /**
     * 评论
     */
    COMMENT("COMMENT", "评论"),
    ;

    private final String code;

    private final String desc;
}
