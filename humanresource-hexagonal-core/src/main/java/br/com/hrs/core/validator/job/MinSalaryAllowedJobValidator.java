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
public class MinSalaryAllowedJobValidator implements SaveValidator<Job>, UpdateValidator<Job> {

	private static Logger logger = LogManager.getLogger(MinSalaryAllowedJobValidator.class);

	public static final Integer MIN_COMPANY_SALARY = 2000;
	
	@Override
	public void validate(Job job) {
		logger.debug("Validating if job has the minimum salary under the company's limit");

		if (Objects.isNull(job)) {
			Error.of("Job").when(FIELD.MANDATORY).trows();
		}

		if(job.getMinSalary() < MIN_COMPANY_SALARY) {
			throw new HrsBusinessException(String.format("The minimum salary allowed in company is %s", MIN_COMPANY_SALARY));
		}
	}
}