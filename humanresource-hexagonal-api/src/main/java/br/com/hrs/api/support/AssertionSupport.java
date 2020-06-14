package br.com.hrs.api.support;

import br.com.hrs.api.exception.ResourceNotFoundException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.util.ObjectUtils;

public class AssertionSupport{
	
	private static Logger logger = LogManager.getLogger(AssertionSupport.class);

	public static <T> void assertResourceFound(T type, String message) {
		if (ObjectUtils.isEmpty(type)){
			logger.debug("Resource is empty or null, ResourceNotFoundException will be throw");
			throw new ResourceNotFoundException(message);
		}
	}
}
