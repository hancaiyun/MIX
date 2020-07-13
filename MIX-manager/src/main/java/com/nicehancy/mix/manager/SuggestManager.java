package com.nicehancy.mix.manager;

import com.nicehancy.mix.dal.SuggestRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * 反馈建议manager
 * <p>
 * <p/>
 *
 * @author hancaiyun
 * @since 2020/7/13 18:02
 **/
@Component
public class SuggestManager {

    @Autowired
    private SuggestRepository suggestRepository;

    /**
     * 反馈提交
     * @param userNo            用户编号
     * @param suggestion        意见反馈内容
     */
    public void commit(String userNo, String suggestion) {

        suggestRepository.commit(userNo, suggestion);
    }
}
