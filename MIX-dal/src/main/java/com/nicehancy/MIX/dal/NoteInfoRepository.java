package com.nicehancy.MIX.dal;


import com.nicehancy.MIX.dal.model.DirectoryQueryReqDO;
import com.nicehancy.MIX.dal.model.NoteInfoDO;
import com.nicehancy.MIX.dal.model.NoteQueryReqDO;

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
     * @return                       一级目录列表或者二级目录列表或者一级目录下笔记列表
     */
    List<NoteInfoDO> queryDirectory(DirectoryQueryReqDO reqDO);

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
