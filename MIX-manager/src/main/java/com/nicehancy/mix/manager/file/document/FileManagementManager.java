package com.nicehancy.mix.manager.file.document;

import com.nicehancy.mix.common.constant.DatePatternConstant;
import com.nicehancy.mix.common.enums.FileFormatEnum;
import com.nicehancy.mix.common.enums.FilePathEnum;
import com.nicehancy.mix.common.enums.FileStatusEnum;
import com.nicehancy.mix.common.utils.DateUtil;
import com.nicehancy.mix.common.utils.FileOperateUtil;
import com.nicehancy.mix.common.utils.FilePathMappingUtil;
import com.nicehancy.mix.common.utils.UUIDUtil;
import com.nicehancy.mix.dal.FileUploadRecordRepository;
import com.nicehancy.mix.dal.model.FileUploadRecordDO;
import com.nicehancy.mix.manager.convert.FileRecordBOConvert;
import com.nicehancy.mix.manager.model.FileUploadRecordBO;
import com.nicehancy.mix.manager.model.FileUploadRecordPageQueryReqBO;
import com.nicehancy.mix.manager.model.FileUploadRequestBO;
import com.nicehancy.mix.manager.model.FileUploadResultBO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.Date;
import java.util.List;

/**
 * 文件管理manager
 * <p>
 * <p/>
 *
 * @author hancaiyun
 * @since 2019/12/5 13:52
 **/
@Component
@Slf4j
public class FileManagementManager {

    @Autowired
    private FileUploadRecordRepository fileUploadRecordRepository;

    /**
     * 文件上传
     * @param requestBO     请求参数BO
     * @return              返回文件路径
     */
    public FileUploadResultBO uploadFile(FileUploadRequestBO requestBO) throws IOException {

        //文件存储
        MultipartFile file = requestBO.getFileData();
        //原完整文件名
        String filename = file.getOriginalFilename();
        assert filename != null;
        //截取目录，获取文件名
        filename = filename.substring(filename.lastIndexOf("\\") + 1);

        //文件格式
        String suffix = filename.substring(filename.lastIndexOf("."));
        //服务器文件目录
        String path = FilePathMappingUtil.getPath(FilePathEnum.FILE_PATH.getCode());

        //变更文件名（唯一）
        String fileName = changeName(filename);

        //文件路径
        String filePath = path + fileName;
        log.info("filePath:{}", filePath);
        File localFile = new File(filePath);
        if (!localFile.exists()) {
            boolean isCreated = localFile.mkdirs();
            if(!isCreated){
                log.error("文件目录创建失败！");
                throw new RuntimeException("文件目录创建失败！");
            }
        }
        file.transferTo(localFile);

        //新增文件上传记录
        requestBO.setFileType(FileFormatEnum.expireOfCode(suffix).getType());
        requestBO.setFilePath(filePath);
        requestBO.setFileName(filename);
        fileUploadRecordRepository.insert(FileRecordBOConvert.getDOByBO(requestBO));

        //返回结果构建
        FileUploadResultBO resultBO = new FileUploadResultBO();
        resultBO.setFileType(FileFormatEnum.expireOfCode(suffix).getType());
        resultBO.setFileName(filename);
        return resultBO;
    }

    /**
     * 变更文件名
     * @param oldName        原始文件名
     * @return               新文件名
     */
    private String changeName(String oldName) {
        String newName = oldName.substring(oldName.lastIndexOf("."));
        //前缀
        String prefix = DateUtil.format(new Date(), DatePatternConstant.FULL_PATTERN) + UUIDUtil.createNoByUUId();
        newName = prefix + newName;
        return newName;
    }


    /**
     * 分页查询总条目数
     * @param reqBO         请求参数
     * @return              总条目数
     */
    public int queryCount(FileUploadRecordPageQueryReqBO reqBO) {

        return fileUploadRecordRepository.queryCount(FileRecordBOConvert.getDOByBO(reqBO));
    }

    /**
     * 分页查询结果集
     * @param reqBO         请求参数
     * @return              分页结果集
     */
    public List<FileUploadRecordBO> pageQuery(FileUploadRecordPageQueryReqBO reqBO) {

        return FileRecordBOConvert.getBOSByDOS(fileUploadRecordRepository.pageQuery(FileRecordBOConvert.getDOByBO(reqBO)));
    }

    /**
     * 查询文件上传明细
     * @param fileId        文件ID
     * @return              文件信息
     */
    public FileUploadRecordBO queryDetail(String fileId) {
        return FileRecordBOConvert.getBOByDO(fileUploadRecordRepository.queryDetail(fileId));
    }

    /**
     * 文件删除
     * @param fileId        文件ID
     * @param operator      操作人
     * @return              删除结果
     */
    public boolean deleteFile(String fileId, String operator) {

        FileUploadRecordDO fileUploadRecordDO = fileUploadRecordRepository.queryDetail(fileId);
        //更新数据库
        FileUploadRecordDO fileUploadRecordReqDO = new FileUploadRecordDO();
        fileUploadRecordReqDO.setFileId(fileId);
        fileUploadRecordReqDO.setFileStatus(FileStatusEnum.DELETE.getCode());
        fileUploadRecordReqDO.setUpdatedBy(operator);

        fileUploadRecordRepository.updateFileInfo(fileUploadRecordReqDO);

        //删除服务器文件
        FileOperateUtil.deleteFile(fileUploadRecordDO.getFilePath());

        return true;
    }
}
