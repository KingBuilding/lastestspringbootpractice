package com.test.demo.nospring;

import org.springframework.context.annotation.PropertySource;

import java.lang.annotation.*;

/**
 * @author 金🗡
 * @date 2020/4/21 11:34
 * @description:
 * @code
 * @see Repeatable
 */
//@PropertySource

@RepeatableAnnotationDemo.Person(role = "cat", desc = "猫")
@RepeatableAnnotationDemo.Person(role = "pig", desc = "猪")
@RepeatableAnnotationDemo.Person(role = "dog", desc = "狗")
public class RepeatableAnnotationDemo {


    @Repeatable(value = Persons.class)
    public @interface Person {
        String role() default "";

        String desc() default "";

    }


    @Target(ElementType.TYPE)
    @Retention(RetentionPolicy.RUNTIME)
    public @interface Persons {
        Person[] value();
    }

    public static void main(String[] args) {
        Annotation[] annotations = RepeatableAnnotationDemo.class.getAnnotations();
        System.out.println(annotations.length);
        Persons p1=(Persons) annotations[0];
        for(Person t:p1.value()){
            System.out.println(t.role());
        }


    }

}
