package br.com.hrs.boot.config;

import br.com.hrs.api.config.HrsApiJwtSecurityConfiguration;
import br.com.hrs.api.config.HrsApiSwaggerConfiguration;
import br.com.hrs.api.config.HrsApiWebMvcConfiguration;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.servlet.config.annotation.ContentNegotiationConfigurer;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.DefaultServletHandlerConfigurer;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.List;

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

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**")
                .allowedOrigins("http://localhost:4200")
                .allowedMethods("*");
    }

    //FIXME: verificar o carregamento. Esta configuração deveria vir do HrsApiWebMvcConfiguration
    @Override
    public void configureMessageConverters(List<HttpMessageConverter<?>> converters) {
        converters.add(new MappingJackson2HttpMessageConverter());
    }

    //FIXME: verificar o carregamento. Esta configuração deveria vir do HrsApiWebMvcConfiguration
    @Override
    public void configureContentNegotiation(ContentNegotiationConfigurer configurer) {
        configurer.defaultContentType(MediaType.APPLICATION_JSON);
    }
}