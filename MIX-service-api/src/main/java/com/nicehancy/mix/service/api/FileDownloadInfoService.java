package com.nicehancy.mix.service.api;

import com.nicehancy.mix.common.Result;
import com.nicehancy.mix.service.api.model.FileDownloadInfoDTO;
import com.nicehancy.mix.service.api.model.request.FileDownloadInfoPageQueryReqDTO;
import com.nicehancy.mix.service.api.model.result.base.BasePageQueryResDTO;

/**
 * 文件下载接口
 * <p>
 * <p/>
 *
 * @author hancaiyun
 * @since 2020/8/5 18:37
 **/
public interface FileDownloadInfoService {

    /**
     * 分页查询
     * @param reqDTO            请求DTO
     * @return                  分页结果集
     */
    Result<BasePageQueryResDTO<FileDownloadInfoDTO>> pageQuery(FileDownloadInfoPageQueryReqDTO reqDTO);
}
