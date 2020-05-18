package br.com.hrs.core.usecase.impl.job;

import br.com.hrs.core.model.Job;
import br.com.hrs.core.repository.Repository;
import br.com.hrs.core.usecase.UpdateUseCase;
import br.com.hrs.core.validator.UpdateValidator;

import javax.inject.Inject;
import javax.inject.Named;

@Named
public class JobUpdateUseCase extends UpdateUseCase<Job, String> {

    @Inject
    public JobUpdateUseCase(Repository<Job, String> repository, UpdateValidator<Job>...validators) {
        super(repository, validators);
    }
}