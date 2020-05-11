package com.test.demo.nospring;

import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonAnySetter;
import com.fasterxml.jackson.annotation.JsonSetter;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.Data;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.Map;

/**
 * @author 金🗡
 * @date 2020/4/24 16:08
 * @description: springMVC  @JsonAnyGetter @JsonAnySetter 注解 会转换参数，
 * 改变数据格式/属性
 */
@Data
public class JackSonAnnotation {


    private String username;
    private String password;
    private Integer age;
    private Map<String, Object> map = new HashMap<>();

    //所有get方法都变成属性
    @JsonAnyGetter
    public Map<String, Object> testGet() {
        return map;
    }

    /**
     * 没有匹配上的反序列化属性，放到这里
     *
     * @param key
     * @param value
     */
    @JsonAnySetter
    public void setOther(String key, Object value) {
        this.map.put(key, value);
    }


    public static void main(String[] args) throws JsonProcessingException {

        JackSonAnnotation user = new JackSonAnnotation();
        user.setUsername("wkw");
        user.setPassword("123");
        Map<String, Object> map2 = user.testGet();
        map2.put("test1", "testOne");
        map2.put("test2", "testTwo");
        ObjectMapper objectMapper = new ObjectMapper();
        String value = objectMapper.writeValueAsString(user);
        System.out.println(value);


        String message = "{\"username\":\"wkw\",\"password\":\"123\"," +
                "\"age\":null," +
                "\"map\":{\"test2\":\"testTwo\",\"test1\":\"testOne\"}," +
                "\"test2\":\"testTwo\",\"test1\":\"testOne\"}";

        JackSonAnnotation jackSonAnnotation = objectMapper.readValue(message, JackSonAnnotation.class);
        System.out.println(jackSonAnnotation);


        System.out.println(LocalDate.now().format(DateTimeFormatter.ofPattern("yyyy.MM.dd")));
    }

}
