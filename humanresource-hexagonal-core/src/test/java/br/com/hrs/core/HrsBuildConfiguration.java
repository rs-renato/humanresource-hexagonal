package br.com.hrs.core;


import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;


@Configuration
@ComponentScan({
        "br.com.hrs.core.repository.impl",
        "br.com.hrs.core.usecase.impl",
        "br.com.hrs.core.validations.impl",
})
public class HrsBuildConfiguration {
    // Development Build : Unit Test  -> Core <-  Mocks
}