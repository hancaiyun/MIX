package com.nicehancy.MIX.manager.note;

import com.nicehancy.MIX.common.enums.NoteStatusEnum;
import com.nicehancy.MIX.dal.NoteInfoRepository;
import com.nicehancy.MIX.dal.model.NoteInfoDO;
import com.nicehancy.MIX.dal.model.NoteQueryReqDO;
import com.nicehancy.MIX.manager.convert.NoteInfoBOConvert;
import com.nicehancy.MIX.manager.model.NoteInfoBO;
import com.nicehancy.MIX.manager.model.NoteManageReqBO;
import com.nicehancy.MIX.manager.model.NoteQueryReqBO;
import com.nicehancy.MIX.manager.model.NoteSaveReqBO;
import io.netty.util.internal.StringUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * 笔记数据库接口实现类
 * <p>
 * <p/>
 *
 * @author hancaiyun
 * @since 2019/10/17 10:58
 **/
@Component
public class NoteInfoManager {

    @Autowired
    private NoteInfoRepository noteRepository;

    /**
     * 目录列表查询
     * @param userNo            用户名
     * @param primaryDirectory  一级目录名
     * @return                  一级目录列表或者二级目录列表
     */
    public List<String> queryDirectory(String userNo, String primaryDirectory) {
        List<String> list = new ArrayList<>();
        List<NoteInfoDO> noteInfoDOS = noteRepository.queryDirectory(userNo, primaryDirectory);
        //结果为空
        if(CollectionUtils.isEmpty(noteInfoDOS)){
            return list;
        }else{
            //设置一级目录列表
            if(StringUtils.isEmpty(primaryDirectory)){
                for(NoteInfoDO noteInfoDO: noteInfoDOS){
                    list.add(noteInfoDO.getPrimaryDirectory());
                }
                return list;
            }
            //设置二级目录列表
            for(NoteInfoDO noteInfoDO: noteInfoDOS){
                list.add(noteInfoDO.getSecondaryDirectory());
            }
            return list;
        }
    }

    /**
     * 根据文档id查询文档信息
     *
     * @param reqBO 请求对象
     * @return      查询结果
     */
    public List<NoteInfoBO> queryNoteInfo(NoteQueryReqBO reqBO) {
        return NoteInfoBOConvert.getBOsByDOs(noteRepository.queryNoteInfo(NoteInfoBOConvert.getReqDOByBO(reqBO)));
    }

    /**
     * 保存笔记
     *
     * @param reqBO         请求参数
     */
    public Boolean saveNote(NoteSaveReqBO reqBO) {

        //查询笔记是否存在
        NoteQueryReqDO reqDO = new NoteQueryReqDO();
        reqDO.setUserNo(reqBO.getUserNo());
        reqDO.setPrimaryDirectory(reqBO.getPrimaryDirectory());
        reqDO.setDocumentId(reqBO.getDocumentId());
        List<NoteInfoDO> noteInfoDOS = noteRepository.queryNoteInfo(reqDO);
        if(!CollectionUtils.isEmpty(noteInfoDOS)){
            //更新
            NoteInfoDO noteBOForUpdate = new NoteInfoDO();
            noteBOForUpdate.setUserNo(reqBO.getUserNo());
            noteBOForUpdate.setPrimaryDirectory(reqBO.getPrimaryDirectory());
            noteBOForUpdate.setDocumentId(reqBO.getDocumentId());
            noteBOForUpdate.setContent(reqBO.getContent());
            noteBOForUpdate.setUpdatedBy(reqBO.getUserNo());
            noteRepository.updateContent(noteBOForUpdate);
        }else{
            //新增
            NoteInfoDO noteBOForSave = new NoteInfoDO();
            noteBOForSave.setUserNo(reqBO.getUserNo());
            noteBOForSave.setPrimaryDirectory(reqBO.getPrimaryDirectory());
            noteBOForSave.setDocumentId(reqBO.getDocumentId());
            noteBOForSave.setContent(reqBO.getContent());
            noteBOForSave.setCreatedBy(reqBO.getUserNo());
            noteBOForSave.setUpdatedBy(reqBO.getUserNo());
            noteRepository.saveNote(noteBOForSave);
        }
        return true;
    }

    /**
     * 笔记管理请求业务处理
     * @param reqBO         请求参数BO
     * @return              处理结果
     */
    public boolean manage(NoteManageReqBO reqBO){

        //增加
        if("ADD".equals(reqBO.getOperatorType())){
            //查询已有，返回成功
            NoteQueryReqDO reqDO = new NoteQueryReqDO();
            reqDO.setUserNo(reqBO.getUserNo());
            reqDO.setPrimaryDirectory(reqBO.getPrimaryDirectory());
            List<NoteInfoDO> noteInfoDOS = noteRepository.queryNoteInfo(reqDO);
            if(!CollectionUtils.isEmpty(noteInfoDOS)){
                for(NoteInfoDO noteInfoDO : noteInfoDOS){
                    if(noteInfoDO.getDocumentName().equals(reqBO.getDocumentName())){
                        return true;
                    }
                }
            }

            //没有即新增
            NoteInfoDO noteInfoDO = new NoteInfoDO();
            noteInfoDO.setUserNo(reqBO.getUserNo());
            noteInfoDO.setPrimaryDirectory(reqBO.getPrimaryDirectory());
            noteInfoDO.setDocumentId(reqBO.getPrimaryDirectory() + (CollectionUtils.isEmpty(noteInfoDOS)? 1:
                    noteInfoDOS.size() + 1));
            noteInfoDO.setDocumentName(reqBO.getDocumentName());
            noteInfoDO.setCreatedBy(reqBO.getUserNo());
            noteInfoDO.setUpdatedBy(reqBO.getUserNo());
            noteRepository.saveNote(noteInfoDO);
        }

        //删除
        if("DELETE".equals(reqBO.getOperatorType())){

            NoteInfoDO noteBOForUpdate = new NoteInfoDO();
            noteBOForUpdate.setUserNo(reqBO.getUserNo());
            noteBOForUpdate.setPrimaryDirectory(reqBO.getPrimaryDirectory());
            noteBOForUpdate.setDocumentName(reqBO.getDocumentName());
            noteBOForUpdate.setStatus(NoteStatusEnum.DISABLE.getCode());
            noteRepository.updateContent(noteBOForUpdate);
        }
        return true;
    }
}
