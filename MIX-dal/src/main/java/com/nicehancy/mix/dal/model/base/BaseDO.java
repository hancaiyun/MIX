package com.nicehancy.mix.dal.model.base;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.Date;

/**
 * <p>
 *     数据库DO基类
 * <p/>
 *
 * @author hancaiyun
 * @since 2019/4/3 0:58
 **/
@Getter
@Setter
@ToString
public class BaseDO {

    /**
     * 数据库id
     */
    private Long id;

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
