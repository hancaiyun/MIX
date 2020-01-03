package com.nicehancy.MIX.manager.model;

import lombok.Getter;
import lombok.Setter;

/**
 * 笔记保存请求对象
 * <p>
 * <p/>
 *
 * @author hancaiyun
 * @since 2019/11/13 19:41
 **/
@Getter
@Setter
public class NoteSaveReqBO {

    /**
     * 用户名（登录名）
     */
    private String userNo;

    /**
     * 一级目录名
     */
    private String primaryDirectory;

    /**
     * 文档id
     */
    private String documentId;

    /**
     * 笔记内容
     */
    private String content;
}
