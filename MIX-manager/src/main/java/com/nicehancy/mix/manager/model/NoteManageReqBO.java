package com.nicehancy.mix.manager.model;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;


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
@ToString
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
     * 二级目录名
     */
    private String secondaryDirectory;

    /**
     * 文档名称
     */
    private String fileName;

    /**
     * 操作类型
     * 增加-ADD
     * 删除-DELETE
     * 修改-EDIT
     */
    private String opType;

    /**
     * 操作位置
     */
    private String opLocation;

    /**
     * 操作值
     */
    private String opName;
}
