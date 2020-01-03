package com.nicehancy.MIX.service.api.model.request.file;

import com.nicehancy.MIX.service.api.model.base.BaseReqDTO;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import net.sf.oval.constraint.MemberOf;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
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
     * 文件类型-大类
     * PICTURE-图片
     * DOCUMENT-文档
     */
    @NotNull(message = "文件类型不能为空")
    @NotBlank(message = "文件类型不能为空")
    @MemberOf(value = {"PICTURE", "DOCUMENT"}, message = "文件类型有误，只能是图片或者文档")
    private String fileType;

    /**
     * 文件类型-细类
     * HEAD-头像
     * DEFAULT-默认图片、自定义图片
     * EXCEL-表格文件
     * PDF-PDF类型文件
     *
     */
    @NotNull(message = "文件详细类型不能为空")
    @NotBlank(message = "文件相信类型不能为空")
    @MemberOf(value = {"DEFAULT", "HEAD", "EXCEL", "PDF"}, message = "暂不支持的文件类型")
    private String subFileType;

    /**
     * 文件数据
     */
    @NotNull(message = "文件数据不能为空")
    @NotBlank(message = "文件数据不能为空")
    private String fileData;
}
