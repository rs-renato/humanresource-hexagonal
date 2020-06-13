package br.com.hrs.api.config.init;

import br.com.hrs.api.config.HrsApiInfraConfiguration;
import br.com.hrs.api.config.HrsApiJwtSecurityConfiguration;
import br.com.hrs.api.config.HrsApiSwaggerConfiguration;
import br.com.hrs.api.config.HrsApiWebMvcConfiguration;
import br.gov.go.sefaz.javaee.security.jwt.rest.config.init.JwtRestSecurityInitializer;
import org.apache.commons.lang3.ArrayUtils;
import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;

public class HrsApiApplicationInitializer extends AbstractAnnotationConfigDispatcherServletInitializer  {
	
	@Override
	protected Class<?>[] getRootConfigClasses() {
		
		Class<?>[] rootConfigClasses = JwtRestSecurityInitializer.getRootConfigClasses();
		
		/*** Carrega classes de configuração da aplicação ***/
		rootConfigClasses = ArrayUtils.add(rootConfigClasses, HrsApiInfraConfiguration.class);
		rootConfigClasses = ArrayUtils.add(rootConfigClasses, HrsApiJwtSecurityConfiguration.class);
		
		return rootConfigClasses;
	}

	@Override
	protected Class<?>[] getServletConfigClasses() {
		return new Class<?>[]{HrsApiWebMvcConfiguration.class, HrsApiSwaggerConfiguration.class};
	}

	@Override
	protected String[] getServletMappings() {
		return new String[]{"/"};
	}
	
	@Override
	public void onStartup(ServletContext servletContext) throws ServletException {
		
		//Open Entity Manager in View
//		OpenEntityManagerInViewSupport.addFilter(servletContext, HrsApiConfigurationParameters.PACKAGE_RESOURCE);
				
		super.onStartup(servletContext);
	}
}