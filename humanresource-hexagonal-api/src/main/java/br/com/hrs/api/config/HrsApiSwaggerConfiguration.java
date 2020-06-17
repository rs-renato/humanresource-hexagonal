package br.com.hrs.api.config;

import br.com.hrs.api.support.HrsApiPropertiesSupport;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import springfox.bean.validators.configuration.BeanValidatorPluginsConfiguration;
import springfox.documentation.builders.ParameterBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.builders.ResponseMessageBuilder;
import springfox.documentation.schema.ModelRef;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.service.Parameter;
import springfox.documentation.service.ResponseMessage;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;
import springfox.documentation.swagger2.mappers.ModelMapper;
import springfox.documentation.swagger2.mappers.ModelMapperImpl;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static com.google.common.collect.Lists.newArrayList;

@Configuration
@EnableSwagger2
@Import(BeanValidatorPluginsConfiguration.class)
public class HrsApiSwaggerConfiguration implements WebMvcConfigurer {

	public static final String HEALTH_IGNORING_PATHS 				= "/health/**";
	public static final String[] SWAGGER_IGNORING_PATHS 			= new String[]{ "/swagger-resources/**","/swagger-ui.html", "/v2/api-docs","/webjars/**"};
	
	public static final String  SWAGGER_UI_RESOURCE_HANDLER 		= "/swagger-ui.html";
	public static final String  SWAGGER_UI_RESOURCE_LOCATION 		= "classpath:/META-INF/resources/swagger-ui.html";
	
	public static final String  SWAGGER_WEBJARS_RESOURCE_HANDLER 	= "/webjars/**";
	public static final String  SWAGGER_WEBJARS_RESOURCE_LOCATION 	= "classpath:/META-INF/resources/webjars/";

	private HrsApiPropertiesSupport hrsApiPropertiesSupport;

	@Autowired
	public HrsApiSwaggerConfiguration(HrsApiPropertiesSupport hrsApiPropertiesSupport) {
		this.hrsApiPropertiesSupport = hrsApiPropertiesSupport;
	}

	@Bean
	public ModelMapper modelMapper() {
		return new ModelMapperImpl();
	}

	@Bean
	public Docket api(List<ResponseMessage> responsesGobal, ApiInfo apiInfo) {
		
		//Global Authorization Header
        List<Parameter> headers = new ArrayList<>();
        headers.add(new ParameterBuilder()
        				.modelRef(new ModelRef("String"))
		            	.name(HrsApiConfigurationParameters.SWAGGER_DOCS_AUTHORIZATION_HEADER_NAME)
		            	.description(HrsApiConfigurationParameters.SWAGGER_DOCS_AUTHORIZATION_HEADER_DESC)
		            	.parameterType(HrsApiConfigurationParameters.SWAGGER_DOCS_AUTHORIZATION_TYPE)
		            	.build());
        
		return new Docket(DocumentationType.SWAGGER_2)
				.select()
				.apis(RequestHandlerSelectors.withClassAnnotation(RestController.class))
				.paths(PathSelectors.regex("^(\\/api\\/v[0-9]{1,2}\\/.*)$"))
				.build()
					.apiInfo(apiInfo)
					.useDefaultResponseMessages(false)
					.globalResponseMessage(RequestMethod.GET, responsesGobal)
					.globalResponseMessage(RequestMethod.PUT, responsesGobal)
					.globalResponseMessage(RequestMethod.POST, responsesGobal)
					.globalResponseMessage(RequestMethod.DELETE, responsesGobal)
					.ignoredParameterTypes(AuthenticationPrincipal.class)
					.globalOperationParameters(headers);
		
	}
	
	@Bean
	protected ArrayList<ResponseMessage> responsesGobal() {

		return newArrayList(
				new ResponseMessageBuilder()
					.code(HttpStatus.UNAUTHORIZED.value())
					.message(HttpStatus.UNAUTHORIZED.getReasonPhrase())
					.build(),
				new ResponseMessageBuilder()
					.code(HttpStatus.FORBIDDEN.value())
					.message(HttpStatus.FORBIDDEN.getReasonPhrase())
					.build(),
				new ResponseMessageBuilder()
					.code(HttpStatus.NOT_FOUND.value())
					.message(HttpStatus.NOT_FOUND.getReasonPhrase())
					.build(),
				new ResponseMessageBuilder()
					.code(HttpStatus.INTERNAL_SERVER_ERROR.value())
					.message(HttpStatus.INTERNAL_SERVER_ERROR.getReasonPhrase())
					.build()
				);
	}
	
	@Bean
	protected ApiInfo apiInfo() {
		
		Contact contact = new Contact(	hrsApiPropertiesSupport.getProperty("hrs.api.docs.contact.name"),
										hrsApiPropertiesSupport.getProperty("hrs.api.docs.contact.url"),
										hrsApiPropertiesSupport.getProperty("hrs.api.docs.contact.email"));

		return new ApiInfo(	hrsApiPropertiesSupport.getProperty("hrs.api.docs.title"),
										hrsApiPropertiesSupport.getProperty("hrs.api.docs.description"),
										hrsApiPropertiesSupport.getProperty("hrs.api.docs.version"),
										hrsApiPropertiesSupport.getProperty("hrs.api.docs.useTerms"),
										contact,
										hrsApiPropertiesSupport.getProperty("hrs.api.docs.license"), 
										hrsApiPropertiesSupport.getProperty("hrs.api.docs.licenseUrl"),
										Collections.emptyList());
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
