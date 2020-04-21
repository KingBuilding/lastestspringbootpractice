package com.test.demo.nospring;

import org.junit.jupiter.api.Test;

import java.lang.annotation.*;
import java.util.Arrays;

import static org.springframework.test.util.AssertionErrors.assertTrue;

/**
 * @author 金🗡
 * @date 2020/4/20 11:14
 * @description:
 */
public class AnnotationTest {

    @Retention(RetentionPolicy.RUNTIME)
    @Target(ElementType.TYPE)
    @Inherited
    public @interface IsInheritedAnnotation {
    }

    @Retention(RetentionPolicy.RUNTIME)
    @Target(ElementType.TYPE)
    public @interface NoInherritedAnnotation {
    }

    @NoInherritedAnnotation
    @IsInheritedAnnotation
    public class InheritedBase {
    }

    public class MyInheritedClass extends InheritedBase {
    }

    @NoInherritedAnnotation
    @IsInheritedAnnotation
    public interface IInheritedInterface {
    }

    public interface IInheritedInterfaceChild extends IInheritedInterface {
    }

    public static class MyInheritedClassUseInterface implements IInheritedInterface {
    }

    @Test
    public void testInherited() {
        {
            Annotation[] annotations = MyInheritedClass.class.getAnnotations();
            for (int i = 0; i < annotations.length; i++) {
                System.out.println("MyInheritedClass"+annotations[i].annotationType());
            }
        }
        {
            Annotation[] annotations = MyInheritedClassUseInterface.class.getAnnotations();
            for (int i = 0; i < annotations.length; i++) {
                System.out.println("MyInheritedClassUseInterface"+annotations[i].annotationType());
            }
       }
        {
            Annotation[] annotations = IInheritedInterface.class.getAnnotations();
            for (int i = 0; i < annotations.length; i++) {
                System.out.println("IInheritedInterface"+annotations[i].annotationType());
            }

        }
        {
            Annotation[] annotations = IInheritedInterfaceChild.class.getAnnotations();
            for (int i = 0; i < annotations.length; i++) {
                System.out.println("IInheritedInterfaceChild"+annotations[i].annotationType());
            }
        }
    }
}
