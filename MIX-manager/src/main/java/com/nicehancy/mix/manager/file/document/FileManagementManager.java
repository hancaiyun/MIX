package com.nicehancy.mix.manager.file.document;

import com.nicehancy.mix.manager.model.FileUploadRecordBO;
import com.nicehancy.mix.manager.model.FileUploadRecordPageQueryReqBO;
import com.nicehancy.mix.manager.model.FileUploadRequestBO;
import com.nicehancy.mix.manager.model.FileUploadResultBO;
import org.springframework.stereotype.Component;

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

    /**
     * 分页查询总条目数
     * @param reqBO         请求参数
     * @return              总条目数
     */
    public int queryCount(FileUploadRecordPageQueryReqBO reqBO) {

        return 0;
    }

    /**
     * 分页查询结果集
     * @param reqBO         请求参数
     * @return              分页结果集
     */
    public List<FileUploadRecordBO> pageQuery(FileUploadRecordPageQueryReqBO reqBO) {
        return null;
    }
}
