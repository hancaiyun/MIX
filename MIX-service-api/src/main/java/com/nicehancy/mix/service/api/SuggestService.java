package com.nicehancy.mix.service.api;

import com.nicehancy.mix.common.Result;
import com.nicehancy.mix.service.api.model.request.SuggestCommitReqDTO;

/**
 * 反馈信息提交接口
 * <p>
 * <p/>
 *
 * @author hancaiyun
 * @since 2020/7/13 16:45
 **/
public interface SuggestService {

    /**
     * 反馈信息提交接口
     * @param reqDTO        请求参数
     * @return              提交结果
     */
    Result<Boolean> commit(SuggestCommitReqDTO reqDTO);
}
