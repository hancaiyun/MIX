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
     * @param reqDO             请求参数
     * @return                  文档信息
     */
    List<NoteInfoDO> queryNoteInfo(NoteQueryReqDO reqDO);

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
}
