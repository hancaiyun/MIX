package com.nicehancy.mix.service.api.model.request.note;

import com.nicehancy.mix.service.api.model.base.BaseReqDTO;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import net.sf.oval.constraint.NotBlank;
import net.sf.oval.constraint.NotNull;

/**
 * <p>
 * <p/>
 *
 * @author hancaiyun
 * @since 2020/7/10 16:09
 **/
@Getter
@Setter
@ToString(callSuper = true)
public class AccountQueryReqDTO extends BaseReqDTO {

    /**
     * 序列化ID
     */
    private static final long serialVersionUID = -1592310229206061761L;

    /**
     * 用户编号
     */
    @NotNull(message = "用户编号不允许为空")
    @NotBlank(message = "用户编号不允许为空")
    private String userNo;

    /**
     * 地址
     */
    private String address;
}
