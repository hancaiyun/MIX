package com.nicehancy.mix.dal;

import com.nicehancy.mix.dal.model.UserLoginRecordDO;
import java.util.Date;
import java.util.List;

/**
 * 用户登录记录表
 * <p>
 * <p/>
 *
 * @author hancaiyun
 * @since 2020/8/11 15:57
 **/
public interface UserLoginRecordRepository {

    /**
     * 新增登录记录
     * @param userLoginRecordDO             登录记录DO
     */
    void insert(UserLoginRecordDO userLoginRecordDO);

    /**
     * 更新登录记录-登出时间
     * @param userLoginRecordDO             登录记录DO
     */
    void update(UserLoginRecordDO userLoginRecordDO);

    /**
     * 分页查询登录记录
     * @param startDate     起始时间
     * @param endDate       结束时间
     * @param pageNum       页码
     * @param pageSize      每页条目数
     * @return              记录列表
     */
    List<UserLoginRecordDO> pageQuery(Date startDate, Date endDate, int pageNum, int pageSize);
}
