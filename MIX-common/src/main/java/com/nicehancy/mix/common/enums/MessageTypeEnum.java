package com.nicehancy.mix.common.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * 消息类型枚举类
 * <p>
 * <p/>
 *
 * @author hancaiyun
 * @since 2020/5/8 16:33
 **/
@Getter
@AllArgsConstructor
public enum MessageTypeEnum {

    /**
     * 短信
     */
    MESSAGE("MESSAGE", "短信"),

    /**
     * 邮件
     */
    EMAIL("EMAIL", "邮件"),
    ;

    private final String code;

    private final String desc;
}
