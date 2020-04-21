package com.test.demo.select;

import com.sun.org.apache.bcel.internal.generic.Select;
import org.springframework.beans.BeanUtils;
import org.springframework.context.annotation.ImportSelector;
import org.springframework.core.type.AnnotationMetadata;
import org.springframework.util.StringUtils;

import java.util.Arrays;

/**
 * @author 金🗡
 * @date 2020/4/10 15:52
 * @description:
 */
public class SelfConfigSelect implements ImportSelector {


    @Override
    public String[] selectImports(AnnotationMetadata importingClassMetadata) {
        return new String[]{"com.test.demo.event.UserRegisterEventAware"};
    }
}
