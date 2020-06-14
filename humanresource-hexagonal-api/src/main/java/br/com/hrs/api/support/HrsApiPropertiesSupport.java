package br.com.hrs.api.support;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.annotation.PropertySources;
import org.springframework.core.env.Environment;

@Configuration
@PropertySources({
	@PropertySource(value="classpath:/br/com/hrs/hrs-api-rest-documentation.properties", encoding = "UTF-8"),
})
public class HrsApiPropertiesSupport {
	
	private Environment environment;
	
	public HrsApiPropertiesSupport(Environment environment) {
		this.environment = environment;
	}

	public String getProperty(String key){
		return environment.getProperty(key);
	}
	
	public <T> T getProperty(String key, Class<T> targetType){
		return environment.getProperty(key, targetType);
	}
}
