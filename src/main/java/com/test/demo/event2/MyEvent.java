package com.test.demo.event2;


import com.test.demo.event.dto.UserRegister;
import lombok.Getter;
import org.springframework.context.ApplicationEvent;

/**
 * @author 金🗡
 * @date 2020/4/9 11:05
 * @description:
 */
public class MyEvent extends ApplicationEvent {

    @Getter
    UserRegister userRegister;

    /**
     * Create a new ApplicationEvent.
     *
     * @param source the object on which the event initially occurred (never {@code null})
     */
    public MyEvent(Object source, UserRegister userRegister) {
        super(source);
        this.userRegister = userRegister;
    }
}
