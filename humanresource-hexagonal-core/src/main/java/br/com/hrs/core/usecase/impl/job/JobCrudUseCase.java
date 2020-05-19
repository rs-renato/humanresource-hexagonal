package br.com.hrs.core.usecase.impl.job;

import br.com.hrs.core.model.Job;
import br.com.hrs.core.repository.Repository;
import br.com.hrs.core.usecase.CrudUseCase;
import br.com.hrs.core.validator.Validator;

import javax.inject.Inject;
import javax.inject.Named;

@Named
public class JobCrudUseCase extends CrudUseCase<Job, String> {

	@Inject
	public JobCrudUseCase(Repository<Job, String> repository, Validator<Job>... validators) {
		super(repository, validators);
	}
}