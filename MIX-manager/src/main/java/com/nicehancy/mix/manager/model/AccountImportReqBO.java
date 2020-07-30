package com.nicehancy.mix.manager.model;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.web.multipart.MultipartFile;

/**
 * 账号导入请求DTO
 * <p>
 * <p/>
 *
 * @author hancaiyun
 * @since 2020/7/29 14:47
 **/
@Getter
@Setter
@ToString
public class AccountImportReqBO {

    /**
     * 用户编号
     */
    private String userNo;

    /**
     * 文件数据
     */
    private MultipartFile fileData;
}
