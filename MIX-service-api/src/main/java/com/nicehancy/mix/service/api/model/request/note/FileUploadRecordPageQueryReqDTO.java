package com.nicehancy.mix.service.api.model.request.note;

import com.nicehancy.mix.service.api.model.base.BaseReqDTO;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import net.sf.oval.constraint.*;

import java.io.Serializable;

/**
 * 文件上传记录分页查询
 * <p>
 * <p/>
 *
 * @author hancaiyun
 * @since 2020/6/15 15:46
 **/
@Getter
@Setter
@ToString
public class FileUploadRecordPageQueryReqDTO extends BaseReqDTO implements Serializable {

    /**
     * 序列化ID
     */
    private static final long serialVersionUID = -399878446878099197L;

    /**
     * 用户编号
     */
    @NotBlank(message = "用户账号不允许为空")
    @NotNull(message = "用户账号不允许为空")
    private String userNo;

    /**
     * 文件ID
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
     * 当前页
     */
    @NotNull(message = "当前页不能为空")
    @MatchPattern(pattern = "^\\d*$", message = "当前页请输入整数")
    @Min(value=1, message="当前页不能小于1")
    private Integer currentPage;

    /**
     * 每页条目数
     */
    @NotNull(message = "每页显示数量不能为空")
    @MatchPattern(pattern = "^\\d*$", message = "每页显示数量请输入整数")
    @Max(value=100, message="每页显示数量不能大于100")
    @Min(value=1, message="每页显示数量不能小于1")
    private Integer pageSize;
}
