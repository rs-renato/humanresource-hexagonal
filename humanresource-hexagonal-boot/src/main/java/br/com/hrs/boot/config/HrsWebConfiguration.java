package br.com.hrs.boot.config;

import br.com.hrs.api.config.HrsApiJwtSecurityConfiguration;
import br.com.hrs.api.config.HrsApiSwaggerConfiguration;
import br.com.hrs.api.config.HrsApiWebMvcConfiguration;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.web.servlet.config.annotation.DefaultServletHandlerConfigurer;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
@Import({
        HrsApiJwtSecurityConfiguration.class,
        HrsApiWebMvcConfiguration.class,
        HrsApiSwaggerConfiguration.class})
public class HrsWebConfiguration implements WebMvcConfigurer{

    @Override
    public void configureDefaultServletHandling(DefaultServletHandlerConfigurer configurer) {
        configurer.enable();
    }
}