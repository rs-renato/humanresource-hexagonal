package br.com.hrs.core.validator.job;

import br.com.hrs.core.exception.HrsBusinessException;
import br.com.hrs.core.exception.error.Error;
import br.com.hrs.core.exception.error.FIELD;
import br.com.hrs.core.model.Job;
import br.com.hrs.core.validator.SaveValidator;
import br.com.hrs.core.validator.UpdateValidator;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.inject.Named;
import java.util.Objects;

@Named
public class MaxSalaryGreaterOrEqualThanMinSalaryJobValidator implements SaveValidator<Job>, UpdateValidator<Job> {

	private static Logger logger = LogManager.getLogger(MaxSalaryGreaterOrEqualThanMinSalaryJobValidator.class);

	@Override
	public void validate(Job job) {
		logger.debug("Validating if job has max salary greater or equal than min salary");

		if (Objects.isNull(job)) {
			Error.of("Job").when(FIELD.MANDATORY).trows();
		}

		if(job.getMinSalary() > job.getMaxSalary()) {
			throw new HrsBusinessException("The maximum salary must be greater than or equal the minimum salary");
		}
	}
}