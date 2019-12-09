package com.nicehancy.MIX.manager.file.document;

import com.nicehancy.MIX.manager.model.FileUploadRequestBO;
import com.nicehancy.MIX.manager.model.FileUploadResultBO;
import org.springframework.stereotype.Component;

/**
 * 文件管理manager
 * <p>
 * <p/>
 *
 * @author hancaiyun
 * @since 2019/12/5 13:52
 **/
@Component
public class FileManagementManager {

    /**
     * 文件上传
     * @param requestBO     请求参数BO
     * @return              返回文件路径
     */
    public FileUploadResultBO uploadFile(FileUploadRequestBO requestBO){

        //文件存储


        //文件路径更新

        FileUploadResultBO resultBO = new FileUploadResultBO();
        resultBO.setFileId("qeiqbaiudh1937219873");
        resultBO.setFileName("测试.PNG");
        return resultBO;
    }
}
