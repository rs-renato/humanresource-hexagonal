package br.com.hrs.core;


import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;


@Configuration
@ComponentScan({
        "br.com.hrs.core.repository",
        "br.com.hrs.core.usecase",
        "br.com.hrs.core.validator",
})
public class HrsBuildConfiguration {
    // Development Build : Unit Test  -> Core <-  Mocks
}