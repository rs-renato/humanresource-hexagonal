package br.com.hrs.api.support;

import br.com.hrs.api.exception.ResourceNotFoundException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Collection;

public class AssertionSupport {
	
	private static Logger logger = LogManager.getLogger(AssertionSupport.class);

	private AssertionSupport() {
	}

	public static void assertResourceFound(Object object, String message) {
		if (object == null) {
			logger.debug("Resource is null, ResourceNotFoundException will be throw");
			throw new ResourceNotFoundException(message);
		}else if (object instanceof Collection && ((Collection<?>)object).isEmpty()) {
			logger.debug("Resource is an empty collection, ResourceNotFoundException will be throw");
			throw new ResourceNotFoundException(message);
		}else if(object instanceof Object[] && ((Object[])object).length == 0 ) {
			logger.debug("Resource is an empty array, ResourceNotFoundException will be throw");
			throw new ResourceNotFoundException(message);
		}
	}
}
