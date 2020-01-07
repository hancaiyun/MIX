package com.nicehancy.MIX.manager.convert;

import com.nicehancy.MIX.dal.model.DirectoryQueryReqDO;
import com.nicehancy.MIX.dal.model.FileListReqDO;
import com.nicehancy.MIX.dal.model.NoteInfoDO;
import com.nicehancy.MIX.dal.model.NoteQueryReqDO;
import com.nicehancy.MIX.manager.model.DirectoryQueryReqBO;
import com.nicehancy.MIX.manager.model.FileListReqBO;
import com.nicehancy.MIX.manager.model.NoteInfoBO;
import com.nicehancy.MIX.manager.model.NoteQueryReqBO;
import org.springframework.util.CollectionUtils;
import java.util.ArrayList;
import java.util.List;

/**
 * 笔记BO模型转换类
 * <p>
 * <p/>
 *
 * @author hancaiyun
 * @since 2019/10/17 11:21
 **/
public class NoteInfoBOConvert {

    /**
     * 无参私有构造函数
     */
    private NoteInfoBOConvert(){
    }

    /**
     * 通过BO获取DO
     * @param noteBO     BO
     * @return           DO
     */
    public static NoteInfoDO getDOByBO(NoteInfoBO noteBO) {

        if (noteBO == null) {
            return null;
        }

        NoteInfoDO noteDO = new NoteInfoDO();
        noteDO.setUserNo(noteBO.getUserNo());
        noteDO.setPrimaryDirectory(noteBO.getPrimaryDirectory());
        noteDO.setSecondaryDirectory(noteBO.getSecondaryDirectory());
        noteDO.setTertiaryDirectory(noteBO.getTertiaryDirectory());
        noteDO.setDocumentName(noteBO.getDocumentName());
        noteDO.setContent(noteBO.getContent());
        noteDO.setStatus(noteBO.getStatus());
        noteDO.setRemark(noteBO.getRemark());
        noteDO.setCreatedAt(noteBO.getCreatedAt());
        noteDO.setCreatedBy(noteBO.getCreatedBy());
        noteDO.setUpdatedAt(noteBO.getUpdatedAt());
        noteDO.setUpdatedBy(noteBO.getUpdatedBy());

        return noteDO;
    }

    /**
     * 通过DO获取BO
     * @param noteDO      DO
     * @return            BO
     */
    public static NoteInfoBO getBOByDO(NoteInfoDO noteDO) {

        if (noteDO == null) {
            return null;
        }

        NoteInfoBO noteBO = new NoteInfoBO();
        noteBO.setUserNo(noteDO.getUserNo());
        noteBO.setPrimaryDirectory(noteDO.getPrimaryDirectory());
        noteBO.setSecondaryDirectory(noteDO.getSecondaryDirectory());
        noteBO.setTertiaryDirectory(noteDO.getTertiaryDirectory());
        noteBO.setDocumentName(noteDO.getDocumentName());
        noteBO.setContent(noteDO.getContent());
        noteBO.setStatus(noteDO.getStatus());
        noteBO.setRemark(noteDO.getRemark());
        noteBO.setCreatedAt(noteDO.getCreatedAt());
        noteBO.setCreatedBy(noteDO.getCreatedBy());
        noteBO.setUpdatedAt(noteDO.getUpdatedAt());
        noteBO.setUpdatedBy(noteDO.getUpdatedBy());

        return noteBO;
    }

    /**
     * DOs转BOs
     * @param noteInfoDOS   DOs
     * @return              BOs
     */
    public static List<NoteInfoBO> getBOsByDOs(List<NoteInfoDO> noteInfoDOS) {

        if(CollectionUtils.isEmpty(noteInfoDOS)){
            return null;
        }
        List<NoteInfoBO> noteInfoBOS = new ArrayList<>();
        for(NoteInfoDO noteInfoDO : noteInfoDOS){
            noteInfoBOS.add(getBOByDO(noteInfoDO));
        }
        return noteInfoBOS;
    }

    /**
     * 请求BO转DO
     * @param reqBO         BO
     * @return              DO
     */
    public static NoteQueryReqDO getReqDOByBO(NoteQueryReqBO reqBO) {

        if (reqBO == null) {
            return null;
        }

        NoteQueryReqDO noteQueryReqDO = new NoteQueryReqDO();
        noteQueryReqDO.setUserNo(reqBO.getUserNo());
        noteQueryReqDO.setPrimaryDirectory(reqBO.getPrimaryDirectory());
        noteQueryReqDO.setSecondaryDirectory(reqBO.getSecondaryDirectory());
        noteQueryReqDO.setDocumentName(reqBO.getDocumentName());

        return noteQueryReqDO;
    }

    /**
     * 请求BO转DO
     * @param reqBO         BO
     * @return              DO
     */
    public static DirectoryQueryReqDO getDOByBO(DirectoryQueryReqBO reqBO) {

        if (reqBO == null) {
            return null;
        }

        DirectoryQueryReqDO directoryQueryReqDO = new DirectoryQueryReqDO();
        directoryQueryReqDO.setUserNo(reqBO.getUserNo());
        directoryQueryReqDO.setPrimaryDirectory(reqBO.getPrimaryDirectory());
        directoryQueryReqDO.setSecondaryDirectory(reqBO.getSecondaryDirectory());

        return directoryQueryReqDO;
    }

    /**
     * 文档列表查询请求BO转DO
     * @param reqBO             BO
     * @return                  DO
     */
    public static FileListReqDO getDOByBO(FileListReqBO reqBO) {

        if (reqBO == null) {
            return null;
        }

        FileListReqDO fileListReqDO = new FileListReqDO();
        fileListReqDO.setUserNo(reqBO.getUserNo());
        fileListReqDO.setPrimaryDirectory(reqBO.getPrimaryDirectory());

        return fileListReqDO;
    }
}
