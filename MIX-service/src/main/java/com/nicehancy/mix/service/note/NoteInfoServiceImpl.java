package com.nicehancy.mix.service.note;

import com.nicehancy.mix.common.Result;
import com.nicehancy.mix.common.utils.VerifyUtil;
import com.nicehancy.mix.manager.model.NoteShareDetailBO;
import com.nicehancy.mix.manager.model.NoteShareInfoBO;
import com.nicehancy.mix.manager.note.NoteInfoManager;
import com.nicehancy.mix.service.api.model.NoteInfoDTO;
import com.nicehancy.mix.service.api.model.request.note.*;
import com.nicehancy.mix.service.api.model.result.NoteShareDetailDTO;
import com.nicehancy.mix.service.api.model.result.NoteShareInfoDTO;
import com.nicehancy.mix.service.api.model.result.base.BasePageQueryResDTO;
import com.nicehancy.mix.service.api.note.NoteInfoService;
import com.nicehancy.mix.service.convert.note.NoteDTOConvert;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.MDC;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import java.util.List;

/**
 * 笔记操作接口
 * <p>
 * <p/>
 *
 * @author hancaiyun
 * @since 2019/10/16 15:19
 **/
@Slf4j
@Service
public class NoteInfoServiceImpl implements NoteInfoService {

    @Autowired
    private NoteInfoManager noteInfoManager;

    /**
     * 目录列表查询接口
     * 可查询一级目录列表或者二级目录列表
     * 注：仅能查询单级目录列表
     * @param reqDTO        请求参数
     * @return              返回结果
     */
    @Override
    public Result<List<String>> queryDirectory(DirectoryQueryReqDTO reqDTO) {

        Result<List<String>> result = new Result<>();
        MDC.put("TRACE_LOG_ID", reqDTO.getTraceLogId());
        try{
            log.info("call NoteInfoServiceImpl queryDirectory param: reqDTO={}", reqDTO);
            //业务处理
            List<String> list= noteInfoManager.queryDirectory(NoteDTOConvert.getBOByDTO(reqDTO));
            result.setResult(list);
            log.info("call NoteInfoServiceImpl queryDirectory result: {}", result);
        }catch (Exception e){
            result.setErrorMsg(e.getMessage());
            log.error("call NoteInfoServiceImpl queryDirectory failed, message：e={}， result={}", e, result);
        }
        return result;
    }

    /**
     * 查询一级目录下文件列表
     * @return               文件列表
     */
    @Override
    public Result<List<String>> queryFileList(FileListReqDTO reqDTO) {

        Result<List<String>> result = new Result<>();
        MDC.put("TRACE_LOG_ID", reqDTO.getTraceLogId());
        try{
            log.info("call NoteInfoServiceImpl queryFileList param: reqDTO={}", reqDTO);
            //业务处理
            List<String> list= noteInfoManager.queryFileList(NoteDTOConvert.getBOByDTO(reqDTO));
            result.setResult(list);
            log.info("call NoteInfoServiceImpl queryFileList result: {}", result);
        }catch (Exception e){
            result.setErrorMsg(e.getMessage());
            log.error("call NoteInfoServiceImpl queryFileList failed, message：e={}， result={}", e, result);
        }
        return result;
    }

    /**
     * 笔记查询
     * @param reqDTO        请求对象
     * @return              查询结果
     */
    @Override
    public Result<List<NoteInfoDTO>> queryNoteInfo(NoteQueryReqDTO reqDTO) {

        Result<List<NoteInfoDTO>> result = new Result<>();
        MDC.put("TRACE_LOG_ID", reqDTO.getTraceLogId());
        try{
            log.info("call NoteInfoServiceImpl queryNoteInfo param: reqDTO={}", reqDTO);
            //业务处理
            List<NoteInfoDTO> noteInfoDTOS = NoteDTOConvert.getDTOsByBOs(noteInfoManager.queryNoteInfo(
                    NoteDTOConvert.getReqBOByDTO(reqDTO)));
            if(!CollectionUtils.isEmpty(noteInfoDTOS)){
                result.setResult(noteInfoDTOS);
            }else{
                result.setResult(null);
            }
            log.info("call NoteInfoServiceImpl queryNoteInfo result: {}", result);
        }catch (Exception e){
            result.setErrorMsg(e.getMessage());
            log.error("call NoteInfoServiceImpl queryNoteInfo failed, message：e={}， result={}", e, result);
        }
        return result;
    }

    /**
     * 笔记保存
     * @param reqDTO                请求参数
     * @return                      保存结果
     */
    @Override
    public Result<Boolean> save(NoteSaveReqDTO reqDTO) {

        Result<Boolean> result = new Result<>();
        MDC.put("TRACE_LOG_ID", reqDTO.getTraceLogId());
        try{
            log.info("call NoteInfoServiceImpl save param: reqDTO={}", reqDTO);
            //业务处理
            boolean isDone = noteInfoManager.saveNote(NoteDTOConvert.getReqBOByDTO(reqDTO));
            result.setResult(isDone);
            log.info("call NoteInfoServiceImpl save result: {}", result);
        }catch (Exception e){
            result.setErrorMsg(e.getMessage());
            log.error("call NoteInfoServiceImpl save failed, message：e={}, result={}", e, result);
        }
        return result;
    }

    /**
     * 笔记管理接口
     * @param reqDTO                请求参数
     * @return                      返回结果
     */
    @Override
    public Result<Boolean> manage(NoteManageReqDTO reqDTO) {

        Result<Boolean> result = new Result<>();
        MDC.put("TRACE_LOG_ID", reqDTO.getTraceLogId());
        try{
            log.info("call NoteInfoServiceImpl manage param: reqDTO={}", reqDTO);
            //参数校验
            VerifyUtil.validateObject(reqDTO);
            //业务处理
            result = noteInfoManager.manage(NoteDTOConvert.getReqBOByDTO(reqDTO));
        }catch (Exception e){
            result.setErrorMsg(e.getMessage());
            log.error("call NoteInfoServiceImpl manage failed, message：e={}, result={}", e, result);
        }
        log.info("call NoteInfoServiceImpl manage result: {}", result);
        return result;
    }

    /**
     * 笔记删除接口
     * @param reqDTO                请求参数
     * @return                      返回结果
     */
    @Override
    public Result<Boolean> delete(NoteDeleteReqDTO reqDTO) {

        Result<Boolean> result = new Result<>();
        MDC.put("TRACE_LOG_ID", reqDTO.getTraceLogId());
        try{
            log.info("call NoteInfoServiceImpl delete param: reqDTO={}", reqDTO);
            //业务处理
            result = noteInfoManager.delete(NoteDTOConvert.getReqBOByDTO(reqDTO));
            log.info("call NoteInfoServiceImpl delete result: {}", result);
        }catch (Exception e){
            result.setErrorMsg(e.getMessage());
            log.error("call NoteInfoServiceImpl delete failed, message：e={}, result={}", e, result);
        }
        return result;
    }

    /**
     * 笔记分享接口
     * @param reqDTO                请求参数
     * @return                      分享结果
     */
    @Override
    public Result<Boolean> share(NoteShareReqDTO reqDTO) {

        Result<Boolean> result = new Result<>();
        MDC.put("TRACE_LOG_ID", reqDTO.getTraceLogId());
        try{
            log.info("call NoteInfoServiceImpl share param: reqDTO={}", reqDTO);
            //参数校验
            VerifyUtil.validateObject(reqDTO);
            //业务处理
            boolean isDone = noteInfoManager.share(NoteDTOConvert.getReqBOByDTO(reqDTO));
            result.setResult(isDone);
            log.info("call NoteInfoServiceImpl share result: {}", result);
        }catch (Exception e){
            result.setErrorMsg(e.getMessage());
            log.error("call NoteInfoServiceImpl share failed, message：e={}, result={}", e, result);
        }
        return result;
    }

    /**
     * 共享文档列表查询
     * @param reqDTO                请求参数
     * @return                      共享文档列表
     */
    @Override
    public Result<BasePageQueryResDTO<NoteShareInfoDTO>> queryShare(NoteShareQueryReqDTO reqDTO) {

        Result<BasePageQueryResDTO<NoteShareInfoDTO>> result = new Result<>();
        BasePageQueryResDTO<NoteShareInfoDTO> pageQueryResDTO = new BasePageQueryResDTO<>();
        MDC.put("TRACE_LOG_ID", reqDTO.getTraceLogId());
        try{
            log.info("call NoteInfoServiceImpl queryShare param: reqDTO={}", reqDTO);
            //业务处理
            //查询总条数
            int totalCount = noteInfoManager.queryShareCount();
            if(totalCount < 1){
                pageQueryResDTO.setCount(0);
                result.setResult(pageQueryResDTO);
            }else{
                //查询结果集
                List<NoteShareInfoBO> shareInfoBOList= noteInfoManager.queryShare(reqDTO.getCurrentPage(), reqDTO.
                        getPageSize());
                pageQueryResDTO.setCount(totalCount);
                pageQueryResDTO.setPageResult(NoteDTOConvert.getShareDTOsByBOs(shareInfoBOList));
            }

            //设置返回结果
            result.setResult(pageQueryResDTO);
            log.info("call NoteInfoServiceImpl queryShare result: {}", result);
        }catch (Exception e){
            result.setErrorMsg(e.getMessage());
            log.error("call NoteInfoServiceImpl queryShare failed, message：e={}, result={}", e, result);
        }
        return result;
    }

    /**
     * 共享明细查询接口
     * @param reqDTO                请求参数
     * @return                      明细结果
     */
    @Override
    public Result<NoteShareDetailDTO> queryShareDetail(NoteShareQueryDetailReqDTO reqDTO) {

        Result<NoteShareDetailDTO> result = new Result<>();
        MDC.put("TRACE_LOG_ID", reqDTO.getTraceLogId());
        try{
            log.info("call NoteInfoServiceImpl queryShareDetail param: reqDTO={}", reqDTO);
            //业务处理
            NoteShareDetailBO noteShareDetailBO= noteInfoManager.queryShareDetail(reqDTO.getId());
            result.setResult(NoteDTOConvert.getDTOByBO(noteShareDetailBO));
            log.info("call NoteInfoServiceImpl queryShareDetail result: {}", result);
        }catch (Exception e){
            result.setErrorMsg(e.getMessage());
            log.error("call NoteInfoServiceImpl queryShareDetail failed, message：e={}, result={}", e, result);
        }
        return result;
    }
}