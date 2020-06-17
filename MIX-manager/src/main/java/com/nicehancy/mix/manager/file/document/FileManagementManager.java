package com.nicehancy.mix.manager.file.document;

import com.nicehancy.mix.common.constant.CommonConstant;
import com.nicehancy.mix.common.constant.DatePatternConstant;
import com.nicehancy.mix.common.enums.FileFormatEnum;
import com.nicehancy.mix.common.utils.DateUtil;
import com.nicehancy.mix.common.utils.UUIDUtil;
import com.nicehancy.mix.dal.FileUploadRecordRepository;
import com.nicehancy.mix.manager.convert.FileRecordBOConvert;
import com.nicehancy.mix.manager.convert.MessageBOConvert;
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
        String oldName = file.getOriginalFilename();
        assert oldName != null;
        //文件格式
        String suffix = oldName.substring(oldName.indexOf('.'));

        //服务器文件目录
        String path = CommonConstant.FILE_PATH;

        //变更文件名（唯一）
        String fileName = changeName(oldName);

        //文件路径
        String filePath = path + fileName;
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
        requestBO.setFileName(oldName);
        fileUploadRecordRepository.insert(FileRecordBOConvert.getDOByBO(requestBO));

        //返回结果构建
        FileUploadResultBO resultBO = new FileUploadResultBO();
        resultBO.setFileType(FileFormatEnum.expireOfCode(suffix).getType());
        resultBO.setFileName(oldName);
        return resultBO;
    }

    /**
     * 变更文件名
     * @param oldName        原始文件名
     * @return
     */
    private String changeName(String oldName) {
        String newName = oldName.substring(oldName.indexOf('.'));
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
}
