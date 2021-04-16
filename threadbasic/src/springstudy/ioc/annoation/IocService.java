package springstudy.ioc.annoation;

import java.lang.annotation.*;

@Documented
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
public @interface IocService {
    String name() default "";
}
