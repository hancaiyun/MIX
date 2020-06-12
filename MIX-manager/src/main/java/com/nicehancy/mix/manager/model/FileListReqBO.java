package com.nicehancy.mix.manager.model;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

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
@ToString
public class FileListReqBO {

    /**
     * 用户账号
     */
    private String userNo;

    /**
     * 一级目录名
     */
    private String primaryDirectory;
}
