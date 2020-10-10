package com.nicehancy.mix.service.convert.note;


import com.nicehancy.mix.common.constant.DatePatternConstant;
import com.nicehancy.mix.common.utils.DateUtil;
import com.nicehancy.mix.manager.model.*;
import com.nicehancy.mix.service.api.model.NoteInfoDTO;
import com.nicehancy.mix.service.api.model.request.note.*;
import com.nicehancy.mix.service.api.model.result.NoteShareDetailDTO;
import com.nicehancy.mix.service.api.model.result.NoteShareInfoDTO;
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
     * 根据BO获取DTO
     * @param noteShareInfoBO    BO
     * @return                   DTO
     */
    public static NoteShareInfoDTO getDTOByBO(NoteShareInfoBO noteShareInfoBO) {

        if (noteShareInfoBO == null) {
            return null;
        }

        NoteShareInfoDTO noteShareInfoDTO = new NoteShareInfoDTO();
        noteShareInfoDTO.setId(noteShareInfoBO.getId());
        noteShareInfoDTO.setUserNo(noteShareInfoBO.getUserNo());
        noteShareInfoDTO.setHeadCopy(noteShareInfoBO.getHeadCopy());
        noteShareInfoDTO.setNickName(noteShareInfoBO.getNickName());
        noteShareInfoDTO.setDocumentName(noteShareInfoBO.getDocumentName());
        noteShareInfoDTO.setCreatedAt(DateUtil.format(noteShareInfoBO.getCreatedAt(), DatePatternConstant.STANDARD_PATTERN));
        noteShareInfoDTO.setCreatedBy(noteShareInfoBO.getCreatedBy());
        noteShareInfoDTO.setUpdatedAt(DateUtil.format(noteShareInfoBO.getUpdatedAt(), DatePatternConstant.STANDARD_PATTERN));
        noteShareInfoDTO.setUpdatedBy(noteShareInfoBO.getUpdatedBy());

        return noteShareInfoDTO;
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
        noteQueryReqBO.setSecondaryDirectory(reqDTO.getSecondaryDirectory());
        noteQueryReqBO.setDocumentName(reqDTO.getDocumentName());
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
        noteSaveReqBO.setSecondaryDirectory(reqDTO.getSecondaryDirectory());
        noteSaveReqBO.setDocumentName(reqDTO.getDocumentName());
        noteSaveReqBO.setContent(reqDTO.getContent());
        return noteSaveReqBO;
    }

    /**
     * 笔记分享请求DTO转BO
     * @param reqDTO            请求DTO
     * @return                  BO
     */
    public static NoteShareReqBO getReqBOByDTO(NoteShareReqDTO reqDTO) {

        if (reqDTO == null) {
            return null;
        }

        NoteShareReqBO noteShareReqBO = new NoteShareReqBO();
        noteShareReqBO.setUserNo(reqDTO.getUserNo());
        noteShareReqBO.setPrimaryDirectory(reqDTO.getPrimaryDirectory());
        noteShareReqBO.setSecondaryDirectory(reqDTO.getSecondaryDirectory());
        noteShareReqBO.setDocumentName(reqDTO.getDocumentName());
        noteShareReqBO.setShareFlag(reqDTO.getShareFlag());

        return noteShareReqBO;
    }

    /**
     * 共享明细BO转DTO
     * @param noteShareDetailBO BO
     * @return                  DTO
     */
    public static NoteShareDetailDTO getDTOByBO(NoteShareDetailBO noteShareDetailBO) {

        if (noteShareDetailBO == null) {
            return null;
        }

        NoteShareDetailDTO noteShareDetailDTO = new NoteShareDetailDTO();
        noteShareDetailDTO.setId(noteShareDetailBO.getId());
        noteShareDetailDTO.setUserNo(noteShareDetailBO.getUserNo());
        noteShareDetailDTO.setDocumentName(noteShareDetailBO.getDocumentName());
        noteShareDetailDTO.setContent(noteShareDetailBO.getContent());
        noteShareDetailDTO.setSeeCount(noteShareDetailBO.getSeeCount());
        noteShareDetailDTO.setSupportCount(noteShareDetailBO.getSupportCount());
        noteShareDetailDTO.setCreatedAt(DateUtil.format(noteShareDetailBO.getCreatedAt(), DatePatternConstant.STANDARD_PATTERN));
        noteShareDetailDTO.setCreatedBy(noteShareDetailBO.getCreatedBy());
        noteShareDetailDTO.setUpdatedAt(DateUtil.format(noteShareDetailBO.getUpdatedAt(), DatePatternConstant.STANDARD_PATTERN));
        noteShareDetailDTO.setUpdatedBy(noteShareDetailBO.getUpdatedBy());

        return noteShareDetailDTO;
    }

    /**
     * 笔记管理请求DTO转BO
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
        noteManageReqBO.setSecondaryDirectory(reqDTO.getSecondaryDirectory());
        noteManageReqBO.setFileName(reqDTO.getFileName());
        noteManageReqBO.setOpType(reqDTO.getOpType());
        noteManageReqBO.setOpLocation(reqDTO.getOpLocation());
        noteManageReqBO.setOpName(reqDTO.getOpName());

        return noteManageReqBO;
    }

    /**
     * 笔记删除请求DTO转BO
     * @param reqDTO              请求DTO
     * @return                    BO
     */
    public static NoteDeleteReqBO getReqBOByDTO(NoteDeleteReqDTO reqDTO) {

        if (reqDTO == null) {
            return null;
        }

        NoteDeleteReqBO noteDeleteReqBO = new NoteDeleteReqBO();
        noteDeleteReqBO.setUserNo(reqDTO.getUserNo());
        noteDeleteReqBO.setPrimaryDirectory(reqDTO.getPrimaryDirectory());
        noteDeleteReqBO.setSecondaryDirectory(reqDTO.getSecondaryDirectory());
        noteDeleteReqBO.setDocumentName(reqDTO.getDocumentName());

        return noteDeleteReqBO;
    }

    /**
     * 笔记目录查询DTO转换成BO
     * @param reqDTO                请求DTO
     * @return                      请求BO
     */
    public static DirectoryQueryReqBO getBOByDTO(DirectoryQueryReqDTO reqDTO) {

        if (reqDTO == null) {
            return null;
        }

        DirectoryQueryReqBO directoryQueryReqBO = new DirectoryQueryReqBO();
        directoryQueryReqBO.setUserNo(reqDTO.getUserNo());
        directoryQueryReqBO.setPrimaryDirectory(reqDTO.getPrimaryDirectory());
        directoryQueryReqBO.setSecondaryDirectory(reqDTO.getSecondaryDirectory());
        return directoryQueryReqBO;
    }

    /**
     * 根据BOs转换成DTOs
     * @param noteShareInfoBOS       BOs
     * @return                       DTOs
     */
    public static List<NoteShareInfoDTO> getShareDTOsByBOs(List<NoteShareInfoBO> noteShareInfoBOS) {

        if(CollectionUtils.isEmpty(noteShareInfoBOS)){
            return null;
        }
        List<NoteShareInfoDTO> noteShareInfoDTOS = new ArrayList<>();
        for(NoteShareInfoBO noteShareInfoBO : noteShareInfoBOS){
            noteShareInfoDTOS.add(getDTOByBO(noteShareInfoBO));
        }
        return noteShareInfoDTOS;
    }



    /**
     * 文件列表查询请求DTO转BO
     * @param reqDTO                请求DTO
     * @return                      请求BO
     */
    public static FileListReqBO getBOByDTO(FileListReqDTO reqDTO) {

        if (reqDTO == null) {
            return null;
        }

        FileListReqBO fileListReqBO = new FileListReqBO();
        fileListReqBO.setUserNo(reqDTO.getUserNo());
        fileListReqBO.setPrimaryDirectory(reqDTO.getPrimaryDirectory());

        return fileListReqBO;
    }
}
