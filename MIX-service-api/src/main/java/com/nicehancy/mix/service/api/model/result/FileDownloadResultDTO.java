package com.nicehancy.mix.service.api.model.result;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;

/**
 * 文件下载返回对象
 * <p>
 * <p/>
 *
 * @author hancaiyun
 * @since 2019/12/5 12:53
 **/
@Getter
@Setter
@ToString
public class FileDownloadResultDTO implements Serializable {

    /**
     * 序列化ID
     */
    private static final long serialVersionUID = 3678996872295914652L;

    /**
     * 文件数据
     */
    private String fileData;
}
