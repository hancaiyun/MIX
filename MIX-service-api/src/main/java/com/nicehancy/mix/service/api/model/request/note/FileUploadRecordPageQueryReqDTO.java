package com.nicehancy.mix.service.api.model.request.note;

import com.nicehancy.mix.service.api.model.base.BaseReqDTO;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import java.io.Serializable;

/**
 * 文件上传记录分页查询
 * <p>
 * <p/>
 *
 * @author hancaiyun
 * @since 2020/6/15 15:46
 **/
@Getter
@Setter
@ToString
public class FileUploadRecordPageQueryReqDTO extends BaseReqDTO implements Serializable {

    /**
     * 序列化ID
     */
    private static final long serialVersionUID = -399878446878099197L;

    /**
     * 用户编号
     */
    private String userNo;

    /**
     * 文件ID
     */
    private String fileId;

    /**
     * 文件名
     */
    private String fileName;

    /**
     * 文件类型
     */
    private String fileType;

    /**
     * 当前页
     */
    private Integer currentPage;

    /**
     * 每页条目数
     */
    private Integer pageSize;
}
