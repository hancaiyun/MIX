package com.nicehancy.mix.manager.model;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

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
@ToString
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
