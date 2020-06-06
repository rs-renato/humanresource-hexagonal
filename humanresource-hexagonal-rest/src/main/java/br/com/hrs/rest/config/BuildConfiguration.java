package br.com.hrs.rest.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
public class BuildConfiguration {

//    @Import(HrsJdbcConfiguration.class)
    @ComponentScan("br.com.hrs.core.repository")
    public class PersistenceConfiguration{}

    @ComponentScan({"br.com.hrs.core.usecase", "br.com.hrs.core.validator"})
    public class CoreConfiguration{}
}