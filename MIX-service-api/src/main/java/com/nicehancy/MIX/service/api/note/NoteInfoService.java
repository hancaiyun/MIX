package com.nicehancy.MIX.service.api.note;

import com.nicehancy.MIX.common.Result;
import com.nicehancy.MIX.service.api.model.NoteInfoDTO;
import com.nicehancy.MIX.service.api.model.request.note.DirectoryQueryReqDTO;
import com.nicehancy.MIX.service.api.model.request.note.NoteManageReqDTO;
import com.nicehancy.MIX.service.api.model.request.note.NoteQueryReqDTO;
import com.nicehancy.MIX.service.api.model.request.note.NoteSaveReqDTO;

import java.util.List;

/**
 * 笔记管理接口
 * <p>
 * <p/>
 *
 * @author hancaiyun
 * @since 2019/10/16 15:19
 **/
public interface NoteInfoService {


    /**
     * 目录查询
     * @param reqDTO
     * @return
     */
    Result<List<String>> queryDirectory(DirectoryQueryReqDTO reqDTO);

    /**
     * 笔记查询
     * @param reqDTO        请求对象
     * @return              查询结果
     */
    Result<List<NoteInfoDTO>> queryNoteInfo(NoteQueryReqDTO reqDTO);

    /**
     * 笔记保存
     * @param reqDTO                请求参数
     * @return                      保存结果
     */
    Result<Boolean> save(NoteSaveReqDTO reqDTO);

    /**
     * 笔记管理接口
     * @param reqDTO                请求参数
     * @return                      返回结果
     */
    Result<Boolean> manage(NoteManageReqDTO reqDTO);
}