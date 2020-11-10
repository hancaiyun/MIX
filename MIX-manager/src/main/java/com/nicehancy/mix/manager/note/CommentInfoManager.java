package com.nicehancy.mix.manager.note;

import com.nicehancy.mix.common.constant.UtilConstant;
import com.nicehancy.mix.common.enums.SubjectTypeEnum;
import com.nicehancy.mix.common.utils.SensitiveWordFilterUtil;
import com.nicehancy.mix.dal.CommentInfoRepository;
import com.nicehancy.mix.dal.NoteInfoRepository;
import com.nicehancy.mix.dal.UserInfoRepository;
import com.nicehancy.mix.dal.model.CommentInfoDO;
import com.nicehancy.mix.dal.model.NoteInfoDO;
import com.nicehancy.mix.dal.model.UserInfoDO;
import com.nicehancy.mix.dal.model.request.CommentInfoPageQueryReqDO;
import com.nicehancy.mix.manager.convert.CommentInfoBOConvert;
import com.nicehancy.mix.manager.model.CommentCommitReqBO;
import com.nicehancy.mix.manager.model.CommentInfoBO;
import com.nicehancy.mix.manager.model.CommentInfoPageQueryReqBO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * <p>
 *     笔记评论管理manager
 * <p/>
 *
 * @author hancaiyun
 * @since 2020/11/6 10:44
 **/
@Component
public class CommentInfoManager {

    @Autowired
    private CommentInfoRepository noteCommentInfoRepository;

    @Autowired
    private NoteInfoRepository noteInfoRepository;

    @Autowired
    private UserInfoRepository userInfoRepository;

    /**
     * 评论提交
     * @param reqBO     请求BO
     */
    public void commentCommit(CommentCommitReqBO reqBO) {

        //评论内容敏感词校验
        boolean isContainSensitive = SensitiveWordFilterUtil.isContainSensitiveWord(reqBO.getContent(),
                UtilConstant.MIN_MATCH_TYPE);
        if(isContainSensitive){
            throw new RuntimeException("评论内容包含敏感词，请修改后重新提交");
        }

        if(SubjectTypeEnum.NOTE.getCode().equals(reqBO.getSubjectType())){
            //查询笔记信息
            NoteInfoDO noteInfoDO = noteInfoRepository.queryNoteByFileId(reqBO.getSubjectId());
            if(null == noteInfoDO){
                throw new RuntimeException("共享文档已被删除或不存在");
            }
            //查询用户信息
            UserInfoDO userInfoDO = userInfoRepository.queryByUserNo(reqBO.getUserNo());
            if(null == userInfoDO){
                throw new RuntimeException("用户信息异常");
            }
            //新增笔记评论
            noteCommentInfoRepository.insert(getCommentDO(reqBO, noteInfoDO, userInfoDO));
        }else{
            //TODO 待扩展
            throw new RuntimeException("暂不支持的评论类型");
        }
    }

    /**
     * 评论DO参数构建
     * @param reqBO             请求BO
     * @param noteInfoDO        笔记DO
     * @param userInfoDO        用户信息DO
     * @return                  评论DO
     */
    private CommentInfoDO getCommentDO(CommentCommitReqBO reqBO, NoteInfoDO noteInfoDO, UserInfoDO userInfoDO) {

        CommentInfoDO commentInfoDO = new CommentInfoDO();

        commentInfoDO.setSubjectId(reqBO.getSubjectId());
        commentInfoDO.setSubjectType(reqBO.getSubjectType());
        commentInfoDO.setUserNo(noteInfoDO.getUserNo());
        commentInfoDO.setNickName(userInfoDO.getNickName());
        commentInfoDO.setHeadCopy(userInfoDO.getHeadCopy());
        commentInfoDO.setContent(reqBO.getContent());
        commentInfoDO.setCreatedBy(reqBO.getUserNo());
        commentInfoDO.setUpdatedBy(reqBO.getUserNo());

        return commentInfoDO;
    }

    /**
     * 评论总总数查询
     * @param reqBO      BO
     * @return           条目数
     */
    public int queryCount(CommentInfoPageQueryReqBO reqBO) {

        return noteCommentInfoRepository.queryCount(CommentInfoBOConvert.getDOByBO(reqBO));
    }

    /**
     * 评论列表查询
     * @param reqBO         BO
     * @return              评论列表
     */
    public List<CommentInfoBO> pageQuery(CommentInfoPageQueryReqBO reqBO) {

        return CommentInfoBOConvert.getBOSByDOS(noteCommentInfoRepository.queryByPage(
                CommentInfoBOConvert.getDOByBO(reqBO)));
    }
}
