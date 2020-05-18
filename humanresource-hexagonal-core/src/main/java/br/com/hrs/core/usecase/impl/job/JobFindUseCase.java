package br.com.hrs.core.usecase.impl.job;

import br.com.hrs.core.model.Job;
import br.com.hrs.core.repository.Repository;
import br.com.hrs.core.usecase.FindUseCase;

import javax.inject.Inject;
import javax.inject.Named;

@Named
public class JobFindUseCase extends FindUseCase<Job, String> {
    
    @Inject
    public JobFindUseCase(Repository<Job, String> repository) {
        super(repository);
    }
}
