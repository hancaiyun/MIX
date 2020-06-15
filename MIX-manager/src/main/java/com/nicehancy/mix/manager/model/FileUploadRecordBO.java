package com.nicehancy.mix.manager.model;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * 文件上传记录
 * <p>
 * <p/>
 *
 * @author hancaiyun
 * @since 2020/6/15 15:52
 **/
@Getter
@Setter
@ToString
public class FileUploadRecordBO {

    /**
     * 文件id
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
