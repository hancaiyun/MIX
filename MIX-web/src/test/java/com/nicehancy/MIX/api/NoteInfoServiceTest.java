package com.nicehancy.MIX.api;

import com.nicehancy.MIX.base.BaseSpringTest;
import com.nicehancy.MIX.common.utils.UUIDUtil;
import com.nicehancy.MIX.service.api.model.request.note.DirectoryQueryReqDTO;
import com.nicehancy.MIX.service.api.note.NoteInfoService;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * 笔记管理接口测试类
 * <p>
 * <p/>
 *
 * @author hancaiyun
 * @since 2020/1/3 11:09
 **/
@Slf4j
public class NoteInfoServiceTest extends BaseSpringTest {

    @Autowired
    private NoteInfoService noteInfoService;

    /**
     * 目录查询测试
     */
    @Test
    public void queryDirectoryTest(){

        DirectoryQueryReqDTO reqDTO = new DirectoryQueryReqDTO();
        reqDTO.setUserNo("19921577717");
        //reqDTO.setPrimaryDirectory("随记");
        reqDTO.setRequestSystem("CY");
        reqDTO.setTraceLogId(UUIDUtil.createNoByUUId());
        noteInfoService.queryDirectory(reqDTO);
    }
}
