package com.nicehancy.MIX.web.controller;

import com.nicehancy.MIX.common.constant.DatePatternConstant;
import com.nicehancy.MIX.common.utils.DateUtil;
import com.nicehancy.MIX.common.utils.UUIDUtil;
import com.nicehancy.MIX.service.api.file.FileManagementService;
import com.nicehancy.MIX.web.controller.base.BaseController;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.MDC;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;
import java.util.Date;
import java.util.UUID;

/**
 * 文件服务controller
 * <p>
 * <p/>
 *
 * @author hancaiyun
 * @since 2019/12/5 14:39
 **/
@Slf4j
@Controller
public class FileController extends BaseController {

    /**
     * 文件上传
     * @return     文件路径
     */
    @RequestMapping(value = "/api/upload")
    @ResponseBody
    public ModelMap upload(@RequestParam("file") MultipartFile file, HttpServletRequest request) throws IOException {

        String traceLogId = UUID.randomUUID().toString();
        MDC.put("TRACE_LOG_ID", traceLogId);
        log.info("FileController upload request: traceLogId={}", traceLogId);

        String oldName = file.getOriginalFilename();
        //服务器文件目录
        String path = "C://file/";
        assert oldName != null;
        String fileName = changeName(oldName);
        String filePath = path + fileName;

        log.info("oldName={}, path={}, fileName={}", oldName, path, fileName);
        File localFile = new File(filePath);
        if (!localFile.exists()) {
            localFile.mkdirs();
        }
        file.transferTo(localFile);
        //返回文件名
        return this.processSuccessJSON(fileName, "success");
    }

    /**
     * 更改文件名
     * @param oldName               原名字
     * @return                      文件名，格式 随机+_yyyy-MM-dd HH:mm:ss+.+文件后缀
     */
    private static String changeName(String oldName) {
        String newName = oldName.substring(oldName.indexOf('.'));
        //前缀
        String prefix = DateUtil.format(new Date(), DatePatternConstant.FULL_PATTERN) + UUIDUtil.createNoByUUId();
        newName = prefix + newName;
        return newName;
    }
}
