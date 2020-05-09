package com.nicehancy.mix.service.api.model.result.base;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import java.io.Serializable;
import java.util.List;

/**
 * 分页查询返回结果基础父类
 * <p>
 * <p/>
 *
 * @author hancaiyun
 * @since 2020/5/9 14:57
 **/
@Getter
@Setter
@ToString
public class BasePageQueryResDTO<T> implements Serializable {

    /**
     * 序列化ID
     */
    private static final long serialVersionUID = -5684557553210297266L;

    /**
     * 总条数
     */
    private Integer count;

    /**
     * 分页结果集
     */
    private List<T> pageResult;
}
