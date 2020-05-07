package com.nicehancy.mix.manager.model;

import lombok.Getter;
import lombok.Setter;

/**
 * 文件上传请求对象
 * <p>
 * <p/>
 *
 * @author hancaiyun
 * @since 2019/12/5 11:26
 **/
@Getter
@Setter
public class FileUploadRequestBO {

    /**
     * 用户编号
     */
    private String userNo;

    /**
     * 文件类型-大类
     * PICTURE-图片
     * DOCUMENT-文档
     */
    private String fileType;

    /**
     * 文件类型-细类
     *
     * HEAD-头像
     * DEFAULT-默认图片、自定义图片
     * EXCEL-表格文件
     * PDF-PDF类型文件
     *
     */
    private String subFileType;

    /**
     * 文件数据
     */
    private String fileData;
}
