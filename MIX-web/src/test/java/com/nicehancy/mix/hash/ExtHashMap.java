package com.nicehancy.mix.hash;

import lombok.extern.slf4j.Slf4j;
import java.util.LinkedList;

/**
 * HashMap
 * <p>
 * <p/>
 *
 * @author hancaiyun
 * @since 2019/12/24 16:19
 **/
@Slf4j
public class ExtHashMap {

    LinkedList[] tables = new LinkedList[990];
    private int size;

    public void put(Object key, Object value) {
        // 创建ExtEntry对象
        ExtEntry extEntry = new ExtEntry(key, value);
        // 使用hashCode()取余,获取索引位置
        int hash = key.hashCode() % tables.length;
        // 防止hash有负数
        hash = hash < 0 ? -hash : hash;
        // 获取数组的hash元素
        LinkedList mapLinkedList = tables[hash];
        if (mapLinkedList == null) {
            // 如果没有LinkedList
            LinkedList linkedList = new LinkedList();
            tables[hash] = linkedList;
            linkedList.add(extEntry);
        } else {
            // 判断Key是否已经存在
            Object existEntry = get(key);
            if (existEntry == null) {
                // 如果existEntry不存在直接往链表后面插入
                mapLinkedList.add(extEntry);
            } else {

            }

        }
    }

    public Object get(Object key) {
        // 使用hashCode()取余,获取索引位置
        int hash = key.hashCode() % tables.length;
        // 防止hash有负数
        hash = hash < 0 ? -hash : hash;
        LinkedList linkedList = tables[key.hashCode() % tables.length];
        for (int i = 0; i < linkedList.size(); i++) {
            ExtEntry extEntry = (ExtEntry) linkedList.get(i);
            if (extEntry.key.equals(key)) {
                return extEntry.value;
            }
        }
        return null;
    }

}

class ExtEntry {

    Object key;
    Object value;

    public ExtEntry(Object key, Object value) {
        super();
        this.key = key;
        this.value = value;
    }

    public Object getKey() {
        return key;
    }

    public void setKey(Object key) {
        this.key = key;
    }

    public Object getValue() {
        return value;
    }

    public void setValue(Object value) {
        this.value = value;
    }

}