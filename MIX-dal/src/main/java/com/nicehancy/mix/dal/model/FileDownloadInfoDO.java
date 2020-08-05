package com.nicehancy.mix.dal.model;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.data.mongodb.core.mapping.Document;
import java.util.Date;

/**
 * 文件下载DO
 * <p>
 * <p/>
 *
 * @author hancaiyun
 * @since 2020/8/4 14:10
 **/
@Getter
@Setter
@ToString
@Document("c_file_download_info")
public class FileDownloadInfoDO {

    /**
     * 用户编号
     */
    private String userNo;

    /**
     * 文件id
     */
    private String fileId;

    /**
     * 文件描述
     */
    private String fileDesc;

    /**
     * 文件生成结果
     * SUCCESS-成功
     * FAIL-失败
     */
    private String createResult;

    /**
     * 文件完整路径
     */
    private String fullFilePath;

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
