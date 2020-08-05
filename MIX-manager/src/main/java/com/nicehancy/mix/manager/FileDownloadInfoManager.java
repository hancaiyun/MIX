package com.nicehancy.mix.manager;

import com.nicehancy.mix.dal.FileDownloadInfoRepository;
import com.nicehancy.mix.dal.model.FileDownloadInfoDO;
import com.nicehancy.mix.manager.convert.FileDownloadInfoBOConvert;
import com.nicehancy.mix.manager.model.FileDownloadInfoBO;
import com.nicehancy.mix.manager.model.FileDownloadInfoPageQueryReqBO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import java.util.List;

/**
 * 文件下载中心manager
 * <p>
 * <p/>
 *
 * @author hancaiyun
 * @since 2020/8/4 15:37
 **/
@Component
public class FileDownloadInfoManager {

    @Autowired
    private FileDownloadInfoRepository fileDownloadInfoRepository;

    /**
     * 分页查询请求BO
     * @param pageQueryReqBO           BO
     * @return                         总条目数
     */
    public Integer queryCount(FileDownloadInfoPageQueryReqBO pageQueryReqBO){

        return fileDownloadInfoRepository.queryCount(FileDownloadInfoBOConvert.getDOByBO(pageQueryReqBO));
    }

    /**
     * 分页查询下载列表
     * @param pageQueryReqBO           BO
     * @return                         分页列表
     */
    public List<FileDownloadInfoBO> pageQuery(FileDownloadInfoPageQueryReqBO pageQueryReqBO){

        List<FileDownloadInfoDO> fileDownloadInfoDOS = fileDownloadInfoRepository.pageQuery(FileDownloadInfoBOConvert.
                getDOByBO(pageQueryReqBO));

        return FileDownloadInfoBOConvert.getBOsByDOs(fileDownloadInfoDOS);
    }
}
