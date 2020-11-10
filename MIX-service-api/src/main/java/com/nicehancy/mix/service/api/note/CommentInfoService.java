package com.nicehancy.mix.service.api.note;

import com.nicehancy.mix.common.Result;
import com.nicehancy.mix.service.api.model.request.note.CommentCommitReqDTO;

import java.util.List;

/**
 * 文档评论接口
 * <p>
 * <p/>
 *
 * @author hancaiyun
 * @since 2020/11/5 11:24
 **/
public interface CommentInfoService {

    /**
     * 评论提交接口
     * @param  reqDTO   请求DTO
     * @return          提交结果
     */
    Result<Boolean> commentCommit(CommentCommitReqDTO reqDTO);

    /**
     * 评论分页查询
     * @param reqDTO    请求参数DTO
     * @return          分页查询结果
     */
    //Result<List<>> pageQuery(CommentInfoPageQueryReqDTO reqDTO);

    /*
      评论列表分页查询
     */

}
