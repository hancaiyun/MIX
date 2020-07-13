package com.nicehancy.mix.service.api.model.request;

import com.nicehancy.mix.service.api.model.base.BaseReqDTO;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import net.sf.oval.constraint.NotBlank;
import net.sf.oval.constraint.NotNull;
import java.io.Serializable;

/**
 * 反馈提交
 * <p>
 * <p/>
 *
 * @author hancaiyun
 * @since 2020/7/13 16:49
 **/
@Getter
@Setter
@ToString(callSuper = true)
public class SuggestCommitReqDTO extends BaseReqDTO implements Serializable {

    /**
     * 序列化ID
     */
    private static final long serialVersionUID = -5262863862755777793L;

    /**
     * 用户编号
     */
    @NotNull(message = "用户编号不允许为空")
    @NotBlank(message = "用户编号不允许为空")
    private String userNo;

    /**
     * 反馈信息
     */
    @NotNull(message = "反馈信息不允许为空")
    @NotBlank(message = "反馈信息不允许为空")
    private String suggestion;
}
