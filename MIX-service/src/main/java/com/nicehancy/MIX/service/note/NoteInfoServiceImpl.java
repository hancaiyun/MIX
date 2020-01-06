package com.nicehancy.MIX.service.note;

import com.alibaba.dubbo.config.annotation.Service;
import com.nicehancy.MIX.common.Result;
import com.nicehancy.MIX.manager.note.NoteInfoManager;
import com.nicehancy.MIX.service.api.model.NoteInfoDTO;
import com.nicehancy.MIX.service.api.model.request.note.DirectoryQueryReqDTO;
import com.nicehancy.MIX.service.api.model.request.note.NoteManageReqDTO;
import com.nicehancy.MIX.service.api.model.request.note.NoteQueryReqDTO;
import com.nicehancy.MIX.service.api.model.request.note.NoteSaveReqDTO;
import com.nicehancy.MIX.service.api.note.NoteInfoService;
import com.nicehancy.MIX.service.convert.note.NoteDTOConvert;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.MDC;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
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
@Service(timeout = 3000)
@Component("noteInfoServiceImpl")
public class NoteInfoServiceImpl implements NoteInfoService {

    @Autowired
    private NoteInfoManager noteInfoManager;

    /**
     * 目录列表查询接口
     * 可查询一级目录列表或者二级目录列表或一级目录下文件列表
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
     * 笔记查询
     * @param reqDTO        请求对象
     * @return              查询结果
     */
    //@Override
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
    public Result<Boolean> manage(NoteManageReqDTO reqDTO) {

        Result<Boolean> result = new Result<>();
        MDC.put("TRACE_LOG_ID", reqDTO.getTraceLogId());
        try{
            log.info("call NoteInfoServiceImpl manage param: reqDTO={}", reqDTO);
            //业务处理
            boolean isDone = noteInfoManager.manage(NoteDTOConvert.getReqBOByDTO(reqDTO));
            result.setResult(isDone);
            log.info("call NoteInfoServiceImpl manage result: {}", result);
        }catch (Exception e){
            result.setErrorMsg(e.getMessage());
            log.error("call NoteInfoServiceImpl manage failed, message：e={}, result={}", e, result);
        }
        return result;
    }
}