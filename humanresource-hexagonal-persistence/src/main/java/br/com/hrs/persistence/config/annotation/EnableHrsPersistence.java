package br.com.hrs.persistence.config.annotation;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

import java.lang.annotation.*;

@Configuration
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
@Inherited
@Import(HrsPersistenceConfigurationSelector.class)
public @interface EnableHrsPersistence {
    PersistenceType type() default PersistenceType.DATASOURCE;
}