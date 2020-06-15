package com.nicehancy.mix.manager.model;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * 文件上传记录分页查询
 * <p>
 * <p/>
 *
 * @author hancaiyun
 * @since 2020/6/15 15:46
 **/
@Getter
@Setter
@ToString
public class FileUploadRecordPageQueryReqBO {

    /**
     * 文件ID
     */
    private String fileId;

    /**
     * 文件名
     */
    private String fileName;

    /**
     * 文件类型
     */
    private String fileType;

    /**
     * 当前页
     */
    private Integer currentPage;

    /**
     * 每页条目数
     */
    private Integer pageSize;
}
