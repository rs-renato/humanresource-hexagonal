package br.com.hrs.api.config;

import br.com.hrs.api.support.HrsApiPropertiesSupport;
import br.com.hrs.api.support.PatchSupport;
import br.com.hrs.api.support.patch.JsonMergePatchHttpMessagemConverter;
import br.com.hrs.api.support.patch.JsonPatchHttpMessagemConverter;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr353.JSR353Module;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;
import org.springframework.core.env.Environment;
import org.springframework.data.web.config.EnableSpringDataWebSupport;
import org.springframework.data.web.config.PageableHandlerMethodArgumentResolverCustomizer;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.Jackson2ObjectMapperBuilder;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.http.converter.xml.MappingJackson2XmlHttpMessageConverter;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.validation.beanvalidation.LocalValidatorFactoryBean;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.method.HandlerTypePredicate;
import org.springframework.web.servlet.config.annotation.ContentNegotiationConfigurer;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.PathMatchConfigurer;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import javax.validation.Validator;
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
		HrsApiConfigurationParameters.PACKAGE_MAPPER,
		HrsApiConfigurationParameters.PACKAGE_ERROR_HANDLER})
public class HrsApiWebMvcConfiguration implements WebMvcConfigurer {

//	@Override
//	public void addArgumentResolvers(List<HandlerMethodArgumentResolver> resolvers) {
//		PageableHandlerMethodArgumentResolver resolver = new PageableHandlerMethodArgumentResolver();
//		resolver.setOneIndexedParameters(true);
//		resolvers.add(resolver);
//	}

	@Override
	public void configurePathMatch(PathMatchConfigurer configurer) {
		configurer.addPathPrefix("api", HandlerTypePredicate.forAnnotation(RestController.class));
	}

	@Override
	public void configureContentNegotiation(ContentNegotiationConfigurer configurer) {
		configurer
				.favorParameter(true)
				.parameterName("mediaType")
				.ignoreAcceptHeader(false)
				.useRegisteredExtensionsOnly(false)
				.defaultContentType(MediaType.APPLICATION_JSON)
				.mediaType("xml", MediaType.APPLICATION_XML)
				.mediaType("json", MediaType.APPLICATION_JSON);
	}

	@Bean
	public HrsApiPropertiesSupport hrsApiPropertiesSupport(Environment environment) {
		return new HrsApiPropertiesSupport(environment);
	}

	@Bean
	public MappingJackson2XmlHttpMessageConverter mappingJackson2XmlHttpMessageConverter() {
		Jackson2ObjectMapperBuilder builder = Jackson2ObjectMapperBuilder.xml();
		builder.indentOutput(true);
		return new MappingJackson2XmlHttpMessageConverter(builder.build());
	}

	@Bean
	public MappingJackson2HttpMessageConverter mappingJackson2HttpMessageConverter() {
		return new MappingJackson2HttpMessageConverter(objectMapper());
	}

	@Bean
	public PageableHandlerMethodArgumentResolverCustomizer customize() {
		return p -> p.setOneIndexedParameters(true);
	}

	@Bean
	public ObjectMapper objectMapper() {
		// creating a new objectMapper instance that has common configuration

		ObjectMapper mapper = new ObjectMapper();

		// does not fails if empty
		mapper.configure(SerializationFeature.FAIL_ON_EMPTY_BEANS, false);
		// does not fails if unknown properties
		mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
		// serialize on UperCamelCase strategy
		mapper.setPropertyNamingStrategy(PropertyNamingStrategy.UPPER_CAMEL_CASE);
		// serialize only non null and non empty
		mapper.setSerializationInclusion(JsonInclude.Include.NON_NULL);
		mapper.setSerializationInclusion(JsonInclude.Include.NON_EMPTY);
		mapper.setTimeZone(TimeZone.getDefault());
		mapper.setDateFormat(new SimpleDateFormat("yyyy-MM-dd"));

		mapper.registerModule(new JSR353Module());
		return mapper;
	}

	@Override
	public void configureMessageConverters(List<HttpMessageConverter<?>> converters) {
		converters.add(new JsonMergePatchHttpMessagemConverter());
		converters.add(new JsonPatchHttpMessagemConverter());
		converters.add(mappingJackson2HttpMessageConverter());
		converters.add(mappingJackson2XmlHttpMessageConverter());
	}

	@Bean
	public MessageSource messageSource() {
		ReloadableResourceBundleMessageSource messageSource = new ReloadableResourceBundleMessageSource();
		messageSource.setBasename("classpath:/ValidationMessages");
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
	public PatchSupport patchSupport(ObjectMapper objectMapper, Validator validator){
		return new PatchSupport(objectMapper, validator);
	}
}