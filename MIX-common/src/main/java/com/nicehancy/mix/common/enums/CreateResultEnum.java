package com.nicehancy.mix.common.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.util.StringUtils;

/**
 * 文件生成结果
 * <p>
 * <p/>
 *
 * @author hancaiyun
 * @since 2020/8/4 14:41
 **/
@Getter
@AllArgsConstructor
public enum CreateResultEnum {

    /**
     * 成功
     */
    SUCCESS("SUCCESS", "成功"),

    /**
     * 失败
     */
    FAIL("FAIL", "失败"),

    /**
     * 未知
     */
    UNKNOWN("UNKNOWN","未知"),
    ;

    private final String code;

    private final String desc;

    public static  CreateResultEnum expireOfCode(String code) {

        if (!StringUtils.isEmpty(code)) {
            for (CreateResultEnum createResultEnum : CreateResultEnum.values()) {
                if (createResultEnum.getCode().equals(code)) {
                    return createResultEnum;
                }
            }
        }
        return UNKNOWN;
    }
}
