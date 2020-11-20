package com.nicehancy.mix;

import lombok.Getter;
import lombok.Setter;

import java.io.*;

/**
 * <p>
 * <p/>
 *
 * @author hancaiyun
 * @since 2020/11/16 17:33
 **/
@Getter
@Setter
//@ToString
public class Person implements Cloneable {

    private String name;

    private String sex;

    private Student student;

    public void sayHello(){
        System.out.print("Hello");
    }

    @Override
    public Object clone() throws CloneNotSupportedException {
        // TODO Auto-generated method stub
        return super.clone();
    }

    public Object deepClone() throws Exception{
        // 序列化
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        ObjectOutputStream oos = new ObjectOutputStream(bos);

        oos.writeObject(this);

        // 反序列化
        ByteArrayInputStream bis = new ByteArrayInputStream(bos.toByteArray());
        ObjectInputStream ois = new ObjectInputStream(bis);

        return ois.readObject();
    }
}
