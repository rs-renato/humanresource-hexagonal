package br.com.hrs.api.v1.controller;

import br.com.hrs.api.support.AssertionSupport;
import br.com.hrs.api.support.MensagemRetornoResponseEntitySupport;
import br.com.hrs.api.support.PatchSupport;
import br.com.hrs.api.v1.docs.JobOperationsDocumentable;
import br.com.hrs.api.v1.mapper.JobMapper;
import br.com.hrs.api.v1.resource.JobResource;
import br.com.hrs.api.validation.FieldValidationStrategy;
import br.com.hrs.core.model.Job;
import br.com.hrs.core.usecase.job.JobUseCase;
import br.gov.go.sefaz.javaee.commons.resource.v1.MensagemRetorno;
import br.gov.go.sefaz.javaee.commons.resource.v1.MensagemRetornoCategoria;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.json.JsonMergePatch;
import java.util.Optional;

@RestController
@RequestMapping("/v1/jobs")
public class JobOperationsController implements JobOperationsDocumentable {

	private static Logger logger = LogManager.getLogger(JobOperationsController.class);

	private JobUseCase jobUseCase;
	private JobMapper jobMapper;
	private PatchSupport patchSupport;

	@Autowired
	public JobOperationsController(JobUseCase jobUseCase, JobMapper jobMapper, PatchSupport patchSupport) {
		this.jobUseCase = jobUseCase;
		this.jobMapper = jobMapper;
		this.patchSupport = patchSupport;
	}

	@PostMapping(consumes = {MediaType.APPLICATION_JSON_VALUE},
				 produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
	public ResponseEntity<JobResource> create(@Validated(FieldValidationStrategy.Create.class) @RequestBody JobResource jobResource) {
		logger.info("Performing 'Create Job' Body:{}", jobResource);
		Job jobCreated = this.jobUseCase.save(jobMapper.toModel(jobResource));
		logger.info("Job {} created on 'Create Job'", jobCreated);
		return ResponseEntity.status(HttpStatus.CREATED)
				.body(jobMapper.toResource(jobCreated));
	}
	
	@PutMapping(value="/{id}", consumes = {MediaType.APPLICATION_JSON_VALUE},
			produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
	public ResponseEntity<JobResource> update(@PathVariable String id, @Validated(FieldValidationStrategy.Update.class)  @RequestBody JobResource jobResource) {
		logger.info("Performing 'Update Job' Id:{}, Body:{}", id, jobResource);
		jobResource.setId(id);
		this.jobUseCase.update(jobMapper.toModel(jobResource));
		logger.info("Job {} updated on 'Update Job'", jobResource);
		return ResponseEntity.ok(jobResource);
	}

	@PatchMapping(value="/{id}", consumes = "application/merge-patch+json",
			produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
	public ResponseEntity<JobResource> patch(@PathVariable String id, @RequestBody JsonMergePatch jsonMergePatch) {
		logger.info("Performing 'Update (Patch) Job' Id:{}, MergePatch:{}", id, jsonMergePatch);
		Optional<Job> job = this.jobUseCase.findById(id);
		AssertionSupport.assertResourceFound(job, "Job not found!");

		JobResource jobPatched = patchSupport.apply(jsonMergePatch, jobMapper.toResource(jobMapper.unwrap(job)));

		this.jobUseCase.update(jobMapper.toModel(jobPatched));
		logger.info("Job {} updated on 'Update (Patch) Job'", jobPatched);
		return ResponseEntity.ok(jobPatched);
	}

	@DeleteMapping(value="/{id}", consumes = {MediaType.APPLICATION_JSON_VALUE},
			produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
	public ResponseEntity<MensagemRetorno> delete(@PathVariable String id) {
		logger.info("Performing 'Delete Job' Id:{}", id);
		this.jobUseCase.deleteById(id);
		logger.info("Job {} deleted on 'Delete Job'", id);
		return MensagemRetornoResponseEntitySupport.createResponseEntity(
				MensagemRetornoCategoria.CONFIRMACAO, HttpStatus.OK, "Job successfully deleted");
	}
}
