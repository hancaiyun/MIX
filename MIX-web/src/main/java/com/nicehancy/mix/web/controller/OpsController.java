package com.nicehancy.mix.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.io.BufferedInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

/**
 * 应用属性获取
 * @author NiceH
 */
@Controller
public class OpsController {

    private String opsInfo;

    public OpsController() {

        try (BufferedInputStream inputStream = new BufferedInputStream(
                OpsController.class.getResourceAsStream("/healthcheck.html"))) {
            StringBuilder sb = new StringBuilder();
            byte[] line = new byte[2048];
            while (inputStream.read(line) != -1) {
                sb.append(new String(line));
            }
            opsInfo = sb.toString();
        } catch (FileNotFoundException e) {
            opsInfo = "ops info not exist";
        } catch (IOException e) {
            opsInfo = "ops info read error";
        }
    }

    /**
     * 应用打包相关信息
     *
     * @return project.version
     */
    @RequestMapping("/healthcheck")
    @ResponseBody
    public String healthCheck() {
        return opsInfo;
    }
}
