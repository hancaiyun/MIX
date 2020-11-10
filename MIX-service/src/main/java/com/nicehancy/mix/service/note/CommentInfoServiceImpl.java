package com.nicehancy.mix.service.note;

import com.nicehancy.mix.common.Result;
import com.nicehancy.mix.common.utils.VerifyUtil;
import com.nicehancy.mix.manager.model.CommentInfoBO;
import com.nicehancy.mix.manager.note.CommentInfoManager;
import com.nicehancy.mix.service.api.model.request.note.CommentCommitReqDTO;
import com.nicehancy.mix.service.api.model.request.note.CommentInfoPageQueryReqDTO;
import com.nicehancy.mix.service.api.model.result.CommentInfoDTO;
import com.nicehancy.mix.service.api.model.result.base.BasePageQueryResDTO;
import com.nicehancy.mix.service.api.note.CommentInfoService;
import com.nicehancy.mix.service.convert.CommentInfoDTOConvert;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.MDC;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

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

    /**
     * 评论分页查询
     * @param reqDTO    请求参数DTO
     * @return          评论列表
     */
    @Override
    public Result<BasePageQueryResDTO<CommentInfoDTO>> pageQuery(CommentInfoPageQueryReqDTO reqDTO) {

        Result<BasePageQueryResDTO<CommentInfoDTO>> result = new Result<>();
        MDC.put("TRACE_LOG_ID", reqDTO.getTraceLogId());
        try{
            log.info("call CommentInfoServiceImpl pageQuery param: reqDTO={}", reqDTO);

            //参数校验
            VerifyUtil.validateObject(reqDTO);

            //业务处理
            BasePageQueryResDTO<CommentInfoDTO> resDTO= new BasePageQueryResDTO<>();

            //查询总条数
            int count = commentInfoManager.queryCount(CommentInfoDTOConvert.getBOByDTO(reqDTO));

            //查询结果集
            if(0 == count) {
                resDTO.setPageResult(null);
                resDTO.setCount(0);
            }else{
                List<CommentInfoBO> list = commentInfoManager.pageQuery(CommentInfoDTOConvert.getBOByDTO(reqDTO));
                List<CommentInfoDTO> dtoList = CommentInfoDTOConvert.getDTOsByBOs(list);
                resDTO.setPageResult(dtoList);
                resDTO.setCount(count);
            }
            result.setResult(resDTO);
        }catch (Exception e){
            result.setErrorMsg(e.getMessage());
            log.error("call CommentInfoServiceImpl pageQuery failed, message：e={}， result={}", e, result);
        }
        log.info("call CommentInfoServiceImpl pageQuery result: {}", result);
        return result;
    }
}
