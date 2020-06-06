package br.com.hrs.rest.config;

import br.com.hrs.persistence.config.HrsJpaConfiguration;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

@Configuration
public class BuildConfiguration {

    @Import(HrsJpaConfiguration.class)
//    @ComponentScan("br.com.hrs.core.repository")
    public class PersistenceConfiguration{}

    @ComponentScan({"br.com.hrs.core.usecase", "br.com.hrs.core.validator"})
    public class CoreConfiguration{}
}