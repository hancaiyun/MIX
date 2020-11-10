package com.nicehancy.mix.service.api.model.result;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;

/**
 * <p>
 *     评论明细DTO
 * <p/>
 *
 * @author hancaiyun
 * @since 2020/11/6 17:05
 **/
@Getter
@Setter
@ToString
public class CommentInfoDTO implements Serializable {

    /**
     * 序列化ID
     */
    private static final long serialVersionUID = 8584346218025045101L;

    private Long id;

    private Long subjectId;

    private String subjectType;

    private String userNo;

    private String nickName;

    private String headCopy;

//    private String ;
//
//    private String ;
}
