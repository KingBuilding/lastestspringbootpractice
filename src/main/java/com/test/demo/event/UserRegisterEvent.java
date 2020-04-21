package com.test.demo.event;


import com.test.demo.event.dto.UserRegister;
import lombok.Getter;
import org.springframework.context.ApplicationEvent;

/**
 * @author 金🗡
 * @date 2020/4/7 12:04
 * @description:
 */
public class UserRegisterEvent extends ApplicationEvent {

    @Getter
    private UserRegister user;

    /**
     * Create a new ApplicationEvent.
     *
     * @param source the object on which the event initially occurred (never {@code null})
     */
    public UserRegisterEvent(UserRegister source) {
        super(source);
        this.user = source;
    }

}
