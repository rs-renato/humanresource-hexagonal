package br.com.hrs.core.usecase.job;

import br.com.hrs.core.model.Job;
import br.com.hrs.core.repository.JobRepository;
import br.com.hrs.core.repository.Repository;
import br.com.hrs.core.usecase.CrudAbstractUseCaseImpl;
import br.com.hrs.core.validator.Validator;

import javax.inject.Inject;
import javax.inject.Named;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

@Named
class JobCrudUseCase extends CrudAbstractUseCaseImpl<Job, String> implements JobUseCase {

	private JobRepository jobRepository;
	private List<Validator<Job>> validators;

	@Inject
	public JobCrudUseCase(JobRepository jobRepository, Validator<Job>... validators) {
		this.jobRepository = jobRepository;
		this.validators = validators != null ? new LinkedList<>(Arrays.asList(validators)) : new ArrayList<>();
	}

	@Override
	protected Repository<Job,String> getRepository() {
		return this.jobRepository;
	}

	@Override
	protected List<Validator<Job>> getValidators() {
		return validators;
	}
}