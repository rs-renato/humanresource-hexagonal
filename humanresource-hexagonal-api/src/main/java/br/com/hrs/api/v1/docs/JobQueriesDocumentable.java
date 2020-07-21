package br.com.hrs.api.v1.docs;

import br.com.hrs.api.support.message.MensagemRetorno;
import br.com.hrs.api.v1.resource.JobResource;
import io.swagger.annotations.*;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@Api(value ="/v1/jobs", tags={"Job - Queries"})
@ApiImplicitParams(
		{@ApiImplicitParam(name="Authorization", dataType = "String", paramType = "header")})
public interface JobQueriesDocumentable {

	/**
	 * List all Jobs
	 * @return a collection of jobs
	 */
	@ApiOperation(value = "List All Jobs", nickname="listAll", notes = "List for all Jobs returning a Job's list if there is any, otherwise 'MensagemRetorno'", response = JobResource.class)
	@ApiResponses(value = {
			@ApiResponse(code = 200, message = "Jobs found.", response = JobResource[].class),
			@ApiResponse(code = 404, message = "Jobs not found.", response = MensagemRetorno.class)})
	ResponseEntity<List<JobResource>> listAll();
	
	/**
	 * Find an Job by Id
	 * @param id job identifier
	 * @return a job 
	 */
	@ApiOperation(value = "Finds a Job by its Id ", nickname="findById", notes = "Finds a Job by its Id returning it if there is any, otherwise 'MensagemRetorno'", response = JobResource.class)
	@ApiResponses(value = {
			@ApiResponse(code = 200, message = "Job found.", response = JobResource.class),
			@ApiResponse(code = 404, message = "Job not found.", response = MensagemRetorno.class)})
	ResponseEntity<JobResource> find(@ApiParam(value = "Job Identifier", required = true) @PathVariable String id);
}