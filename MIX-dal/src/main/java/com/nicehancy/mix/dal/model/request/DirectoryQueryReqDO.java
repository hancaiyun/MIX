package com.nicehancy.mix.dal.model.request;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * 笔记目录查询请求参数
 * <p>
 * <p/>
 *
 * @author hancaiyun
 * @since 2020/1/6 15:09
 **/
@Getter
@Setter
@ToString
public class DirectoryQueryReqDO {

    /**
     * 用户账号
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
}
