package com.nicehancy.MIX.manager.model;

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
public class NoteManageReqBO {

    /**
     * 用户名（登录名）
     */
    private String userNo;

    /**
     * 一级目录名
     */
    private String primaryDirectory;

    /**
     * 操作类型
     * 增加-ADD
     * 删除-DELETE
     */
    private String operatorType;

    /**
     * 文档名称
     */
    private String documentName;
}
