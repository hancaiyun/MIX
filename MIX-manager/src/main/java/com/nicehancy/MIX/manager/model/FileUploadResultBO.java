package com.nicehancy.MIX.manager.model;

import lombok.Getter;
import lombok.Setter;

/**
 * 文件上传返回结果
 * <p>
 * <p/>
 *
 * @author hancaiyun
 * @since 2019/12/5 17:35
 **/
@Getter
@Setter
public class FileUploadResultBO {

    /**
     * 文件id
     */
    private String fileId;

    /**
     * 文件名
     */
    private String fileName;
}
