package com.nicehancy.mix.service.api.model.result;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import java.io.Serializable;
import java.util.Date;

/**
 * 文件上传记录DTO
 * <p>
 * <p/>
 *
 * @author hancaiyun
 * @since 2020/6/15 15:52
 **/
@Getter
@Setter
@ToString
public class FileUploadRecordDTO implements Serializable {
    /**
     * 序列化ID
     */
    private static final long serialVersionUID = 8948020642948522728L;

    /**
     * 用户编号
     */
    private String userNo;

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
     * 文件路径
     */
    private String filePath;

    /**
     * 文件状态
     */
    private String fileStatus;

    /**
     * 创建时间
     */
    private Date createdAt;

    /**
     * 创建人
     */
    private String createdBy;

    /**
     * 更新时间
     */
    private Date updatedAt;

    /**
     * 更新人
     */
    private String updatedBy;
}
