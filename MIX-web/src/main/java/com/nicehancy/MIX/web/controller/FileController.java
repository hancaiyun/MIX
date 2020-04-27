package com.nicehancy.MIX.web.controller;

import com.nicehancy.MIX.common.constant.DatePatternConstant;
import com.nicehancy.MIX.common.utils.DateUtil;
import com.nicehancy.MIX.common.utils.UUIDUtil;
import com.nicehancy.MIX.web.controller.base.BaseController;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.MDC;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.nio.charset.StandardCharsets;
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
     *
     * @return 文件路径
     */
    @RequestMapping(value = "/file/upload")
    @ResponseBody
    public ModelMap upload(@RequestParam("file") MultipartFile file) throws IOException {

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
            boolean isCreated = localFile.mkdirs();
            if(!isCreated){
                log.error("文件目录创建失败！");
                throw new RuntimeException("文件目录创建失败！");
            }
        }
        file.transferTo(localFile);

        //封装返回报文
        ModelMap modelMap = this.processSuccessJSON(fileName, "success");
        log.info("FileController upload result={}", modelMap);

        //返回文件名
        return modelMap;
    }

    /**
     * 更改文件名
     *
     * @param oldName 原名字
     * @return 文件名，格式 yyyyMMddHHmmss+随机码.+文件后缀
     */
    private static String changeName(String oldName) {
        String newName = oldName.substring(oldName.indexOf('.'));
        //前缀
        String prefix = DateUtil.format(new Date(), DatePatternConstant.FULL_PATTERN) + UUIDUtil.createNoByUUId();
        newName = prefix + newName;
        return newName;
    }

    /**
     * 文件下载
     * 根据文件名fileName下载文件
     */
    @RequestMapping(value = "/file/download")
    public void download(HttpServletRequest request, HttpServletResponse response){

        String traceLogId = UUID.randomUUID().toString();
        MDC.put("TRACE_LOG_ID", traceLogId);
        String fileName = this.getParameters(request).get("fileName");
        log.info("FileController download request: fileName={}, traceLogId={}", fileName, traceLogId);
        if(StringUtils.isEmpty(fileName)){
            return;
        }

        //图片下载
        //通过物理路径下载文件
        String realPath = "C://file/" + fileName;
        File file = new File(realPath);
        downFile(response, file, fileName);
    }

    /**
     * 文件下载逻辑
     * @param response          返回http
     * @param file              文件路径
     * @param fileName          文件名
     */
    private void downFile(HttpServletResponse response,File file,String fileName){

        boolean bo=file.exists();

        if (bo) {
            try {
                //把文件名按UTF-8取出并按ISO8859-1编码，保证弹出窗口中的文件名中文不乱码，中文不要太多，最多支持17个中文，因为header有150个字节限制。
                fileName = new String(fileName.getBytes(StandardCharsets.UTF_8), "ISO8859-1");
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            }
            response.setContentType("application/force-download");// 设置强制下载不打开
            //response.setContentType("application/octet-stream");// 告诉浏览器输出内容为流
            //Content-Disposition中指定的类型是文件的扩展名，并且弹出的下载对话框中的文件类型图片是按照文件的扩展名显示的，点保存后，
            // 文件以filename的值命名，保存类型以Content中设置的为准。注意：在设置Content-Disposition头字段之前，一定要设置Content-Type头字段。
            response.addHeader("Content-Disposition", "attachment;fileName=" + fileName);// 设置文件名
            String len = String.valueOf(file.length());
            response.setHeader("Content-Length", len);//设置内容长度
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
                // System.out.println("success");
            } catch (Exception e) {
                e.printStackTrace();
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
}
