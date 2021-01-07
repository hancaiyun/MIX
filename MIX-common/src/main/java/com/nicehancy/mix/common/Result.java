package com.nicehancy.mix.common;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import java.io.Serializable;

/**
 * <p>
 *     结果包装类
 * <p/>
 *
 * @author hancaiyun
 * @since 2019/5/5 18:57
 **/
@Getter
@Setter
@ToString
public class Result<T> implements Serializable {

    private static final long serialVersionUID = -503467586013152688L;
    private boolean success;
    private T result;
    private String errorCode;
    private String errorMsg;

    public Result() {
    }

    public Result(T result) {
        this.success = true;
        this.result = result;
    }

    public Result(String errorCode, String errorMsg) {
        this.success = false;
        this.errorCode = errorCode;
        this.errorMsg = errorMsg;
    }

    public Result(String errorCode, String errorMsg, String primaryErrorCode, String primaryErrorMsg, String primaryErrorIP) {
        this.success = false;
        this.errorCode = errorCode;
        this.errorMsg = errorMsg;
    }

    public boolean isSuccess() {
        return this.success;
    }

    public T getResult() {
        return this.result;
    }

    public void setResult(T result) {
        this.success = true;
        this.result = result;
    }

    public void setErrorCode(String errorCode) {
        this.success = false;
        this.errorCode = errorCode;
    }

    public String getErrorMsg() {
        return this.errorMsg;
    }

    public void setErrorMsg(String errorMsg) {
        this.success = false;
        this.errorMsg = errorMsg;
    }
}