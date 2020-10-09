package com.nicehancy.mix.manager.model;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * 文件共享查询请求参数
 * <p>
 * <p/>
 *
 * @author hancaiyun
 * @since 2020/9/27 17:33
 **/
@Getter
@Setter
@ToString
public class NoteShareQueryReqBO {

    /**
     * 用户名（登录名）
     */
    private String userNo;
}
