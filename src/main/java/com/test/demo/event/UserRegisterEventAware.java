package com.test.demo.event;


import com.test.demo.event.dto.UserRegister;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.context.ApplicationEventPublisherAware;
import org.springframework.stereotype.Component;

/**
 * @author 金🗡
 * @date 2020/4/7 11:59
 * @description:
 */

public class UserRegisterEventAware implements ApplicationEventPublisherAware {
    ApplicationEventPublisher applicationEventPublisher;


    public boolean register(UserRegister user) {

        //用户注册
        System.out.println("[service]用户[" + user + "]注册成功！");

        //消息发布
        applicationEventPublisher.publishEvent(new UserRegisterEvent(user));

        return true;
    }


    @Override
    public void setApplicationEventPublisher(ApplicationEventPublisher applicationEventPublisher) {
        this.applicationEventPublisher = applicationEventPublisher;
    }
}
