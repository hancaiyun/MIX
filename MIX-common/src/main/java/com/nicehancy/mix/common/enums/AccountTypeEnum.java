package com.nicehancy.mix.common.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.util.StringUtils;

/**
 * 账号类型枚举
 * <p>
 * <p/>
 *
 * @author hancaiyun
 * @since 2020/7/30 9:30
 **/
@Getter
@AllArgsConstructor
public enum AccountTypeEnum {

    /**
     * 办公账号
     */
    WORK("WORK", "办公账号"),

    /**
     * 个人账号
     */
    OWN("OWN", "个人账号"),

    /**
     * 未知
     */
    UNKNOWN("UNKNOWN","未知"),
    ;

    private final String code;

    private final String desc;

    public static  AccountTypeEnum expireOfCode(String code) {

        if (!StringUtils.isEmpty(code)) {
            for (AccountTypeEnum accountTypeEnum : AccountTypeEnum.values()) {
                if (accountTypeEnum.getCode().equals(code)) {
                    return accountTypeEnum;
                }
            }
        }
        return UNKNOWN;
    }

    public static  AccountTypeEnum expireOfDesc(String desc) {

        if (!StringUtils.isEmpty(desc)) {
            for (AccountTypeEnum accountTypeEnum : AccountTypeEnum.values()) {
                if (accountTypeEnum.getDesc().equals(desc)) {
                    return accountTypeEnum;
                }
            }
        }
        return UNKNOWN;
    }
}
