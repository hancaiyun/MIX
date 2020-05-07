package com.nicehancy.mix.manager.model.base;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;

/**
 * 基础BO模型
 * <p>
 * <p/>
 *
 * @author hancaiyun
 * @since 2019/11/8 9:04
 **/
@Getter
@Setter
public class BaseBO {

    /**
     * 创建时间
     */
    private Date createdAt;

    /**
     * 创建人
     */
    private String createdBy;

    /**
     * 更新时间
     */
    private Date updatedAt;

    /**
     * 更新人
     */
    private String updatedBy;
}
