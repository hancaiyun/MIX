package com.nicehancy.mix.manager;

import com.nicehancy.mix.base.BaseSpringTest;
import com.nicehancy.mix.manager.model.UserLoginRecordBO;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

import java.util.*;

/**
 * <p>
 * <p/>
 *
 * @author hancaiyun
 * @since 2020/8/11 18:04
 **/
@Slf4j
public class UserLoginRecordManagerTest extends BaseSpringTest {

    @Test
    public void test(){

        UserLoginRecordBO userLoginRecordBO1 = new UserLoginRecordBO();
        userLoginRecordBO1.setUserNo("19921577717");
        userLoginRecordBO1.setLoginTime(new Date());

        UserLoginRecordBO userLoginRecordBO2 = new UserLoginRecordBO();
        userLoginRecordBO2.setUserNo("19921577717");
        userLoginRecordBO2.setLoginTime(new Date());

        UserLoginRecordBO userLoginRecordBO3 = new UserLoginRecordBO();
        userLoginRecordBO3.setUserNo("15021500935");
        userLoginRecordBO3.setLoginTime(new Date());

        List<UserLoginRecordBO> recordBOList = new ArrayList<>();
        recordBOList.add(userLoginRecordBO1);
        recordBOList.add(userLoginRecordBO2);
        recordBOList.add(userLoginRecordBO3);
        try {
            Map<String, List<UserLoginRecordBO>> group = groupLoginRecordDataByUserNo(recordBOList);
            log.info("group:{}", group);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 数据分组
     *
     * @param recordBOList       登录记录BO列表
     * @return map               map
     * @throws Exception         e
     */
    private Map<String, List<UserLoginRecordBO>> groupLoginRecordDataByUserNo(List<UserLoginRecordBO> recordBOList)
            throws Exception {
        Map<String, List<UserLoginRecordBO>> resultMap = new HashMap<>();
        try {
            for (UserLoginRecordBO recordBO : recordBOList) {

                if (resultMap.containsKey(recordBO.getUserNo())) {//map中异常批次已存在，将该数据存放到同一个key（key存放的是异常批次）的map中
                    resultMap.get(recordBO.getUserNo()).add(recordBO);
                } else {//map中不存在，新建key，用来存放数据
                    List<UserLoginRecordBO> tmpList = new ArrayList<>();
                    tmpList.add(recordBO);
                    resultMap.put(recordBO.getUserNo(), tmpList);
                }
            }
        } catch (Exception e) {
            throw new Exception("数据进行分组时出现异常", e);
        }
        return resultMap;
    }
}
