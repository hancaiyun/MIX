package com.nicehancy.mix.manager.model;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * 账号信息DO
 * <p>
 * <p/>
 *
 * @author hancaiyun
 * @since 2020/7/10 14:38
 **/
@Getter
@Setter
@ToString
public class AccountInfoImportResultBO {

    /**
     * 结果
     * 成功、失败
     */
    private String result;

    /**
     * 原因
     */
    private String reason;
}
