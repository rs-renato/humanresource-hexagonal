package br.com.hrs.core;


import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;


@Configuration
@ComponentScan({
        "br.com.hrs.core.port",
        "br.com.hrs.core.business.service",
})
public class DevEnvironmentConfiguration {
    // Development Build : Unit Test  -> Core <-  Mocks
}