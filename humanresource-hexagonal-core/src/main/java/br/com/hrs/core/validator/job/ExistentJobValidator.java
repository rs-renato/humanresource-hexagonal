package br.com.hrs.core.validator.job;

import br.com.hrs.core.exception.HrsNotFoundException;
import br.com.hrs.core.exception.error.Error;
import br.com.hrs.core.exception.error.FIELD;
import br.com.hrs.core.model.Job;
import br.com.hrs.core.repository.Repository;
import br.com.hrs.core.validator.Validator;

import javax.inject.Inject;
import javax.inject.Named;
import java.util.Objects;

@Named
public class ExistentJobValidator implements Validator<Job> {

    private Repository<Job, String> jobRepository;

    @Inject
    public ExistentJobValidator(Repository<Job, String> jobRepository) {
        this.jobRepository = jobRepository;
    }

    @Override
    public void validate(Job job) {
        logger.debug("Validating if job exists");

        if (Objects.isNull(job)) {
            Error.of("Job").when(FIELD.MANDATORY).trows();
        }

        if (!this.jobRepository.exists(job.getId())) {
            throw new HrsNotFoundException("Job id doenst exists");
        }
    }
}