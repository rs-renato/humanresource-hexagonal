package br.com.hrs.api.config;

import br.gov.go.sefaz.javaee.service.rest.factory.RestObjectMapperFactory;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;
import org.springframework.data.web.config.EnableSpringDataWebSupport;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.validation.beanvalidation.LocalValidatorFactoryBean;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import java.text.SimpleDateFormat;
import java.util.List;
import java.util.TimeZone;

@Configuration
@EnableWebMvc
@EnableSpringDataWebSupport
@EnableGlobalMethodSecurity(prePostEnabled = true)
@EnableAspectJAutoProxy(proxyTargetClass=true)
@ComponentScan(basePackages ={
		HrsApiConfigurationParameters.PACKAGE_CONTROLLER,
		HrsApiConfigurationParameters.PACKAGE_ERROR_HANDLER})
public class HrsApiWebMvcConfiguration extends WebMvcConfigurerAdapter {

	
	@Bean
	public MessageSource messageSource() {
		ReloadableResourceBundleMessageSource messageSource = new ReloadableResourceBundleMessageSource();
		messageSource.setBasename("classpath:/br/com/hrs/hrs-api-rest-messages");
		messageSource.setDefaultEncoding("UTF-8");
		return messageSource;
	}
	    
	@Bean
	public LocalValidatorFactoryBean validator() {
	    LocalValidatorFactoryBean validatorFactoryBean = new LocalValidatorFactoryBean();
	    validatorFactoryBean.setValidationMessageSource(messageSource());
	    return validatorFactoryBean;
	}
	
	@Bean
	public MappingJackson2HttpMessageConverter mappingJackson2HttpMessageConverter() {
		// creating a new objectMapper instance that has common configuration
		ObjectMapper hrsServiceObjectMapper = RestObjectMapperFactory.createObjectMapper();
		hrsServiceObjectMapper.setTimeZone(TimeZone.getDefault());
		hrsServiceObjectMapper.setDateFormat(new SimpleDateFormat("yyyy-MM-dd"));
		return new MappingJackson2HttpMessageConverter(hrsServiceObjectMapper);
	}

	@Override
	public void configureMessageConverters(List<HttpMessageConverter<?>> converters) {
		converters.add(mappingJackson2HttpMessageConverter());
	}
	
	@Override
	public void addResourceHandlers(final ResourceHandlerRegistry registry) {
		// Finds swagger resources in the classpath
		registry.addResourceHandler(HrsApiSwaggerConfiguration.SWAGGER_UI_RESOURCE_HANDLER)
			.addResourceLocations(HrsApiSwaggerConfiguration.SWAGGER_UI_RESOURCE_LOCATION);

		// Finds webjars resources in the classpath
		registry.addResourceHandler(HrsApiSwaggerConfiguration.SWAGGER_WEBJARS_RESOURCE_HANDLER)
			.addResourceLocations(HrsApiSwaggerConfiguration.SWAGGER_WEBJARS_RESOURCE_LOCATION);
	}
}