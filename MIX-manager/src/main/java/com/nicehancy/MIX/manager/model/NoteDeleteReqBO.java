package com.nicehancy.MIX.manager.model;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * 笔记删除请求对象
 * <p>
 * <p/>
 *
 * @author hancaiyun
 * @since 2019/11/13 19:41
 **/
@Getter
@Setter
@ToString
public class NoteDeleteReqBO {

    /**
     * 用户名（登录名）
     */
    private String userNo;

    /**
     * 一级目录名
     */
    private String primaryDirectory;

    /**
     * 二级目录名
     */
    private String secondaryDirectory;

    /**
     * 文档名称
     */
    private String documentName;
}
