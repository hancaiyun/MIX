package com.nicehancy.mix.dal.model;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;

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
@Document("c_file_upload_record")
public class FileUploadRecordDO {

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
