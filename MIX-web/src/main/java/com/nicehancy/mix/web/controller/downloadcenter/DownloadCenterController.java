package com.nicehancy.mix.web.controller.downloadcenter;

import com.nicehancy.mix.common.Result;
import com.nicehancy.mix.common.enums.CreateResultEnum;
import com.nicehancy.mix.common.utils.FileOperateUtil;
import com.nicehancy.mix.service.api.FileDownloadInfoService;
import com.nicehancy.mix.service.api.model.FileDownloadInfoDTO;
import com.nicehancy.mix.service.api.model.request.FileDownloadInfoPageQueryReqDTO;
import com.nicehancy.mix.service.api.model.result.base.BasePageQueryResDTO;
import com.nicehancy.mix.web.controller.base.BaseController;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.MDC;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.UUID;

/**
 * 下载中心controller
 * <p>
 * <p/>
 *
 * @author hancaiyun
 * @since 2020/8/3 9:38
 **/
@Slf4j
@Controller
@RequestMapping("/downloadCenter")
public class DownloadCenterController extends BaseController {

    @Autowired
    private FileDownloadInfoService fileDownloadInfoService;

    /**
     * 下载中心主页
     * @return      主页视图
     */
    @RequestMapping("/page")
    public ModelAndView downloadCenterPage(){
        return new ModelAndView("download/download");
    }

    /**
     * 分页查询
     * @return      分页查询结果列表
     */
    @RequestMapping("/pageQuery")
    @ResponseBody
    public ModelMap pageQuery(HttpServletRequest request){
        String traceLogId = UUID.randomUUID().toString();
        MDC.put("TRACE_LOG_ID", traceLogId);
        FileDownloadInfoPageQueryReqDTO reqDTO = new FileDownloadInfoPageQueryReqDTO();
        reqDTO.setUserNo(this.getParameters(request).get("userNo"));
        reqDTO.setCurrentPage(Integer.valueOf(this.getParameters(request).get("page")));
        reqDTO.setPageSize(Integer.valueOf(this.getParameters(request).get("limit")));
        reqDTO.setRequestSystem("SYSTEM");
        reqDTO.setTraceLogId(traceLogId);
        log.info("DownloadCenterController pageQuery request PARAM: reqDTO={}", reqDTO);
        Result<BasePageQueryResDTO<FileDownloadInfoDTO>> result =  fileDownloadInfoService.pageQuery(reqDTO);
        ModelMap modelMap;
        if(result.isSuccess()){
            if(!CollectionUtils.isEmpty(result.getResult().getPageResult())) {
                //翻译
                List<FileDownloadInfoDTO> list = result.getResult().getPageResult();
                for(FileDownloadInfoDTO fileDownloadInfoDTO : list){
                    fileDownloadInfoDTO.setCreateResult(CreateResultEnum.expireOfCode(fileDownloadInfoDTO.
                            getCreateResult()).getDesc());
                }

                modelMap = this.processSuccessJSON(result.getResult().getPageResult(), result.getResult().getCount());
            } else {
                modelMap = this.processSuccessJSON("查无数据");
            }
        }else{
            modelMap = this.processSuccessJSON(result.getErrorMsg());
        }
        log.info("DownloadCenterController pageQuery result modelMap={}", modelMap);
        return modelMap;
    }

    /**
     * 文件下载至本地
     */
    @RequestMapping("/download")
    @ResponseBody
    public void download(HttpServletRequest request, HttpServletResponse response) {

        String traceLogId = UUID.randomUUID().toString();
        MDC.put("TRACE_LOG_ID", traceLogId);
        String fullFilePath = this.getParameters(request).get("fullFilePath");
        //查询文件明细信息获取下载路径
        log.info("DownloadCenterController download request: fullFilePath={}", fullFilePath);

        //文件下载
        FileOperateUtil.download(fullFilePath, response);

        log.info("DownloadCenterController download success");
    }
}
