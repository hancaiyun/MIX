package com.nicehancy.mix.common.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * 消息发送结果枚举类
 * <p>
 * <p/>
 *
 * @author hancaiyun
 * @since 2020/5/8 16:42
 **/
@Getter
@AllArgsConstructor
public enum SendResultEnum {

    /**
     * 成功
     */
    SUCCESS("SUCCESS", "成功"),

    /**
     * 失败
     */
    FAILURE("FAILURE", "失败"),

    ;
    private final String code;

    private final String desc;
}
