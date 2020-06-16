package com.nicehancy.mix.dal.model.request;

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
     * 二级目录名
     */
    private String secondaryDirectory;

    /**
     * 文档名
     */
    private String documentName;

}
