package com.nicehancy.mix.manager.note;

import com.nicehancy.mix.common.Result;
import com.nicehancy.mix.common.enums.NoteStatusEnum;
import com.nicehancy.mix.dal.NoteInfoRepository;
import com.nicehancy.mix.dal.model.NoteInfoDO;
import com.nicehancy.mix.dal.model.request.NoteQueryReqDO;
import com.nicehancy.mix.manager.convert.NoteInfoBOConvert;
import com.nicehancy.mix.manager.model.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

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
     * @param reqBO             请求BO
     * @return                  一级目录列表或者二级目录列表或者文件列表
     */
    public List<String> queryDirectory(DirectoryQueryReqBO reqBO) {
        List<String> list = new ArrayList<>();
        List<NoteInfoDO> noteInfoDOS = noteRepository.queryDirectory(NoteInfoBOConvert.getDOByBO(reqBO));
        //结果为空
        if(CollectionUtils.isEmpty(noteInfoDOS)){
            return list;
        }else{
            //设置一级目录列表
            if(StringUtils.isEmpty(reqBO.getPrimaryDirectory())){
                for(NoteInfoDO noteInfoDO: noteInfoDOS){
                    list.add(noteInfoDO.getPrimaryDirectory());
                }
                return list.stream().distinct().collect(Collectors.toList());
            }
            //设置二级目录列表
            if(StringUtils.isEmpty(reqBO.getSecondaryDirectory())) {
                for (NoteInfoDO noteInfoDO : noteInfoDOS) {
                    if (StringUtils.isEmpty(noteInfoDO.getSecondaryDirectory())) {
                        continue;
                    }
                    list.add(noteInfoDO.getSecondaryDirectory());
                }
                return list.stream().distinct().collect(Collectors.toList());
            }
            //设置文件名列表
            for(NoteInfoDO noteInfoDO: noteInfoDOS){
                if(StringUtils.isEmpty(noteInfoDO.getFileName())){
                    continue;
                }
                list.add(noteInfoDO.getFileName());
            }
            return list.stream().distinct().collect(Collectors.toList());
        }
    }

    /**
     * 文件列表查询
     * @param reqBO     请求BO
     * @return          文件列表
     */
    public List<String> queryFileList(FileListReqBO reqBO) {

        List<String> list = new ArrayList<>();
        List<NoteInfoDO> noteInfoDOS = noteRepository.queryFileList(NoteInfoBOConvert.getDOByBO(reqBO));
        if(CollectionUtils.isEmpty(noteInfoDOS)){
            return list;
        }
        for(NoteInfoDO noteInfoDO: noteInfoDOS){
            list.add(noteInfoDO.getFileName());
        }
        return list;
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
        reqDO.setSecondaryDirectory(reqBO.getSecondaryDirectory());
        reqDO.setFileName(reqBO.getDocumentName());
        List<NoteInfoDO> noteInfoDOS = noteRepository.queryNoteInfo(reqDO);
        if(!CollectionUtils.isEmpty(noteInfoDOS)){
            //更新
            NoteInfoDO noteBOForUpdate = new NoteInfoDO();
            noteBOForUpdate.setUserNo(reqBO.getUserNo());
            noteBOForUpdate.setPrimaryDirectory(reqBO.getPrimaryDirectory());
            noteBOForUpdate.setSecondaryDirectory(reqBO.getSecondaryDirectory());
            noteBOForUpdate.setFileName(reqBO.getDocumentName());
            noteBOForUpdate.setContent(reqBO.getContent());
            noteBOForUpdate.setUpdatedBy(reqBO.getUserNo());
            noteRepository.updateContent(noteBOForUpdate);
        }else{
            //新增
            NoteInfoDO noteBOForSave = new NoteInfoDO();
            noteBOForSave.setUserNo(reqBO.getUserNo());
            noteBOForSave.setPrimaryDirectory(reqBO.getPrimaryDirectory());
            noteBOForSave.setSecondaryDirectory(reqBO.getSecondaryDirectory());
            noteBOForSave.setFileName(reqBO.getDocumentName());
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
    public Result<Boolean> manage(NoteManageReqBO reqBO){

        //增加
        if("ADD".equals(reqBO.getOpType())){
            doAdd(reqBO);
        }

        //删除
        if("DELETE".equals(reqBO.getOpType())){
            doDelete(reqBO);
        }

        //修改
        if("EDIT".equals(reqBO.getOpType())){
            doEdit(reqBO);
        }
        return new Result<>(true);
    }

    /**
     * 新增
     * @param reqBO     请求BO
     */
    private void doAdd(NoteManageReqBO reqBO){

        NoteQueryReqDO reqDO = new NoteQueryReqDO();
        NoteInfoDO noteInfoDO = new NoteInfoDO();
        //根据操作位置分开处理
        if("PRIMARY_DIRECTORY_OP".equals(reqBO.getOpLocation())){
            //重复性校验
            reqDO.setUserNo(reqBO.getUserNo());
            reqDO.setPrimaryDirectory(reqBO.getOpName());
            repeatCheck(reqDO, "该一级目录已存在");

            //新增参数设置
            noteInfoDO.setUserNo(reqBO.getUserNo());
            noteInfoDO.setPrimaryDirectory(reqBO.getOpName());
        }
        if("SECONDARY_DIRECTORY_OP".equals(reqBO.getOpLocation())){
            //重复性校验
            reqDO.setUserNo(reqBO.getUserNo());
            reqDO.setPrimaryDirectory(reqBO.getPrimaryDirectory());
            reqDO.setSecondaryDirectory(reqBO.getOpName());
            repeatCheck(reqDO, "该二级目录已存在");

            //新增参数设置
            noteInfoDO.setUserNo(reqBO.getUserNo());
            noteInfoDO.setPrimaryDirectory(reqBO.getPrimaryDirectory());
            noteInfoDO.setSecondaryDirectory(reqBO.getOpName());
        }
        if("FILE_OP".equals(reqBO.getOpLocation())){
            //重复性校验
            reqDO.setUserNo(reqBO.getUserNo());
            reqDO.setPrimaryDirectory(reqBO.getPrimaryDirectory());
            reqDO.setSecondaryDirectory(reqBO.getSecondaryDirectory());
            reqDO.setFileName(reqBO.getOpName());
            repeatCheck(reqDO, "该文件名已存在");

            //新增参数设置
            noteInfoDO.setUserNo(reqBO.getUserNo());
            noteInfoDO.setPrimaryDirectory(reqBO.getPrimaryDirectory());
            noteInfoDO.setSecondaryDirectory(reqBO.getSecondaryDirectory());
            noteInfoDO.setFileName(reqBO.getOpName());
        }

        //新增
        noteInfoDO.setCreatedBy(reqBO.getUserNo());
        noteInfoDO.setUpdatedBy(reqBO.getUserNo());
        noteRepository.saveNote(noteInfoDO);
    }

    /**
     * 重复性校验
     * @param reqDO          查询请求reqDTO
     * @param message        提示信息
     */
    private void repeatCheck(NoteQueryReqDO reqDO, String message) {

        List<NoteInfoDO> noteInfoDOS = noteRepository.queryNoteInfo(reqDO);
        if(!CollectionUtils.isEmpty(noteInfoDOS)){
            throw new RuntimeException(message);
        }
    }

    /**
     * 删除
     * @param reqBO     请求BO
     */
    private void doDelete(NoteManageReqBO reqBO){

        NoteInfoDO updateReqDO = new NoteInfoDO();
        updateReqDO.setUserNo(reqBO.getUserNo());
        //一级目录
        if("PRIMARY_DIRECTORY_OP".equals(reqBO.getOpLocation())) {
            updateReqDO.setPrimaryDirectory(reqBO.getPrimaryDirectory());
            //设置状态为不可用
            updateReqDO.setStatus(NoteStatusEnum.DISABLE.getCode());
        }
        //二级目录
        if("SECONDARY_DIRECTORY_OP".equals(reqBO.getOpLocation())){
            updateReqDO.setPrimaryDirectory(reqBO.getPrimaryDirectory());
            updateReqDO.setSecondaryDirectory(reqBO.getSecondaryDirectory());
        }
        //文件
        if("FILE_OP".equals(reqBO.getOpLocation())){
            updateReqDO.setPrimaryDirectory(reqBO.getPrimaryDirectory());
            updateReqDO.setSecondaryDirectory(reqBO.getSecondaryDirectory());
            updateReqDO.setFileName(reqBO.getFileName());
            //设置状态为不可用
            updateReqDO.setStatus(NoteStatusEnum.DISABLE.getCode());
        }

        updateReqDO.setUpdatedBy(reqBO.getUserNo());
        noteRepository.updateForDelete(updateReqDO, reqBO.getOpLocation());
    }

    /**
     * 修改
     * @param reqBO     请求BO
     */
    private void doEdit(NoteManageReqBO reqBO){

        NoteQueryReqDO reqDO = new NoteQueryReqDO();
        NoteInfoDO noteInfoDO = new NoteInfoDO();
        //查询参数设置
        noteInfoDO.setUserNo(reqBO.getUserNo());
        noteInfoDO.setPrimaryDirectory(reqBO.getPrimaryDirectory());
        //根据操作位置分开处理
        if("PRIMARY_DIRECTORY_OP".equals(reqBO.getOpLocation())){
            //重复性校验
            reqDO.setUserNo(reqBO.getUserNo());
            reqDO.setPrimaryDirectory(reqBO.getOpName());
            repeatCheck(reqDO, "该一级目录已存在");
        }
        if("SECONDARY_DIRECTORY_OP".equals(reqBO.getOpLocation())){
            //重复性校验
            reqDO.setUserNo(reqBO.getUserNo());
            reqDO.setPrimaryDirectory(reqBO.getPrimaryDirectory());
            reqDO.setSecondaryDirectory(reqBO.getOpName());
            repeatCheck(reqDO, "该二级目录已存在");

            //查询参数设置
            noteInfoDO.setSecondaryDirectory(reqBO.getSecondaryDirectory());
        }
        if("FILE_OP".equals(reqBO.getOpLocation())){
            //重复性校验
            reqDO.setUserNo(reqBO.getUserNo());
            reqDO.setPrimaryDirectory(reqBO.getPrimaryDirectory());
            reqDO.setSecondaryDirectory(reqBO.getSecondaryDirectory());
            reqDO.setFileName(reqBO.getOpName());
            repeatCheck(reqDO, "该文件名已存在");

            //查询参数设置
            noteInfoDO.setSecondaryDirectory(reqBO.getSecondaryDirectory());
            noteInfoDO.setFileName(reqBO.getFileName());
        }

        //更新

        noteInfoDO.setUpdatedBy(reqBO.getUserNo());
        noteRepository.updateDirectory(noteInfoDO, reqBO.getOpLocation(), reqBO.getOpName());
    }

    /**
     * 笔记删除
     * @param reqBO         请求BO
     * @return              处理结果
     */
    public Result<Boolean> delete(NoteDeleteReqBO reqBO) {

        Result<Boolean> result = new Result<>();

        NoteInfoDO noteBOForUpdate = new NoteInfoDO();
        noteBOForUpdate.setUserNo(reqBO.getUserNo());
        noteBOForUpdate.setPrimaryDirectory(reqBO.getPrimaryDirectory());
        noteBOForUpdate.setSecondaryDirectory(reqBO.getSecondaryDirectory());
        noteBOForUpdate.setFileName(reqBO.getDocumentName());
        noteBOForUpdate.setUpdatedBy(reqBO.getUserNo());
        //设置状态为不可用
        noteBOForUpdate.setStatus(NoteStatusEnum.DISABLE.getCode());
        noteRepository.updateContent(noteBOForUpdate);

        result.setResult(true);
        return result;
    }

    /**
     * 笔记分享
     * @param reqBO          分享请求BO
     * @return               分享结果
     */
    public boolean share(NoteShareReqBO reqBO) {

        NoteInfoDO noteInfoDO = new NoteInfoDO();
        noteInfoDO.setUserNo(reqBO.getUserNo());
        noteInfoDO.setPrimaryDirectory(reqBO.getPrimaryDirectory());
        noteInfoDO.setSecondaryDirectory(reqBO.getSecondaryDirectory());
        noteInfoDO.setFileName(reqBO.getDocumentName());
        noteInfoDO.setUpdatedBy(reqBO.getUserNo());
        //设置分享标识
        noteInfoDO.setShareFlag(reqBO.getShareFlag());

        noteRepository.updateForShare(noteInfoDO);

        return true;
    }
}
