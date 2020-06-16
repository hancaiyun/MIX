package com.nicehancy.mix.manager.model;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import net.sf.oval.constraint.MemberOf;
import net.sf.oval.constraint.NotBlank;
import net.sf.oval.constraint.NotNull;
import org.springframework.web.multipart.MultipartFile;

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
@ToString
public class FileUploadRequestBO {

    /**
     * 用户编号
     */
    private String userNo;

    /**
     * 文件数据
     */
    private MultipartFile fileData;

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
}
