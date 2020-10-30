package com.nicehancy.mix.common.utils;

import lombok.extern.slf4j.Slf4j;
import org.springframework.util.FileCopyUtils;
import org.springframework.util.StringUtils;

import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileInputStream;
import java.io.OutputStream;
import java.net.URLEncoder;

/**
 * 文件下载工具类
 * <p>
 * <p/>
 *
 * @author hancaiyun
 * @since 2020/6/17 14:31
 **/
@Slf4j
public class FileOperateUtil {

    private static final String ENCODING = "UTF-8";
    private static final String CONTENT_DISPOSITION = "Content-Disposition";
    private static final String ATTACHMENT = "attachment;filename=";
    private static final String CONTENT_TYPE = "application/octet-stream;charset=UTF-8";
    private static final String CACHE_CONTROL = "Cache-Control";
    private static final String NO_CACHE = "no-cache";
    private static final String PRAGMA = "Pragma";
    private static final String EXPIRES = "Expires";

    /**
     * 文件下载
     * @param path              文件路径
     * @param response          返回结果
     */
    public static void download(String path, HttpServletResponse response) {
        OutputStream fos = null;
        FileInputStream fis = null;
        try {
            response.setHeader(PRAGMA, NO_CACHE);
            response.setHeader(CACHE_CONTROL, NO_CACHE);
            response.setCharacterEncoding(ENCODING);
            response.setHeader(CONTENT_DISPOSITION, ATTACHMENT + URLEncoder.encode(path, ENCODING));
            response.setContentType(CONTENT_TYPE);
            response.setDateHeader(EXPIRES, 0);
            fos = response.getOutputStream();
            File file = new File(path);
            if (file.exists()) {
                fis = new FileInputStream(file);
                FileCopyUtils.copy(fis, fos);
            }
            assert fis != null;
            fis.close();
            fos.flush();
            fos.close();
        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            try {
                if(fis!=null){
                    fis.close();
                }
                if (fos!=null){
                    fos.close();
                }
            }catch (Exception e){
                log.error(e.getMessage());
            }
        }
    }

    /**
     * 删除文件
     *
     * @param pathname        文件路径名
     */
    public static void deleteFile(String pathname){
        if(StringUtils.isEmpty(pathname)){
            return;
        }
        File file = new File(pathname);
        if (file.exists()) {
            boolean isDelete = file.delete();
            if(isDelete) {
                log.info("文件{}已经被成功删除", pathname.substring(pathname.lastIndexOf("/")));
            }else{
                log.info("文件{}删除失败", pathname.substring(pathname.lastIndexOf("/")));
            }
        }
    }
}
