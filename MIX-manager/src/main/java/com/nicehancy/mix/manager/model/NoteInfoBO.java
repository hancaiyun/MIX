package com.nicehancy.mix.manager.model;

import com.nicehancy.mix.manager.model.base.BaseBO;
import lombok.Getter;
import lombok.Setter;

/**
 * 笔记BO模型
 * <p>
 * <p/>
 *
 * @author hancaiyun
 * @since 2019/10/17 10:50
 **/
@Getter
@Setter
public class NoteInfoBO extends BaseBO {

    /**
     * 用户编号
     */
    private String userNo;

    /**
     * 一级目录
     */
    private String primaryDirectory;

    /**
     * 二级目录
     */
    private String secondaryDirectory;

    /**
     * 三级目录
     */
    private String tertiaryDirectory;

    /**
     * 文档名
     */
    private String documentName;

    /**
     * 文档内容
     */
    private String content;

    /**
     * 状态
     */
    private String status;

    /**
     * 备注
     */
    private String remark;
}
