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
public class MaxSalaryAllowedJobValidator implements SaveValidator<Job>, UpdateValidator<Job> {

	private static Logger logger = LogManager.getLogger(MaxSalaryAllowedJobValidator.class);

	public static final Integer MAX_COMPANY_SALARY = 30000;
	
	@Override
	public void validate(Job job) {
		logger.debug("Validating if job has the maximum salary above the company's limit");

		if (Objects.isNull(job)) {
			Error.of("Job").when(FIELD.MANDATORY).trows();
		}

		if(job.getMaxSalary() > MAX_COMPANY_SALARY) {
			throw new HrsBusinessException(String.format("The maximum salary allowed in company is %s", MAX_COMPANY_SALARY));
		}
	}
}