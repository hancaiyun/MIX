package com.nicehancy.mix.manager.convert;

import com.nicehancy.mix.dal.model.CommentInfoDO;
import com.nicehancy.mix.dal.model.request.CommentInfoPageQueryReqDO;
import com.nicehancy.mix.manager.model.CommentInfoBO;
import com.nicehancy.mix.manager.model.CommentInfoPageQueryReqBO;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 *    评论BO转换类
 * <p/>
 *
 * @author hancaiyun
 * @since 2020/11/10 15:17
 **/
public class CommentInfoBOConvert {

    /**
     * 私有构造函数
     */
    private CommentInfoBOConvert(){
    }

    /**
     * 分页查询请求BO转DO
     * @param reqBO         请求BO
     * @return              请求DO
     */
    public static CommentInfoPageQueryReqDO getDOByBO(CommentInfoPageQueryReqBO reqBO) {

        if (reqBO == null) {
            return null;
        }

        CommentInfoPageQueryReqDO commentInfoPageQueryReqDO = new CommentInfoPageQueryReqDO();
        commentInfoPageQueryReqDO.setSubjectId(reqBO.getSubjectId());
        commentInfoPageQueryReqDO.setSubjectType(reqBO.getSubjectType());
        commentInfoPageQueryReqDO.setCurrentPage(reqBO.getCurrentPage());
        commentInfoPageQueryReqDO.setPageSize(reqBO.getPageSize());

        return commentInfoPageQueryReqDO;
    }

    /**
     * 评论DO转BO
     * @param commentInfoDO     DO
     * @return                  BO
     */
    public static CommentInfoBO getBOByDO(CommentInfoDO commentInfoDO) {

        if (commentInfoDO == null) {
            return null;
        }

        CommentInfoBO commentInfoBO = new CommentInfoBO();
        commentInfoBO.setSubjectId(commentInfoDO.getSubjectId());
        commentInfoBO.setSubjectType(commentInfoDO.getSubjectType());
        commentInfoBO.setUserNo(commentInfoDO.getUserNo());
        commentInfoBO.setNickName(commentInfoDO.getNickName());
        commentInfoBO.setHeadCopy(commentInfoDO.getHeadCopy());
        commentInfoBO.setContent(commentInfoDO.getContent());
        commentInfoBO.setCommentStatus(commentInfoDO.getCommentStatus());
        commentInfoBO.setId(commentInfoDO.getId());
        commentInfoBO.setCreatedAt(commentInfoDO.getCreatedAt());
        commentInfoBO.setCreatedBy(commentInfoDO.getCreatedBy());
        commentInfoBO.setUpdatedAt(commentInfoDO.getUpdatedAt());
        commentInfoBO.setUpdatedBy(commentInfoDO.getUpdatedBy());

        return commentInfoBO;
    }

    /**
     * 评论DO列表转BO列表
     * @param commentInfoDOList DO列表
     * @return                  BO列表
     */
    public static List<CommentInfoBO> getBOSByDOS(List<CommentInfoDO> commentInfoDOList) {

        if(CollectionUtils.isEmpty(commentInfoDOList)){
            return null;
        }
        List<CommentInfoBO> commentInfoBOList = new ArrayList<>();
        for(CommentInfoDO commentInfoDO : commentInfoDOList){
            commentInfoBOList.add(getBOByDO(commentInfoDO));
        }
        return commentInfoBOList;
    }
}
