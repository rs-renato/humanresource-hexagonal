package br.com.hrs.core.usecase.impl.job;

import br.com.hrs.core.model.Job;
import br.com.hrs.core.repository.Repository;
import br.com.hrs.core.usecase.DeleteUseCase;
import br.com.hrs.core.validator.DeleteValidator;

import javax.inject.Inject;
import javax.inject.Named;

@Named
public class JobDeleteUseCase extends DeleteUseCase<Job, String> {

	@Inject
	public JobDeleteUseCase(Repository<Job, String> repository, DeleteValidator<Job>... validators) {
		super(repository, validators);
	}
}