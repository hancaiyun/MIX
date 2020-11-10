package com.nicehancy.mix.service.note;

import com.nicehancy.mix.common.Result;
import com.nicehancy.mix.common.utils.VerifyUtil;
import com.nicehancy.mix.manager.note.CommentInfoManager;
import com.nicehancy.mix.service.api.model.request.note.CommentCommitReqDTO;
import com.nicehancy.mix.service.api.note.CommentInfoService;
import com.nicehancy.mix.service.convert.CommentInfoDTOConvert;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.MDC;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * <p>
 *     评论管理接口
 *     1、评论提交
 *     2、评论分页查询
 * <p/>
 *
 * @author hancaiyun
 * @since 2020/11/5 16:13
 **/
@Slf4j
@Service
public class CommentInfoServiceImpl implements CommentInfoService {

    @Autowired
    private CommentInfoManager commentInfoManager;

    @Override
    public Result<Boolean> commentCommit(CommentCommitReqDTO reqDTO) {

        Result<Boolean> result = new Result<>();
        MDC.put("TRACE_LOG_ID", reqDTO.getTraceLogId());
        try{
            log.info("call CommentInfoServiceImpl commentCommit param: reqDTO={}", reqDTO);
            //参数校验
            VerifyUtil.validateObject(reqDTO);
            //业务处理
            commentInfoManager.commentCommit(CommentInfoDTOConvert.getBOByDTO(reqDTO));
            result.setResult(true);
            log.info("call CommentInfoServiceImpl commentCommit result: {}", result);
        }catch (Exception e){
            result.setErrorMsg(e.getMessage());
            log.error("call CommentInfoServiceImpl commentCommit failed, message：e={}， result={}", e, result);
        }
        return result;
    }
}
