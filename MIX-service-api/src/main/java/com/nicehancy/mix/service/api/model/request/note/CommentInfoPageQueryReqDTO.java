package com.nicehancy.mix.service.api.model.request.note;

import com.nicehancy.mix.service.api.model.base.BasePageDTO;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * <p>
 *     评论分页查询接口
 * <p/>
 *
 * @author hancaiyun
 * @since 2020/11/10 14:35
 **/
@Getter
@Setter
@ToString(callSuper = true)
public class CommentInfoPageQueryReqDTO extends BasePageDTO {

    /**
     * 序列化ID
     */
    private static final long serialVersionUID = -6677246439132994112L;

    /**
     * 主体id
     */
    private Long subjectId;

    /**
     * 主体类型
     */
    private String subjectType;
}
