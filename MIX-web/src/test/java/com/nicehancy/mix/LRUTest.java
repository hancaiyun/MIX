package com.nicehancy.mix;

import com.nicehancy.mix.base.BaseSpringTest;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.redisson.cache.LRUCacheMap;

import java.util.LinkedList;

/**
 * <p>
 *     LRU算法实现
 *     Least Recently Used 最近最少使用
 * <p/>
 *
 * @author hancaiyun
 * @since 2020/11/26 17:14
 **/
@Slf4j
public class LRUTest extends BaseSpringTest {

    private int cap = 0;
    private final LinkedList<User> link = new LinkedList<>();
    LRUTest(int capacity){
        this.cap=capacity;
    }

    public LRUTest(){
    }

    public User get(String user){
        User u = null;
        for(User tmp:link){
            u=tmp;
            if(tmp.getUser().equals(user)){
                //-------将数据移到头部----------
                link.remove(tmp);
                link.addFirst(tmp);
                //------------------------------
            }
        }
        return u;
    }

    public void put(User u){
        //如果达到容量则将末尾数据删除（淘汰最少使用）
        if(link.size()==cap){
            link.removeLast();
        }
        link.addFirst(u);//头部插入数据
    }

    @Test
    public void test(){
        // 测试
        LRUTest lru = new LRUTest(3);
        lru.put(new User("cocky","5"));
        lru.put(new User("coco","8"));
        lru.put(new User("ricky","4"));
        lru.put(new User("vicky","3"));
        lru.get("coco");
        System.out.println(lru.toString());

    }

    @Override
    public String toString() {
        return link.toString();
    }

    //---------------------实体类----------------------------
    static class User{
        private String user;
        private String age;


        public User(String user, String age) {
            super();
            this.user = user;
            this.age = age;
        }
        public String getUser() {
            return user;
        }
        public void setUser(String user) {
            this.user = user;
        }
        public String getAge() {
            return age;
        }
        public void setAge(String age) {
            this.age = age;
        }
        @Override
        public String toString() {
            return "User [user=" + user + ", age=" + age + "]";
        }

    }
}
