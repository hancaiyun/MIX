package com.nicehancy.mix.service.api.model.result;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;

/**
 * 共享笔记DTO
 * <p>
 * <p/>
 *
 * @author hancaiyun
 * @since 2019/10/17 12:26
 **/
@Getter
@Setter
@ToString
public class NoteShareInfoDTO implements Serializable {

    /**
     * 序列化ID
     */
    private static final long serialVersionUID = -1793753641653542243L;

    /**
     * 数据库id
     */
    private Long id;

    /**
     * 用户编号
     */
    private String userNo;

    /**
     * 头像
     */
    private String headCopy;

    /**
     * 用户昵称
     */
    private String nickName;

    /**
     * 文档名
     */
    private String documentName;

    /**
     * 创建时间
     */
    private String createdAt;

    /**
     * 创建人
     */
    private String createdBy;

    /**
     * 更新时间
     */
    private String updatedAt;

    /**
     * 更新人
     */
    private String updatedBy;
}
