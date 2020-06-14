package br.com.hrs.core.validator.job;

import br.com.hrs.core.exception.HrsNotFoundException;
import br.com.hrs.core.exception.error.Error;
import br.com.hrs.core.exception.error.FIELD;
import br.com.hrs.core.model.Job;
import br.com.hrs.core.repository.JobRepository;
import br.com.hrs.core.validator.DeleteValidator;
import br.com.hrs.core.validator.UpdateValidator;

import javax.inject.Inject;
import javax.inject.Named;
import java.util.Objects;

@Named
public class ExistentJobValidator implements UpdateValidator<Job>, DeleteValidator<Job> {

    private JobRepository jobRepository;

    @Inject
    public ExistentJobValidator(JobRepository jobRepository) {
        this.jobRepository = jobRepository;
    }

    @Override
    public void validate(Job job) {
        logger.debug("Validating if job exists");

        if (Objects.isNull(job)) {
            Error.of("Job").when(FIELD.MANDATORY).trows();
        }

        if (!this.jobRepository.existsById(job.getId())) {
            throw new HrsNotFoundException("Job id doenst exists");
        }
    }
}