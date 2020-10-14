package com.nicehancy.mix.web.controller.note;

import com.nicehancy.mix.common.Result;
import com.nicehancy.mix.common.constant.CommonErrorConstant;
import com.nicehancy.mix.common.utils.FileOperateUtil;
import com.nicehancy.mix.service.api.file.FileManagementService;
import com.nicehancy.mix.service.api.model.FileDownloadInfoDTO;
import com.nicehancy.mix.service.api.model.request.file.FileDeleteRequestDTO;
import com.nicehancy.mix.service.api.model.request.file.FileQueryDetailReqDTO;
import com.nicehancy.mix.service.api.model.request.file.FileShareRequestDTO;
import com.nicehancy.mix.service.api.model.request.file.FileUploadRequestDTO;
import com.nicehancy.mix.service.api.model.request.note.FileUploadRecordPageQueryReqDTO;
import com.nicehancy.mix.service.api.model.result.FileUploadRecordDTO;
import com.nicehancy.mix.service.api.model.result.FileUploadResultDTO;
import com.nicehancy.mix.service.api.model.result.base.BasePageQueryResDTO;
import com.nicehancy.mix.web.controller.base.BaseController;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.MDC;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.UUID;

/**
 * 文件管理controller
 * <p>
 * <p/>
 *
 * @author hancaiyun
 * @since 2020/6/15 14:05
 **/
@Slf4j
@Controller
public class FileManageController extends BaseController {

    @Autowired
    private FileManagementService fileManagementService;

    /**
     * 主页面
     * @return      主页视图
     */
    @RequestMapping("/note/file/list")
    public ModelAndView accountPage(){
        return new ModelAndView("note/file/list");
    }

    /**
     * 文件表单页
     * @return      表单页视图
     */
    @RequestMapping("/note/file/fileForm")
    public ModelAndView fileForm(){
        return new ModelAndView("note/file/fileform");
    }

    /**
     * 文件上传
     * @return      上传结果
     */
    @RequestMapping("/note/file/upload")
    @ResponseBody
    public ModelMap upload(HttpServletRequest request, @RequestParam("file") MultipartFile file) {

        String traceLogId = UUID.randomUUID().toString();
        MDC.put("TRACE_LOG_ID", traceLogId);
        FileUploadRequestDTO fileUploadRequestDTO = new FileUploadRequestDTO();
        fileUploadRequestDTO.setUserNo(this.getParameters(request).get("userNo"));
        fileUploadRequestDTO.setFileData(file);
        fileUploadRequestDTO.setRequestSystem("SYSTEM");
        fileUploadRequestDTO.setTraceLogId(traceLogId);
        log.info("FileManageController upload request: fileUploadRequestDTO={}", fileUploadRequestDTO);

        Result<FileUploadResultDTO> result = fileManagementService.uploadFile(fileUploadRequestDTO);
        ModelMap modelMap;
        if(result.isSuccess()){
            modelMap = this.processSuccessJSON(result);
        }else{
            modelMap = this.processSuccessJSON(result.getErrorMsg());
        }
        log.info("FileManageController upload result={}", modelMap);

        return modelMap;
    }

    /**
     * 文件下载至本地
     */
    @RequestMapping("/note/file/download")
    @ResponseBody
    public void download(HttpServletRequest request, HttpServletResponse response) {

        String traceLogId = UUID.randomUUID().toString();
        MDC.put("TRACE_LOG_ID", traceLogId);
        FileQueryDetailReqDTO fileQueryDetailReqDTO = new FileQueryDetailReqDTO();
        fileQueryDetailReqDTO.setFileId(this.getParameters(request).get("fileId"));
        fileQueryDetailReqDTO.setRequestSystem("SYSTEM");
        fileQueryDetailReqDTO.setTraceLogId(traceLogId);
        //查询文件明细信息获取下载路径
        log.info("FileManageController download request: fileQueryDetailReqDTO={}", fileQueryDetailReqDTO);
        Result<FileUploadRecordDTO> result = fileManagementService.queryDetail(fileQueryDetailReqDTO);

        //查询失败
        if(!result.isSuccess()){
            throw new RuntimeException(result.getErrorMsg());
        //文件未找到
        } else if(null == result.getResult()){
            throw new RuntimeException(CommonErrorConstant.FILE_NOT_FOND);
        //查询成功且有文件
        } else{
            //文件下载
            FileOperateUtil.download(result.getResult().getFilePath(), response);
        }

        log.info("FileManageController download result= SUCCESS");
    }

    /**
     * 文件预览
     * 根据文件路径filePath下载文件
     */
    @RequestMapping(value = "/note/file/preview")
    public void preview(HttpServletRequest request, HttpServletResponse response){

        String traceLogId = UUID.randomUUID().toString();
        MDC.put("TRACE_LOG_ID", traceLogId);
        String filePath = this.getParameters(request).get("filePath");
        log.info("FileManageController preview request: filePath={}, traceLogId={}", filePath, traceLogId);
        if(StringUtils.isEmpty(filePath)){

        return;
    }
        //通过物理路径下载文件
        String fileName = filePath.substring(filePath.lastIndexOf("/"));
        File file = new File(filePath);
        downFile(response, file, fileName);
    }

    /**
     * 文件下载逻辑
     * @param response          返回http
     * @param file              文件路径
     * @param fileName          文件名
     */
    public void downFile(HttpServletResponse response,File file,String fileName){

        boolean bo=file.exists();

        if (bo) {
            try {
                //把文件名按UTF-8取出并按ISO8859-1编码，保证弹出窗口中的文件名中文不乱码，中文不要太多，最多支持17个中文，因为header有150个字节限制。
                fileName = new String(fileName.getBytes(StandardCharsets.UTF_8), "ISO8859-1");
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            }
            // 设置强制下载不打开
            response.setContentType("application/force-download");
            //response.setContentType("application/octet-stream");// 告诉浏览器输出内容为流
            //Content-Disposition中指定的类型是文件的扩展名，并且弹出的下载对话框中的文件类型图片是按照文件的扩展名显示的，点保存后，
            // 文件以filename的值命名，保存类型以Content中设置的为准。注意：在设置Content-Disposition头字段之前，一定要设置Content-Type头字段。
            // 设置文件名
            response.addHeader("Content-Disposition", "attachment;fileName=" + fileName);
            String len = String.valueOf(file.length());
            //设置内容长度
            response.setHeader("Content-Length", len);
            byte[] buffer = new byte[1024];
            FileInputStream fis = null;
            BufferedInputStream bis = null;
            try {
                fis = new FileInputStream(file);
                bis = new BufferedInputStream(fis);
                OutputStream os = response.getOutputStream();
                int i = bis.read(buffer);
                while (i != -1) {
                    os.write(buffer, 0, i);
                    i = bis.read(buffer);
                }
            } catch (Exception e) {
                log.warn("文件流异常，异常信息:{}", e.getMessage());
            //关闭流（客户端手动终止文件流传输，服务端会IOException，服务端需主动关闭流）
            } finally {
                if (bis != null) {
                    try {
                        bis.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
                if (fis != null) {
                    try {
                        fis.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
    }

    /**
     * 文件删除
     * @return      删除结果
     */
    @RequestMapping("/note/file/delete")
    @ResponseBody
    public ModelMap upload(HttpServletRequest request) {

        String traceLogId = UUID.randomUUID().toString();
        MDC.put("TRACE_LOG_ID", traceLogId);
        FileDeleteRequestDTO fileDeleteRequestDTO = new FileDeleteRequestDTO();
        fileDeleteRequestDTO.setOperator(this.getParameters(request).get("userNo"));
        fileDeleteRequestDTO.setFileId(this.getParameters(request).get("fileId"));
        fileDeleteRequestDTO.setRequestSystem("SYSTEM");
        fileDeleteRequestDTO.setTraceLogId(traceLogId);
        log.info("FileManageController delete request: fileUploadRequestDTO={}", fileDeleteRequestDTO);

        Result<Boolean> result = fileManagementService.deleteFile(fileDeleteRequestDTO);
        ModelMap modelMap;
        if(result.isSuccess()){
            modelMap = this.processSuccessJSON(result);
        }else{
            modelMap = this.processSuccessJSON(result.getErrorMsg());
        }
        log.info("FileManageController delete result={}", modelMap);

        return modelMap;
    }

    /**
     * 文件删除
     * @return      删除结果
     */
    @RequestMapping("/note/file/share")
    @ResponseBody
    public ModelMap share(HttpServletRequest request) {

        String traceLogId = UUID.randomUUID().toString();
        MDC.put("TRACE_LOG_ID", traceLogId);
        FileShareRequestDTO fileShareRequestDTO = new FileShareRequestDTO();
        fileShareRequestDTO.setOperator(this.getParameters(request).get("userNo"));
        fileShareRequestDTO.setFileId(this.getParameters(request).get("fileId"));
        fileShareRequestDTO.setRequestSystem("SYSTEM");
        fileShareRequestDTO.setTraceLogId(traceLogId);
        log.info("FileManageController share request: fileShareRequestDTO={}", fileShareRequestDTO);

        Result<Boolean> result = fileManagementService.shareFile(fileShareRequestDTO);
        ModelMap modelMap;
        if(result.isSuccess()){
            modelMap = this.processSuccessJSON(result);
        }else{
            modelMap = this.processSuccessJSON(result.getErrorMsg());
        }
        log.info("FileManageController share result={}", modelMap);

        return modelMap;
    }

    /**
     * 分页查询
     * @return      分页查询结果列表
     */
    @RequestMapping("/note/file/pageQuery")
    @ResponseBody
    public ModelMap pageQuery(HttpServletRequest request){
        String traceLogId = UUID.randomUUID().toString();
        MDC.put("TRACE_LOG_ID", traceLogId);
        FileUploadRecordPageQueryReqDTO reqDTO = new FileUploadRecordPageQueryReqDTO();
        reqDTO.setUserNo(this.getParameters(request).get("userNo"));
        reqDTO.setFileId(this.getParameters(request).get("fileId"));
        reqDTO.setFileName(this.getParameters(request).get("fileName"));
        reqDTO.setFileType(this.getParameters(request).get("fileType"));
        reqDTO.setCurrentPage(Integer.valueOf(this.getParameters(request).get("page")));
        reqDTO.setPageSize(Integer.valueOf(this.getParameters(request).get("limit")));
        reqDTO.setRequestSystem("SYSTEM");
        reqDTO.setTraceLogId(traceLogId);
        log.info("FileManageController pageQuery request PARAM: reqDTO={}", reqDTO);
        Result<BasePageQueryResDTO<FileUploadRecordDTO>> result =  fileManagementService.pageQuery(reqDTO);
        ModelMap modelMap;
        if(result.isSuccess()){
            if(!CollectionUtils.isEmpty(result.getResult().getPageResult())) {
                modelMap = this.processSuccessJSON(result.getResult().getPageResult(), result.getResult().getCount());
            } else {
                modelMap = this.processSuccessJSON("查无数据");
            }
        }else{
            modelMap = this.processSuccessJSON(result.getErrorMsg());
        }
        log.info("FileManageController pageQuery result modelMap={}", modelMap);
        return modelMap;
    }
}
