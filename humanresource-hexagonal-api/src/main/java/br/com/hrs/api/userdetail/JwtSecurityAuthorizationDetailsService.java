package br.com.hrs.api.userdetail;

import br.com.hrs.api.support.HrsApiPropertiesSupport;
import br.gov.go.sefaz.javaee.commons.resource.jwt.v1.JwtDetails;
import br.gov.go.sefaz.javaee.security.jwt.config.JwtClaimsConfigurationParameters;
import br.gov.go.sefaz.javaee.security.jwt.dto.JwtApp;
import br.gov.go.sefaz.javaee.security.jwt.service.JwtAuthorizationDetailsService;
import br.gov.go.sefaz.javaee.security.jwt.user.JwtAuthenticatedUser;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class JwtSecurityAuthorizationDetailsService implements JwtAuthorizationDetailsService{
	
	private static final Logger logger = LogManager.getLogger(JwtSecurityAuthorizationDetailsService.class);
	
	private HrsApiPropertiesSupport hrsApiPropertiesSupport;
	
	public JwtSecurityAuthorizationDetailsService(HrsApiPropertiesSupport hrsApiPropertiesSupport) {
		this.hrsApiPropertiesSupport = hrsApiPropertiesSupport;
	}
	
	
	@Override
	public JwtAuthenticatedUser authorize(JwtAuthenticatedUser jwtAuthenticatedUser) {
		
		String username = jwtAuthenticatedUser.getUsername();
		JwtDetails jwtDetails = jwtAuthenticatedUser.getJwtDetails();
		String resourceApp = jwtDetails.getResourceApp();
		
		logger.debug(String.format("Authorization user '%s' to application '%s'", username, resourceApp));
		
		String authoritiesKey = String.format("hrs.api.security.jwt.credentials[%s].app[%s].authorities", username, resourceApp);
		
		// Loads authorization data 
		String[] appAuthoritiesArray =  hrsApiPropertiesSupport.getProperty(authoritiesKey, String[].class);
		
		List<String> appAuthorities = new ArrayList<>();
		
		if (appAuthoritiesArray != null) {
			// Builds JWT authorities
			for (String authority : appAuthoritiesArray) {
				appAuthorities.add( JwtClaimsConfigurationParameters.JWT_AUTHORITY_PREFIX + authority);
			}
		}
		
		JwtApp jwtApp = new JwtApp(resourceApp, appAuthorities, null);
		// The password is not necessary after authentication process
		return new JwtAuthenticatedUser(username, "", jwtApp, jwtDetails);
	}
}
