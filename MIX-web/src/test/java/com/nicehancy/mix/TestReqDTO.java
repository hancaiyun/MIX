package com.nicehancy.mix;

import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;

/**
 * 使用Externalizable实现序列化
 * 1、需要有默认的构造函数
 * 2、需要重写方法，指定需要序列化的变量（对象转换为字节序列、 字节序列转换为对象）
 * <p>
 * <p/>
 *
 * @author hancaiyun
 * @since 2019/12/30 12:26
 **/
public class TestReqDTO implements Externalizable {

    private static final long serialVersionUID = 3852901849211862746L;

    private String name;

    private Integer age;

    /**
     * 无参构造函数
     */
    public TestReqDTO(){}

    @Override
    public void writeExternal(ObjectOutput out) throws IOException {
        out.writeObject(name);
        out.writeObject(age);
    }

    @Override
    public void readExternal(ObjectInput in) throws IOException, ClassNotFoundException {
        this.name  = (String) in.readObject();
        this.age = (Integer) in.readObject();
    }
}
