package br.com.hrs.api.config;

import br.com.hrs.api.support.HrsApiPropertiesSupport;
import br.com.hrs.api.userdetail.JwtSecurityAuthenticationDetailsService;
import br.com.hrs.api.userdetail.JwtSecurityAuthorizationDetailsService;
import br.gov.go.sefaz.javaee.commons.exception.SefazException;
import br.gov.go.sefaz.javaee.security.jwt.rest.config.JwtRestSecurityConfiguration;
import br.gov.go.sefaz.javaee.security.jwt.service.JwtAuthorizationDetailsService;
import br.gov.go.sefaz.javaee.security.jwt.support.RSALoaderSupport;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import java.security.PrivateKey;
import java.security.PublicKey;

@Configuration
@EnableWebSecurity
public class HrsApiJwtSecurityConfiguration extends JwtRestSecurityConfiguration {

	private HrsApiPropertiesSupport hrsApiPropertiesSupport;

	@Autowired
	public HrsApiJwtSecurityConfiguration(HrsApiPropertiesSupport hrsApiPropertiesSupport) {
		this.hrsApiPropertiesSupport = hrsApiPropertiesSupport;
	}
	
	@Override
	protected void configure(HttpSecurity httpSecurity) throws Exception {
		httpSecurity
			.csrf().disable()
			.authorizeRequests()
				// Permits login request without security context
				.regexMatchers(jwtTokenPath()).permitAll()
				.anyRequest()
					.authenticated()
			.and()
				// Filter all requests to login path
				.addFilterBefore(jwtRestLoginAuthenticationFilter(),UsernamePasswordAuthenticationFilter.class)
				// filter all requests to any other paths to validate JWT 
				.addFilterBefore(jwtRestAuthorizationFilter(), UsernamePasswordAuthenticationFilter.class)
				// Configure policy to stateless fashion
				.sessionManagement()
					.sessionCreationPolicy(SessionCreationPolicy.STATELESS)
			.and()
				.exceptionHandling()
					// Configures exception handler to unauthorized requests
					.authenticationEntryPoint(jwtRestAuthenticationExceptionHandler())
					// Configures exception handler to forbidden requests
					.accessDeniedHandler(jwtRestAuthenticationExceptionHandler());
	}
	
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.authenticationProvider(daoAuthenticationProvider());
	}
	
	@Override
	public void configure(WebSecurity webSecurity) throws Exception {
		webSecurity.ignoring()
			.antMatchers(HrsApiSwaggerConfiguration.HEALTH_IGNORING_PATHS)
			.antMatchers(HrsApiSwaggerConfiguration.SWAGGER_IGNORING_PATHS);
	}
	
	@Override
	public PrivateKey servicePrivateKey() throws SefazException{
		return RSALoaderSupport.loadPrivateKey(hrsApiPropertiesSupport.getProperty("hrs.api.security.jwt.private.key.path"));
	}
	
	@Override
	public PublicKey servicePublicKey() throws SefazException{
		return RSALoaderSupport.loadPublicKey(hrsApiPropertiesSupport.getProperty("hrs.api.security.jwt.public.key.path"));
	}
	
	
	@Bean
	public DaoAuthenticationProvider daoAuthenticationProvider(){
		DaoAuthenticationProvider authenticationProvider = new DaoAuthenticationProvider();
		authenticationProvider.setUserDetailsService(jwtSecurityAuthenticationDetailsService());
		return authenticationProvider;
	}
	
	@Bean
	public JwtSecurityAuthenticationDetailsService jwtSecurityAuthenticationDetailsService() {
		return new JwtSecurityAuthenticationDetailsService(hrsApiPropertiesSupport);
	}
	
	@Bean
	public JwtAuthorizationDetailsService jwtAuthorizationDetailsService(){
		return new JwtSecurityAuthorizationDetailsService(hrsApiPropertiesSupport);
	}
}
