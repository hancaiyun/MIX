package com.nicehancy.mix.service.api.model.request.file;

import com.nicehancy.mix.service.api.model.base.BaseReqDTO;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import net.sf.oval.constraint.NotBlank;
import net.sf.oval.constraint.NotNull;

import java.io.Serializable;

/**
 * 文件下载请求参数
 * <p>
 * <p/>
 *
 * @author hancaiyun
 * @since 2019/12/5 12:51
 **/
@Getter
@Setter
@ToString
public class FileShareRequestDTO extends BaseReqDTO implements Serializable {

    /**
     * 序列化ID
     */
    private static final long serialVersionUID = -336452548683000875L;

    /**
     * 文件id
     */
    @NotBlank(message = "文件ID不允许为空")
    @NotNull(message = "文件ID不允许为空")
    private String fileId;

    /**
     * 操作人
     */
    @NotBlank(message = "操作人不允许为空")
    @NotNull(message = "操作人不允许为空")
    private String operator;
}
