package br.com.hrs.core.usecase.impl.job;

import br.com.hrs.core.model.Job;
import br.com.hrs.core.repository.Repository;
import br.com.hrs.core.usecase.SaveUseCase;
import br.com.hrs.core.validator.SaveValidator;

import javax.inject.Inject;
import javax.inject.Named;

@Named
public class JobSaveUseCase extends SaveUseCase<Job, String> {
	
	@Inject
	public JobSaveUseCase(Repository<Job, String> repository, SaveValidator<Job>... validators) {
		super(repository, validators);
	}
}
