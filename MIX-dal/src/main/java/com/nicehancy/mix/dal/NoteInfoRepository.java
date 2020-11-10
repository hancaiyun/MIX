package com.nicehancy.mix.dal;


import com.nicehancy.mix.dal.model.request.DirectoryQueryReqDO;
import com.nicehancy.mix.dal.model.request.FileListReqDO;
import com.nicehancy.mix.dal.model.NoteInfoDO;
import com.nicehancy.mix.dal.model.request.NoteQueryReqDO;

import java.util.List;

/**
 * 笔记数据库操作接口
 * <p>
 * <p/>
 *
 * @author hancaiyun
 * @since 2019/10/17 11:05
 **/
public interface NoteInfoRepository {

    /**
     *  目录列表查询
     * @param reqDO                  请求DO
     * @return                       一级目录列表或者二级目录列表
     */
    List<NoteInfoDO> queryDirectory(DirectoryQueryReqDO reqDO);

    /**
     * 文档列表查询
     * @param reqDO                  请求DO
     * @return                       文档列表
     */
    List<NoteInfoDO> queryFileList(FileListReqDO reqDO);

    /**
     * 查询文档
     * @param reqDO                 请求参数
     * @return                      文档信息
     */
    List<NoteInfoDO> queryNoteInfo(NoteQueryReqDO reqDO);

    /**
     * 根据文件id查询文件
     * @param fileId                文件id
     * @return                      文件信息
     */
    NoteInfoDO queryNoteByFileId(Long fileId);

    /**
     * 保存笔记
     * @param noteInfoDO             笔记DO
     */
    void saveNote(NoteInfoDO noteInfoDO);

    /**
     * 更新文档
     * @param noteInfoDO             文档
     */
    void updateContent(NoteInfoDO noteInfoDO);

    /**
     * 更新目录
     * @param noteInfoDO             DO
     * @param opLocation             更新目录位置
     * @param opName                 更新值
     */
    void updateDirectory(NoteInfoDO noteInfoDO, String opLocation, String opName);

    /**
     * 删除更新
     * @param reqDO                  请求DO
     * @param opLocation             删除位置
     */
    void updateForDelete(NoteInfoDO reqDO, String opLocation);

    /**
     * 分享更新
     * @param noteInfoDO             请求DO
     */
    void updateForShare(NoteInfoDO noteInfoDO);

    /**
     * 查询分享列表总数
     * @return                       总条数
     */
    int queryShareCount();

    /**
     * 分享文档列表查询
     * @return                       共享文档列表
     */
    List<NoteInfoDO> queryShareNote(int current, int limit);

    /**
     *  根据id查询文档明细
     * @param id                      数据库主键id
     * @return                        文档明细
     */
    NoteInfoDO queryNoteById(Long id);
}
