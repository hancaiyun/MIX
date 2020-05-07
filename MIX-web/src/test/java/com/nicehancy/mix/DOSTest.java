package com.nicehancy.mix;

import com.nicehancy.mix.base.BaseSpringTest;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * DOS攻击测试
 * <p>
 * <p/>
 *
 * @author hancaiyun
 * @since 2019/12/24 11:27
 **/
@Slf4j
public class DOSTest extends BaseSpringTest {

    /**
     * collision碰撞攻击数据测试
     * 谨慎执行：攻击服务器CPU将被耗尽
     */
    @Test
    public void collisionTest() {

        String jsonStr = "";
        try {
            FileReader fr = new FileReader("collision.txt");//需要读取的文件路径
            BufferedReader br = new BufferedReader(fr);
            jsonStr = br.readLine();
            br.close();//关闭BufferReader流
            fr.close();     //关闭文件流
        } catch (IOException e)//捕捉异常
        {
            log.info("指定文件不存在");//处理异常
        }

        Map<String, Object> map = new HashMap<String, Object>();

        //map = JSONObject.fromObject(jsonStr);

    }
}
