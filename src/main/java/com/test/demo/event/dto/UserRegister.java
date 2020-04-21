package com.test.demo.event.dto;

import lombok.Builder;
import lombok.Data;

/**
 * @author 金🗡
 * @date 2020/4/7 12:02
 * @description:
 */
@Data
@Builder
public class UserRegister {
    private String userName;
}
