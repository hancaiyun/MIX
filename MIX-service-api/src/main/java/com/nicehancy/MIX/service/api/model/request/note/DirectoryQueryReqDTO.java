package com.nicehancy.MIX.service.api.model.request.note;

import com.nicehancy.MIX.service.api.model.base.BaseReqDTO;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import net.sf.oval.constraint.NotNull;
import javax.validation.constraints.NotBlank;

/**
 * 一级目录列表查询接口
 * <p>
 * <p/>
 *
 * @author hancaiyun
 * @since 2020/1/3 10:37
 **/
@Getter
@Setter
@ToString
public class DirectoryQueryReqDTO extends BaseReqDTO {

    /**
     * 序列化ID
     */
    private static final long serialVersionUID = 8375211550774154485L;

    /**
     * 用户账号
     */
    @NotBlank(message = "用户账号不允许为空")
    @NotNull(message = "用户账号不允许为空")
    private String userNo;

    /**
     * 一级目录名
     */
    private String primaryDirectory;

    /**
     * 是否查询一级目录下的笔记名列表
     * Y-是
     * N/null-否
     */
    private String fileInPrimary;

    /**
     * 二级目录名
     */
    private String secondaryDirectory;
}
