package com.nicehancy.mix.service.api.model.result;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import java.io.Serializable;

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
public class FileUploadResultDTO implements Serializable {

    /**
     * 序列化ID
     */
    private static final long serialVersionUID = -8962305379245557748L;

    /**
     * 文件类型
     */
    private String fileType;

    /**
     * 文件名
     */
    private String fileName;
}
