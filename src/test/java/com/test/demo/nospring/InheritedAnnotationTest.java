package com.test.demo.nospring;

import org.junit.jupiter.api.Test;

import java.lang.annotation.*;

/**
 * @author 金🗡
 * @date 2020/4/20 11 :14
 * @description: Inherited 注解只能是继承父类(非接口)，才能得到父类的注解
 */
public class InheritedAnnotationTest {

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
                System.out.println("MyInheritedClass" + annotations[i].annotationType());
                System.err.print("表明继承可以继承注解");
            }

        }
        {
            Annotation[] annotations = MyInheritedClassUseInterface.class.getAnnotations();
            for (int i = 0; i < annotations.length; i++) {
                System.out.println("MyInheritedClassUseInterface" + annotations[i].annotationType());
                System.err.print("表明实现可以继承注解");
            }
        }
        {
            Annotation[] annotations = IInheritedInterface.class.getAnnotations();
            for (int i = 0; i < annotations.length; i++) {
                System.out.println("IInheritedInterface" + annotations[i].annotationType());

            }

        }
        {
            Annotation[] annotations = IInheritedInterfaceChild.class.getAnnotations();
            for (int i = 0; i < annotations.length; i++) {
                System.out.println("IInheritedInterfaceChild" + annotations[i].annotationType());
            }
        }
    }
}
