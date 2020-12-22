package com.nicehancy.mix.manager;

import com.nicehancy.mix.common.constant.CommonConstant;
import com.nicehancy.mix.common.constant.DatePatternConstant;
import com.nicehancy.mix.common.utils.DateUtil;
import com.nicehancy.mix.dal.UserInfoRepository;
import com.nicehancy.mix.dal.UserLoginRecordRepository;
import com.nicehancy.mix.dal.UserLoginReportRepository;
import com.nicehancy.mix.dal.model.UserInfoDO;
import com.nicehancy.mix.dal.model.UserLoginRecordDO;
import com.nicehancy.mix.dal.model.UserLoginReportDO;
import com.nicehancy.mix.manager.convert.UserLoginRecordConvert;
import com.nicehancy.mix.manager.model.UserLoginRecordBO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;
import java.util.*;

/**
 * 登陆统计记录表manager
 * <p>
 * <p/>
 *
 * @author hancaiyun
 * @since 2020/8/11 10:10
 **/
@Slf4j
@Component
public class LoginStatisticManager {

    @Autowired
    private UserLoginReportRepository userLoginReportRepository;

    @Autowired
    private UserLoginRecordRepository userLoginRecordRepository;

    @Autowired
    private UserInfoRepository userInfoRepository;

    /**
     * 登陆数据统计，生成日报表
     */
    public void loginStatistic() throws Exception {

        //1、分页查询登陆记录表
        Date startDate = DateUtil.formatToDate(new Date(), DatePatternConstant.DATE_PATTERN);
        startDate = DateUtil.plusDays(startDate, -1);
        Date endDate = DateUtil.formatToDate(new Date(), DatePatternConstant.DATE_PATTERN);
        for(int pageNum=1; ; pageNum++){
            List<UserLoginRecordDO> recordDOList = userLoginRecordRepository.pageQuery(startDate, endDate, pageNum,
                    CommonConstant.DEFAULT_PAGE_SIZE);
            List<UserLoginRecordBO> recordBOList = UserLoginRecordConvert.getBOsByDOs(recordDOList);
            if(CollectionUtils.isEmpty(recordBOList)){
                break;
            }

            //数据分组
            Map<String, List<UserLoginRecordBO>> group = groupLoginRecordDataByUserNo(recordBOList);
            //批量处理
            for (Map.Entry<String, List<UserLoginRecordBO>> entry : group.entrySet()) {

                //设置每组数据值
                List<UserLoginRecordBO> recordBOS = entry.getValue();
                UserLoginReportDO reportDO = new UserLoginReportDO();
                reportDO.setUserNo(entry.getKey());
                reportDO.setLoginDate(startDate);
                reportDO.setLoginCount(recordBOS.size());
                UserInfoDO userInfoDO = userInfoRepository.queryByUserNo(reportDO.getUserNo());
                reportDO.setNickName(userInfoDO.getNickName());
                reportDO.setCreatedBy(CommonConstant.SYSTEM);
                reportDO.setUpdatedBy(CommonConstant.SYSTEM);

                System.out.print("<<<<<<<<定时任务执行结果:" + reportDO);
                //新增日报表数据，按用户
                //userLoginReportRepository.insert(reportDO);
            }
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

                if (resultMap.containsKey(recordBO.getUserNo())) {
                    //map中异常批次已存在，将该数据存放到同一个key（key存放的是异常批次）的map中
                    resultMap.get(recordBO.getUserNo()).add(recordBO);
                } else {
                    //map中不存在，新建key，用来存放数据
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
