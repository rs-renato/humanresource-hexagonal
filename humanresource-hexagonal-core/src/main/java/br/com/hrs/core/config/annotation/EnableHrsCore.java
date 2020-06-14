package br.com.hrs.core.config.annotation;

import org.springframework.context.annotation.Import;

import java.lang.annotation.*;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
@Inherited
@Import({HrsCoreSelector.class})
public @interface EnableHrsCore {
    boolean loadValidators() default true;
    boolean loadMockRepository() default false;
}