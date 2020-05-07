package com.nicehancy.mix;

import java.util.ArrayList;

import java.util.LinkedList;
import java.util.Scanner;

/**
 * 排队叫号系统
 */
public class QueueNumber {
    //银行排队叫号
    public static void main(String[] args) {
        //叫号相当于排队，所以存贮在链表集合中
        //自动叫号机 普通客户
        LinkedList<Integer> list = new LinkedList<Integer>();
        //vip客服专用通道
        LinkedList<Integer> list_vip = new LinkedList<Integer>();
        //三个工作人员的工作量，查询操作会比较多，所以用ArrayList
        //三个前台工作人员
        ArrayList<Integer> list01 = new ArrayList<Integer>();
        ArrayList<Integer> list02 = new ArrayList<Integer>();
        ArrayList<Integer> list03 = new ArrayList<Integer>();
        //模拟叫号，给集合添加100个号
        int i;//普通账号从1号开始
        for (i = 1; i < 100; i++) {
            list.add(i);
        }
        int vip = 1000; //vip账号从1000开始
        boolean isNumber = true;//这是一个退出叫号系统的标记
        while (isNumber) {
            Scanner sc = new Scanner(System.in);
            System.out.println("请您叫号,0.VIP用户，1.普通用户，2.取消");
            int x = sc.nextInt();
            switch (x) {
                case 0 :
                    vip++;
                    list_vip.addLast(vip);
                    System.out.println("叫号成功（VIP），您的号码是"+vip);
                    break;
                case 1:
                    i++;
                    list.addLast(i);
                    System.out.println("叫号成功，您的号码是"+i);
                    break;
                case 2:
                    isNumber = false;
                    break;
            }
        }
        //模拟前台叫号
        boolean flag = true;
        while (flag) {
            //此按钮模拟银行柜台的操作
            System.out.println("请按下您的按钮");
            Scanner sc = new Scanner(System.in);
            int x = sc.nextInt();
            switch (x) {
            //第一个工作人员叫号,一号为vip窗口，当没有vip时，普通号可以到一号窗口办理
                case 1:
                    if(!list_vip.isEmpty()){
                        int numbervip = list_vip.removeFirst();
                        list01.add(numbervip);
                        System.out.println("请"+numbervip+"号客户上台");
                    }else{
                        int number = list.removeFirst();
                        list01.add(number);
                        System.out.println("请"+number+"号客户上台");}
                    break;
                //第二个工作人员叫号
                case 2:
                    int number2 = list.removeFirst();
                    list02.add(number2);
                    System.out.println("请"+number2+"号客户上台");
                    break;
                //第三个工作人员叫号
                case 3:
                    int number3 = list.removeFirst();
                    list03.add(number3);
                    System.out.println("请"+number3+"号客户上台");
                    break;
                //当按下某个开关的时候停止叫号
                case 0:
                    flag = false;
                    break;
            }
        }
        //查看当前排队的序号
        System.out.println(list);
        //查看每个工作人员已经完成的客户
        System.out.println(list01);
        System.out.println(list02);
        System.out.println(list03);
    }
}