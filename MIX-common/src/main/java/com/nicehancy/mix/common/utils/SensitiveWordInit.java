package com.nicehancy.mix.common.utils;

import lombok.extern.slf4j.Slf4j;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.*;

/**
 * <p>
 *     敏感词初始化词库
 * <p/>
 *
 * @author hancaiyun
 * @since 2020/11/5 14:18
 **/
@Slf4j
public class SensitiveWordInit {

    /**
     * 字符编码
     */
    private static final String ENCODING = "UTF-8";

    @SuppressWarnings("rawtypes")
    public HashMap sensitiveWordMap;

    public SensitiveWordInit(){
    }

    /**
     * @author chenming
     * @date 2014年4月20日 下午2:28:32
     * @version 1.0
     */
    @SuppressWarnings("rawtypes")
    public Map initKeyWord(){
        try {
            //读取敏感词库
            Set<String> keyWordSet = readSensitiveWordFile();
            //将敏感词库加入到HashMap中
            addSensitiveWordToHashMap(keyWordSet);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return sensitiveWordMap;
    }

    /**
     * 读取敏感词库，将敏感词放入HashSet中，构建一个DFA算法模型：<br>
     * 中 = {
     *      isEnd = 0
     *      国 = {<br>
     *      	 isEnd = 1
     *           人 = {isEnd = 0
     *                民 = {isEnd = 1}
     *                }
     *           男  = {
     *           	   isEnd = 0
     *           		人 = {
     *           			 isEnd = 1
     *           			}
     *           	}
     *           }
     *      }
     *  五 = {
     *      isEnd = 0
     *      星 = {
     *      	isEnd = 0
     *      	红 = {
     *              isEnd = 0
     *              旗 = {
     *                   isEnd = 1
     *                  }
     *              }
     *      	}
     *      }
     * @author chenming
     * @date 2014年4月20日 下午3:04:20
     * @param keyWordSet  敏感词库
     */
    @SuppressWarnings({ "rawtypes", "unchecked" })
    private void addSensitiveWordToHashMap(Set<String> keyWordSet) {
        //初始化敏感词容器，减少扩容操作
        sensitiveWordMap = new HashMap(keyWordSet.size());
        String key;
        Map nowMap;
        Map<String, String> newWorMap;
        //迭代keyWordSet
        for (String s : keyWordSet) {
            //关键字
            key = s;
            nowMap = sensitiveWordMap;
            for (int i = 0; i < key.length(); i++) {
                //转换成char型
                char keyChar = key.charAt(i);
                //获取
                Object wordMap = nowMap.get(keyChar);

                //如果存在该key，直接赋值
                if (wordMap != null) {
                    nowMap = (Map) wordMap;
                } else {
                    //不存在则，则构建一个map，同时将isEnd设置为0，因为他不是最后一个
                    newWorMap = new HashMap<>(10);
                    //不是最后一个
                    newWorMap.put("isEnd", "0");
                    nowMap.put(keyChar, newWorMap);
                    nowMap = newWorMap;
                }

                if (i == key.length() - 1) {
                    //最后一个
                    nowMap.put("isEnd", "1");
                }
            }
        }
    }

    /**
     * 读取敏感词库中的内容，将内容添加到set集合中
     * @author chenming
     * @date 2014年4月20日 下午2:31:18
     */
    @SuppressWarnings("resource")
    private Set<String> readSensitiveWordFile() throws Exception{
        Set<String> set;

        //读取文件
        String filepath = System.getProperty("user.dir") + "/MIX-web/src/main/resources/SensitiveWord.txt";
        File file = new File(filepath);
        try (InputStreamReader read = new InputStreamReader(new FileInputStream(file), ENCODING)) {
            //文件流是否存在
            if (file.isFile() && file.exists()) {
                set = new HashSet<>();
                BufferedReader bufferedReader = new BufferedReader(read);
                String txt;
                //读取文件，将文件内容放入到set中
                while ((txt = bufferedReader.readLine()) != null) {
                    set.add(txt);
                }
            } else {
                //不存在抛出异常信息
                throw new Exception("敏感词库文件不存在");
            }
        } catch (Exception e) {
            log.error("敏感词库加载失败， 失败原因:", e);
            throw e;
        }
        //关闭文件流
        return set;
    }
}
