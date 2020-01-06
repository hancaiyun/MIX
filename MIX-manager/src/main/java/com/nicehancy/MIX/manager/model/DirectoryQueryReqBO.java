package com.nicehancy.MIX.manager.model;

import lombok.Getter;
import lombok.Setter;

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
public class DirectoryQueryReqBO {

    /**
     * 用户账号
     */
    private String userNo;

    /**
     * 一级目录名
     */
    private String primaryDirectory;

    /**
     * 是否查询一级目录下的笔记名列表
     * Y-是
     * N/null-否
     */
    private String fileInPrimary;

    /**
     * 二级目录名
     */
    private String secondaryDirectory;
}
