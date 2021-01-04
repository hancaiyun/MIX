package com.nicehancy.mix.service.api.note;

import com.nicehancy.mix.common.Result;
import com.nicehancy.mix.service.api.model.NoteInfoDTO;
import com.nicehancy.mix.service.api.model.request.note.*;
import com.nicehancy.mix.service.api.model.result.NoteShareDetailDTO;
import com.nicehancy.mix.service.api.model.result.NoteShareInfoDTO;
import com.nicehancy.mix.service.api.model.result.base.BasePageQueryResDTO;

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
     * @param reqDTO                请求参数
     * @return                      目录列表
     */
    Result<List<String>> queryDirectory(DirectoryQueryReqDTO reqDTO);

    /**
     * 文档列表查询
     * @param reqDTO                请求参数DTO
     * @return                      文档列表
     */
    Result<List<String>> queryFileList(FileListReqDTO reqDTO);

    /**
     * 笔记查询
     * @param reqDTO                请求对象
     * @return                      查询结果
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

    /**
     * 笔记删除接口
     * @param reqDTO                请求参数
     * @return                      返回结果
     */
    Result<Boolean> delete(NoteDeleteReqDTO reqDTO);

    /**
     * 笔记共享
     * @param reqDTO                请求参数
     * @return                      返回结果
     */
    Result<Boolean> share(NoteShareReqDTO reqDTO);

    /**
     * 共享笔记查询
     * @param reqDTO                请求参数
     * @return                      返回结果
     */
    Result<BasePageQueryResDTO<NoteShareInfoDTO>> queryShare(NoteShareQueryReqDTO reqDTO);

    /**
     * 共享笔记明细查询
     * @param reqDTO                请求参数
     * @return                      返回结果
     */
    Result<NoteShareDetailDTO> queryShareDetail(NoteShareQueryDetailReqDTO reqDTO);
}