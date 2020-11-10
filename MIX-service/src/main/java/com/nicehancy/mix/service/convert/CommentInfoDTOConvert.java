package com.nicehancy.mix.service.convert;

import com.nicehancy.mix.manager.model.CommentCommitReqBO;
import com.nicehancy.mix.service.api.model.request.note.CommentCommitReqDTO;

/**
 * <p>
 *     评论数据模型转换类
 * <p/>
 *
 * @author hancaiyun
 * @since 2020/11/6 14:56
 **/
public class CommentInfoDTOConvert {

    private CommentInfoDTOConvert(){
    }

    /**
     * 评论提交DTO转BO
     * @param reqDTO                请求DTO
     * @return                      请求BO
     */
    public static CommentCommitReqBO getBOByDTO(CommentCommitReqDTO reqDTO) {

        if (reqDTO == null) {
            return null;
        }

        CommentCommitReqBO commentCommitReqBO = new CommentCommitReqBO();
        commentCommitReqBO.setUserNo(reqDTO.getUserNo());
        commentCommitReqBO.setSubjectId(reqDTO.getSubjectId());
        commentCommitReqBO.setSubjectType(reqDTO.getSubjectType());
        commentCommitReqBO.setContent(reqDTO.getContent());

        return commentCommitReqBO;
    }
}
