package com.nicehancy.mix.service.api.model.request.file;

import com.nicehancy.mix.service.api.model.base.BaseReqDTO;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import net.sf.oval.constraint.NotBlank;
import net.sf.oval.constraint.NotNull;
import org.springframework.web.multipart.MultipartFile;

import java.io.Serializable;

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
public class FileUploadRequestDTO extends BaseReqDTO implements Serializable{

    /**
     * 序列化ID
     */
    private static final long serialVersionUID = 7963555740583338874L;

    /**
     * 用户编号
     */
    @NotNull(message = "用户编号不能为空")
    @NotBlank(message = "用户编号不能为空")
    private String userNo;

    /**
     * 文件数据
     */
    @NotNull(message = "文件数据不能为空")
    @NotBlank(message = "文件数据不能为空")
    private MultipartFile fileData;
}
