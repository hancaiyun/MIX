package com.nicehancy.mix.service.api.model.request.note;

import com.nicehancy.mix.service.api.model.base.BaseReqDTO;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import net.sf.oval.constraint.NotBlank;
import net.sf.oval.constraint.NotNull;
import org.springframework.web.multipart.MultipartFile;

/**
 * 账号导入请求DTO
 * <p>
 * <p/>
 *
 * @author hancaiyun
 * @since 2020/7/29 14:47
 **/
@Getter
@Setter
@ToString
public class AccountImportReqDTO extends BaseReqDTO {

    /**
     * 序列化ID
     */
    private static final long serialVersionUID = -6511967541710008200L;

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
