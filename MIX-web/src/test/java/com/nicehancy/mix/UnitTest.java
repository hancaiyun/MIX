package com.nicehancy.mix;

import com.nicehancy.mix.base.BaseSpringTest;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

/**
 * 单元测试
 * <p>
 * <p/>
 *
 * @author hancaiyun
 * @since 2019/12/18 16:18
 **/
@Slf4j
public class UnitTest extends BaseSpringTest {

    @Test
    public void integerTest() {

        Integer integer = 128;//[-128,127]
        Integer integer1 = 128;
        log.info("{}", integer == integer1);
    }

    @Test
    public void boxingTest() {
        //自动拆装箱
        Integer i = 13;
        Integer j = 13;
        if (i == j) {
            log.info("{}", "两者相等");
        }
    }

    /**
     * 手动终止虚拟机的运行，finally块将不再被执行
     */
    @Test
    public void exceptionTest() {
        try {
            log.info("执行try/catch中的代码块");
            System.exit(0);
            throw new Exception("自定义异常");
        } catch (Exception e) {
            log.info("捕获异常日志打印");
        } finally {
            log.info("finally中执行的操作");
        }
    }

    /**
     * hash碰撞数据
     * 哈希算法：Times31（java）
     */
    @Test
    public void hashTest() {
        System.out.println("atatatatatatatat".hashCode());
        System.out.println("c6atatatatatatbU".hashCode());
        System.out.println("atatatatatatbUat".hashCode());
        System.out.println("c6c6c6c6c6c6bUat".hashCode());
    }

    @Test
    public void ClassTest() {

        //UnitTest.class;
        ThreadLocal local = new ThreadLocal();
        local.remove();
    }

    @Test
    public void printTest() {

        for (int i = 0; i < 4; i++) {
            //#需要打印的数量
            int count1 = 2 * i - 1;
            if (i == 0) {
                for (int j = 0; j < 7; j++) {
                    System.out.print("*");
                }
            } else {
                int count2 = (7 - count1) / 2;
                int k = count2;
                int m = count2;
                for (; k > 0; k--) {
                    System.out.print("*");
                }

                for (; count1 > 0; count1--) {
                    System.out.print("#");
                }

                for (; m > 0; m--) {
                    System.out.print("*");
                }
            }
            System.out.println();
        }
    }

    @Test
    public void splitTest(){
        String oldName = "分润配置-功能点拆分V2.3.xlsx";
        int index=oldName.indexOf(".");
        log.info("index:{}", index);
//        String[] split = oldName.split(".");

        String str = oldName.substring(oldName.lastIndexOf("."));
        log.info("{}", str);
    }
}
