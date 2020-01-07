package com.nicehancy.MIX.dal.model;

import lombok.Getter;
import lombok.Setter;

/**
 * 一级目录下文件列表查询请求DTO
 * <p>
 * <p/>
 *
 * @author hancaiyun
 * @since 2020/1/3 10:37
 **/
@Getter
@Setter
public class FileListReqDO {

    /**
     * 用户账号
     */
    private String userNo;

    /**
     * 一级目录名
     */
    private String primaryDirectory;
}
