package br.com.hrs.api.v1.controller;

import br.com.hrs.api.support.AssertionSupport;
import br.com.hrs.api.v1.docs.JobQueriesDocumentable;
import br.com.hrs.api.v1.mapper.JobMapper;
import br.com.hrs.api.v1.resource.CountryResource;
import br.com.hrs.api.v1.resource.JobResource;
import br.com.hrs.core.model.Country;
import br.com.hrs.core.model.Job;
import br.com.hrs.core.repository.pagination.Pagination;
import br.com.hrs.core.usecase.job.JobUseCase;
import br.com.hrs.persistence.repository.filter.SpecificationFilter;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/v1/jobs")
public class JobQueriesController implements JobQueriesDocumentable{
	
	private static Logger logger = LogManager.getLogger(JobQueriesController.class);

	private JobUseCase jobUseCase;
	private JobMapper jobMapper;

	@Autowired
	public JobQueriesController(JobUseCase jobUseCase, JobMapper jobMapper) {
		this.jobUseCase = jobUseCase;
		this.jobMapper = jobMapper;
	}

	@GetMapping(produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
	public ResponseEntity<List<JobResource>> listAll(Pageable pageable) {
		logger.info("Performing 'List All Jobs'..");
		List<Job> jobs = this.jobUseCase.findAll(new Pagination(pageable.getPageNumber(), pageable.getPageSize()));
		AssertionSupport.assertResourceFound(jobs, "Jobs not found!");
		List<JobResource> jobResources = jobMapper.toResourceList(jobs);
		logger.info("Found {} jobs on 'List All Jobs'..", jobResources.size());
		return ResponseEntity.ok(jobResources);
	}

	@GetMapping(value = "/{id}", produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
	public ResponseEntity<JobResource> find(@PathVariable String id) {
		logger.info("Performing 'Find Job' Id:{}", id);
		Optional<Job> job = this.jobUseCase.findById(id);
		AssertionSupport.assertResourceFound(job, "Job not found!");
		JobResource jobResource = jobMapper.toResource(jobMapper.unwrap(job));
		logger.info("Job {} found on 'Find Job'", jobResource);
		return ResponseEntity.ok(jobResource);
	}

	@GetMapping(params = {"search"}, produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
	public  ResponseEntity<List<JobResource>> search(@RequestParam String search) {
		logger.info("Performing 'Search Jobs'  Search:{}", search);

		List<Job> jobs = this.jobUseCase.findAll(new SpecificationFilter<Job>(search));

		AssertionSupport.assertResourceFound(jobs, "Jobs not found!");
		List<JobResource> jobResources = jobMapper.toResourceList(jobs);
		logger.info("Found {} jobs on 'Search Jobs'..", jobResources.size());
		return ResponseEntity.ok(jobResources);
	}
}