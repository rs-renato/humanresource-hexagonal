package br.com.hrs.core;


import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;


@Configuration
@ComponentScan({
        "br.com.hrs.core.repository.impl",
        "br.com.hrs.core.service.impl",
})
public class DevEnvironmentConfiguration {
    // Development Build : Unit Test  -> Core <-  Mocks
}