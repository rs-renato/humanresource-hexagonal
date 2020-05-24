package br.com.hrs.core.validator.job;

import br.com.hrs.core.exception.HrsBusinessException;
import br.com.hrs.core.exception.error.Error;
import br.com.hrs.core.exception.error.FIELD;
import br.com.hrs.core.model.Job;
import br.com.hrs.core.repository.Repository;
import br.com.hrs.core.validator.SaveValidator;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.inject.Inject;
import javax.inject.Named;
import java.util.Objects;

@Named
public class UniqueIdJobValidator implements SaveValidator<Job> {

	private static Logger logger = LogManager.getLogger(UniqueIdJobValidator.class);

	private Repository<Job, String> jobRepository;

	@Inject
	public UniqueIdJobValidator(Repository<Job, String> jobRepository) {
		this.jobRepository = jobRepository;
	}
	
	@Override
	public void validate(Job job) {
		logger.debug("Validating if job has an unique Id");

		if (Objects.isNull(job)) {
			Error.of("Job").when(FIELD.MANDATORY).trows();
		}

		if (this.jobRepository.existsById(job.getId())) {
			throw new HrsBusinessException("Job id should be unique");
		}
	}
}