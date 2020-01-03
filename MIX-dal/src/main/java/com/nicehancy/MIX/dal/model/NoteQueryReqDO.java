package com.nicehancy.MIX.dal.model;

import lombok.Getter;
import lombok.Setter;

/**
 * 笔记查询请求对象
 * <p>
 * <p/>
 *
 * @author hancaiyun
 * @since 2019/11/13 19:41
 **/
@Getter
@Setter
public class NoteQueryReqDO {

    /**
     * 用户名（登录名）
     */
    private String userNo;

    /**
     * 一级目录名
     */
    private String primaryDirectory;

    /**
     * 文档名
     */
    private String documentId;

    /**
     * 文档名
     */
    private String documentName;

    /**
     * 日志ID
     */
    private String traceLogId;
}
