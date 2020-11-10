package com.nicehancy.mix.service.convert;

import com.nicehancy.mix.common.constant.DatePatternConstant;
import com.nicehancy.mix.common.utils.DateUtil;
import com.nicehancy.mix.manager.model.CommentCommitReqBO;
import com.nicehancy.mix.manager.model.CommentInfoBO;
import com.nicehancy.mix.manager.model.CommentInfoPageQueryReqBO;
import com.nicehancy.mix.service.api.model.request.note.CommentCommitReqDTO;
import com.nicehancy.mix.service.api.model.request.note.CommentInfoPageQueryReqDTO;
import com.nicehancy.mix.service.api.model.result.CommentInfoDTO;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.List;

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

    /**
     * 评论分页查询DTO转BO
     * @param reqDTO        请求DTO
     * @return              BO
     */
    public static CommentInfoPageQueryReqBO getBOByDTO(CommentInfoPageQueryReqDTO reqDTO) {

        if (reqDTO == null) {
            return null;
        }

        CommentInfoPageQueryReqBO commentInfoPageQueryReqBO = new CommentInfoPageQueryReqBO();
        commentInfoPageQueryReqBO.setSubjectId(reqDTO.getSubjectId());
        commentInfoPageQueryReqBO.setSubjectType(reqDTO.getSubjectType());
        commentInfoPageQueryReqBO.setCurrentPage(reqDTO.getCurrentPage());
        commentInfoPageQueryReqBO.setPageSize(reqDTO.getPageSize());

        return commentInfoPageQueryReqBO;
    }

    /**
     * 根据BO获取DTO
     * @param commentInfoBO     BO
     * @return                  DTO
     */
    public static CommentInfoDTO getDTOByBO(CommentInfoBO commentInfoBO) {

        if (commentInfoBO == null) {
            return null;
        }

        CommentInfoDTO commentInfoDTO = new CommentInfoDTO();
        commentInfoDTO.setSubjectId(commentInfoBO.getSubjectId());
        commentInfoDTO.setSubjectType(commentInfoBO.getSubjectType());
        commentInfoDTO.setUserNo(commentInfoBO.getUserNo());
        commentInfoDTO.setNickName(commentInfoBO.getNickName());
        commentInfoDTO.setHeadCopy(commentInfoBO.getHeadCopy());
        commentInfoDTO.setContent(commentInfoBO.getContent());
        commentInfoDTO.setCommentStatus(commentInfoBO.getCommentStatus());
        commentInfoDTO.setUpdatedAt(DateUtil.format(commentInfoBO.getUpdatedAt(), DatePatternConstant.STANDARD_PATTERN));
        commentInfoDTO.setId(commentInfoBO.getId());

        return commentInfoDTO;
    }

    /**
     * BO list 转 DTO list
     * @param list          BO list
     * @return              DTO list
     */
    public static List<CommentInfoDTO> getDTOsByBOs(List<CommentInfoBO> list) {

        if(CollectionUtils.isEmpty(list)){
            return null;
        }
        List<CommentInfoDTO> commentInfoDTOList = new ArrayList<>();
        for(CommentInfoBO commentInfoBO : list){
            commentInfoDTOList.add(getDTOByBO(commentInfoBO));
        }
        return commentInfoDTOList;
    }
}
