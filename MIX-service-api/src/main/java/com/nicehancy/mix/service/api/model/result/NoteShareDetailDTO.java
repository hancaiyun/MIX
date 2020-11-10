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
public class NoteShareDetailDTO implements Serializable {

    /**
     * 序列化ID
     */
    private static final long serialVersionUID = -3176429398302537331L;

    /**
     * 数据库id
     */
    private Long id;

    /**
     * 用户编号
     */
    private String userNo;

    /**
     * 文档名
     */
    private String documentName;

    /**
     * 文档内容
     */
    private String content;

    /**
     * 查看数
     */
    private Integer seeCount;

    /**
     * 支持数
     */
    private Integer supportCount;

    /**
     * 评论数
     */
    private Integer commentCount;

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
