package br.com.hrs.api.config;

import br.com.hrs.api.support.HrsApiPropertiesSupport;
import br.com.hrs.core.config.annotation.EnableHrsCore;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import springfox.documentation.swagger2.mappers.ModelMapper;
import springfox.documentation.swagger2.mappers.ModelMapperImpl;

@Configuration
@EnableHrsCore(loadMockRepository = true)
@ComponentScan(basePackages ={HrsApiConfigurationParameters.PACKAGE_SUPPORT})
public class HrsApiInfraConfiguration {

	@Bean
	public HrsApiPropertiesSupport hrsApiPropertiesSupport(Environment environment) {
		return new HrsApiPropertiesSupport(environment);
	}

	@Bean
	public ModelMapper modelMapper() {
		return new ModelMapperImpl();
	}
}
