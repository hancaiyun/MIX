package com.nicehancy.mix.manager.model;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * 笔记分享请求对象
 * <p>
 * <p/>
 *
 * @author hancaiyun
 * @since 2019/11/13 19:41
 **/
@Getter
@Setter
@ToString
public class NoteShareReqBO {

    /**
     * 用户名（登录名）
     */
    private String userNo;

    /**
     * id
     */
    private Long id;

    /**
     * 分享标识
     * ShareFlagEnum
     */
    private String shareFlag;
}
