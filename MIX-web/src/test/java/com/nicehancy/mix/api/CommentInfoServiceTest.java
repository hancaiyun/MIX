package com.nicehancy.mix.api;

import com.nicehancy.mix.base.BaseSpringTest;
import com.nicehancy.mix.common.Result;
import com.nicehancy.mix.service.api.model.request.note.CommentInfoPageQueryReqDTO;
import com.nicehancy.mix.service.api.model.result.CommentInfoDTO;
import com.nicehancy.mix.service.api.model.result.base.BasePageQueryResDTO;
import com.nicehancy.mix.service.api.note.CommentInfoService;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.UUID;

/**
 * <p>
 *     评论接口测试类
 * <p/>
 *
 * @author hancaiyun
 * @since 2020/11/10 15:40
 **/
@Slf4j
public class CommentInfoServiceTest extends BaseSpringTest {

    @Autowired
    private CommentInfoService commentInfoService;

    @Test
    public void test(){

        CommentInfoPageQueryReqDTO reqDTO = new CommentInfoPageQueryReqDTO();
        reqDTO.setSubjectId(6000000608433919L);
        reqDTO.setSubjectType("NOTE");
        reqDTO.setCurrentPage(1);
        reqDTO.setPageSize(10);
        reqDTO.setRequestSystem("MIX");
        reqDTO.setTraceLogId(UUID.randomUUID().toString());
        Result<BasePageQueryResDTO<CommentInfoDTO>> result  = commentInfoService.pageQuery(reqDTO);

        log.info("{}", result);
    }
}
