package com.test.demo.nospring;

import com.alibaba.fastjson.JSON;
import lombok.Data;
import org.junit.jupiter.api.Test;

import java.io.*;

/**
 * @author 金🗡
 * @date 2020/4/15 20:21
 * @description:
 */
public class SerializationUtils {


    private static String FILE_NAME = "D:\\check\\temp\\1.txt";

    //序列化  写的过程
    public static void write(Serializable s) {
        try {
            ObjectOutputStream objectOutputStream = new ObjectOutputStream(new FileOutputStream(FILE_NAME));
            objectOutputStream.writeObject(s);
            objectOutputStream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    //反序列化 读的过程
    public static Object read() {
        Object obj = null;
        // 反序列化
        try {
            ObjectInput input = new ObjectInputStream(new FileInputStream(FILE_NAME));
            obj = input.readObject();
            input.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return obj;
    }

    @Test
    public void testWrite() {
        Person person = new Person();
        person.setId(1);
        person.setName("张丹");
        SerializationUtils.write(person);
    }

    @Test
    public void testRead() {
        Person p = (Person) SerializationUtils.read();
        System.out.println(p.getName());
        System.out.println(JSON.toJSONString(p));
    }

    @Data
    public static class Person implements Serializable {
        private static final long serialVersionUID = 1L;
        private int id;
        private String name;
        private String sex;


    }
}
