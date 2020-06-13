package br.com.hrs.api.userdetail;

import br.com.hrs.api.support.HrsApiPropertiesSupport;
import br.gov.go.sefaz.javaee.security.jwt.user.JwtAuthenticatedUser;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.util.StringUtils;

import java.text.MessageFormat;

public class JwtSecurityAuthenticationDetailsService implements UserDetailsService {

	private static final Logger logger = LogManager.getLogger(JwtSecurityAuthenticationDetailsService.class);
	
	private HrsApiPropertiesSupport hrsApiPropertiesSupport;
	
	public JwtSecurityAuthenticationDetailsService(HrsApiPropertiesSupport hrsApiPropertiesSupport) {
		this.hrsApiPropertiesSupport = hrsApiPropertiesSupport;
	}
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		logger.debug(String.format("Trying log in username '%s' in property file", username));
		
		String passwordKey = String.format("hrs.api.security.jwt.credentials[%s].password", username);
		String password = hrsApiPropertiesSupport.getProperty(passwordKey, String.class);
		
		if (StringUtils.isEmpty(username) || StringUtils.isEmpty(password) ) {
			logger.error(String.format("Credentials not satisfied. Username: %s", username));
			throw new UsernameNotFoundException(MessageFormat.format(hrsApiPropertiesSupport.getProperty("hrs.api.security.jwt.login.error", String.class), username));
		}
		// At this moment we don't have authorities yet
		return new JwtAuthenticatedUser(username, password);
	}
}
