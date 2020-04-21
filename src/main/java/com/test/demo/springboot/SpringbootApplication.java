package com.test.demo.springboot;

import com.test.demo.event.dto.UserRegister;
import com.test.demo.event2.MyEvent;
import com.test.demo.select.SelfSelectAnnotation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.event.EventListener;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.EnableAsync;

@SpringBootApplication
@EnableAsync
@Slf4j
@SelfSelectAnnotation
public class SpringbootApplication  {

    public static void main(String[] args) {
        SpringApplication.run(SpringbootApplication.class, args);
    }

    @Autowired
    ApplicationContext applicationContext;


    @Bean
    ApplicationRunner applicationRunner(){
        return args -> {
            js();
        };
    }
    public void js() {
        UserRegister userRegister = UserRegister.builder().userName("张三").build();
        applicationContext.publishEvent(new MyEvent(this, userRegister));
    }

    @EventListener // 注意此处
    @Async
    public void handleDemoEvent(MyEvent event) {
        log.info("发布的data为:{}", event.getUserRegister());
    }



}
