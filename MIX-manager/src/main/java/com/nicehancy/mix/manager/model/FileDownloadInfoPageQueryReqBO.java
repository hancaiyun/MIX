package com.nicehancy.mix.manager.model;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * 文件下载信息分页查询请求BO
 * <p>
 * <p/>
 *
 * @author hancaiyun
 * @since 2020/6/15 15:46
 **/
@Getter
@Setter
@ToString
public class FileDownloadInfoPageQueryReqBO {

    /**
     * 用户编号
     */
    private String userNo;

    /**
     * 当前页
     */
    private Integer currentPage;

    /**
     * 每页条目数
     */
    private Integer pageSize;
}
