package com.nicehancy.MIX.service.api.model.result;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;

/**
 * 密码重置结果DTO
 * <p>
 * <p/>
 *
 * @author hancaiyun
 * @since 2020/4/26 17:46
 **/
@Getter
@Setter
@ToString
public class PasswordResetDTO implements Serializable {

    /**
     * 序列化ID
     */
    private static final long serialVersionUID = 888945184418342187L;

    /**
     * 邮箱
     */
    private String email;
}
