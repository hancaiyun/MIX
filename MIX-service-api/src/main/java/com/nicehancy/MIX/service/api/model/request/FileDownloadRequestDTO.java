package com.nicehancy.MIX.service.api.model.request;

import com.nicehancy.MIX.service.api.model.base.BaseReqDTO;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;

/**
 * 文件下载请求参数
 * <p>
 * <p/>
 *
 * @author hancaiyun
 * @since 2019/12/5 12:51
 **/
@Getter
@Setter
@ToString
public class FileDownloadRequestDTO extends BaseReqDTO implements Serializable {

    /**
     * 序列化ID
     */
    private static final long serialVersionUID = 3384075084859628474L;

    /**
     * 文件id，唯一标识
     */
    private String fileId;
}
