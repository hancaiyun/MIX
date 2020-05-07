package com.nicehancy.mix.dal.model.base;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * 分页查询请求基础类
 * <p>
 * <p/>
 *
 * @author hancaiyun
 * @since 2020/5/7 17:48
 **/
@Getter
@Setter
@ToString
public class PageQueryBaseDO {

    /**
     * 当前页
     */
    private Integer currentPage;

    /**
     * 每页显示条数
     */
    private Integer pageSize;
}
