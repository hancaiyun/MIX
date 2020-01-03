package com.nicehancy.MIX.service.convert.note;


import com.nicehancy.MIX.manager.model.NoteInfoBO;
import com.nicehancy.MIX.manager.model.NoteManageReqBO;
import com.nicehancy.MIX.manager.model.NoteQueryReqBO;
import com.nicehancy.MIX.manager.model.NoteSaveReqBO;
import com.nicehancy.MIX.service.api.model.NoteInfoDTO;
import com.nicehancy.MIX.service.api.model.request.note.NoteManageReqDTO;
import com.nicehancy.MIX.service.api.model.request.note.NoteQueryReqDTO;
import com.nicehancy.MIX.service.api.model.request.note.NoteSaveReqDTO;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.List;

/**
 *  笔记DTO转换类
 * <p>
 * <p/>
 *
 * @author hancaiyun
 * @since 2019/10/17 12:23
 **/
public class NoteDTOConvert {

    /**
     * 笔记转换类
     */
    private NoteDTOConvert(){
    }

    /**
     * 根据BO获取DTO
     * @param noteInfoBO        BO
     * @return                  DTO
     */
    public static NoteInfoDTO getDTOByBO(NoteInfoBO noteInfoBO) {

        if (noteInfoBO == null) {
            return null;
        }

        NoteInfoDTO noteInfoDTO = new NoteInfoDTO();
        noteInfoDTO.setUserNo(noteInfoBO.getUserNo());
        noteInfoDTO.setPrimaryDirectory(noteInfoBO.getPrimaryDirectory());
        noteInfoDTO.setSecondaryDirectory(noteInfoBO.getSecondaryDirectory());
        noteInfoDTO.setTertiaryDirectory(noteInfoBO.getTertiaryDirectory());
        noteInfoDTO.setDocumentName(noteInfoBO.getDocumentName());
        noteInfoDTO.setContent(noteInfoBO.getContent());
        noteInfoDTO.setStatus(noteInfoBO.getStatus());
        noteInfoDTO.setRemark(noteInfoBO.getRemark());
        noteInfoDTO.setCreatedAt(noteInfoBO.getCreatedAt());
        noteInfoDTO.setCreatedBy(noteInfoBO.getCreatedBy());
        noteInfoDTO.setUpdatedAt(noteInfoBO.getUpdatedAt());
        noteInfoDTO.setUpdatedBy(noteInfoBO.getUpdatedBy());

        return noteInfoDTO;
    }

    /**
     * 根据BOs转换成DTOs
     * @param noteInfoBOS         BOs
     * @return                    DTOs
     */
    public static List<NoteInfoDTO> getDTOsByBOs(List<NoteInfoBO> noteInfoBOS) {

        if(CollectionUtils.isEmpty(noteInfoBOS)){
            return null;
        }
        List<NoteInfoDTO> noteInfoDTOS = new ArrayList<>();
        for(NoteInfoBO noteInfoBO : noteInfoBOS){
            noteInfoDTOS.add(getDTOByBO(noteInfoBO));
        }
        return noteInfoDTOS;
    }

    /**
     * 查询请求DTO转BO
     * @param reqDTO            请求DTO
     * @return                  BO
     */
    public static NoteQueryReqBO getReqBOByDTO(NoteQueryReqDTO reqDTO) {

        if (reqDTO == null) {
            return null;
        }

        NoteQueryReqBO noteQueryReqBO = new NoteQueryReqBO();
        noteQueryReqBO.setUserNo(reqDTO.getUserNo());
        noteQueryReqBO.setPrimaryDirectory(reqDTO.getPrimaryDirectory());
        noteQueryReqBO.setDocumentId(reqDTO.getDocumentId());
        return noteQueryReqBO;
    }
    /**
     * 保存请求DTO转BO
     * @param reqDTO            请求DTO
     * @return                  BO
     */
    public static NoteSaveReqBO getReqBOByDTO(NoteSaveReqDTO reqDTO) {

        if (reqDTO == null) {
            return null;
        }

        NoteSaveReqBO noteSaveReqBO = new NoteSaveReqBO();
        noteSaveReqBO.setUserNo(reqDTO.getUserNo());
        noteSaveReqBO.setPrimaryDirectory(reqDTO.getPrimaryDirectory());
        noteSaveReqBO.setDocumentId(reqDTO.getDocumentId());
        noteSaveReqBO.setContent(reqDTO.getContent());
        return noteSaveReqBO;
    }

    /**
     * 笔记管理请求DTOzhuanBO
     * @param reqDTO              请求DTO
     * @return                    BO
     */
    public static NoteManageReqBO getReqBOByDTO(NoteManageReqDTO reqDTO) {

        if (reqDTO == null) {
            return null;
        }

        NoteManageReqBO noteManageReqBO = new NoteManageReqBO();
        noteManageReqBO.setUserNo(reqDTO.getUserNo());
        noteManageReqBO.setPrimaryDirectory(reqDTO.getPrimaryDirectory());
        noteManageReqBO.setOperatorType(reqDTO.getOperatorType());
        noteManageReqBO.setDocumentName(reqDTO.getDocumentName());

        return noteManageReqBO;
    }
}
