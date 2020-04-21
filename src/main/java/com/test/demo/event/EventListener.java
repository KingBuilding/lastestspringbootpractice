package com.test.demo.event;

import org.springframework.context.ApplicationListener;

/**
 * @author 金🗡
 * @date 2020/4/7 12:06
 * @description:
 */
//@Component
public class EventListener implements ApplicationListener<UserRegisterEvent> {


    @Override
    public void onApplicationEvent(UserRegisterEvent event) {
        //发邮件
        System.out.println("正在发送邮件至： " + event.getUser().getUserName());

    }
}
