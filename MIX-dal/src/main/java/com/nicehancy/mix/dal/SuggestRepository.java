package com.nicehancy.mix.dal;

/**
 * 信息反馈
 * <p>
 * <p/>
 *
 * @author hancaiyun
 * @since 2020/7/13 18:16
 **/
public interface SuggestRepository {

    /**
     * 反馈提交
     * @param userNo        用户编号
     * @param suggestion    反馈内容
     */
    void commit(String userNo, String suggestion);
}
