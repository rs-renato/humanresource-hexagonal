package br.com.hrs.api.v1.docs;

import br.com.hrs.api.support.message.MensagemRetorno;
import br.com.hrs.api.v1.resource.JobResource;
import io.swagger.annotations.*;
import org.springframework.http.ResponseEntity;

import javax.json.JsonMergePatch;

@Api(value ="/v1/jobs", tags={"Job - Operations"})
@ApiImplicitParams(
		{@ApiImplicitParam(name="Authorization", dataType = "String", paramType = "header")})
public interface JobOperationsDocumentable {

	/**
	 * Creates an Job
	 * @param jobResource representation of job
	 * @return respose entity of {@link JobResource} created
	 */
	@ApiOperation(value = "Creates an Job", nickname="create", notes = "Creates an Job returning itself saved", response = JobResource.class)
	@ApiResponses(value = {
				@ApiResponse(code = 201, message = "Job successfully created.", response = JobResource.class),
				@ApiResponse(code = 400, message = "General request restrictions and/or Bussiness rules validations.", response = MensagemRetorno.class)})
	ResponseEntity<JobResource> create(@ApiParam(value = "Job Representation", required = true) JobResource jobResource);

	/**
	 * Updates an Job
	 * @param id job identifier
	 * @param jobResource representation of job to be updated
	 * @return respose entity of {@link JobResource} updated
	 */
	@ApiOperation(value = "Updates an Job", nickname="update", notes = "Updates an Job returning itself updated", response = JobResource.class)
	@ApiResponses(value = {
				@ApiResponse(code = 200, message = "Job successfully updated.", response = JobResource.class),
				@ApiResponse(code = 400, message = "General request restrictions and/or Bussiness rules validations.", response = MensagemRetorno.class),
				@ApiResponse(code = 404, message = "Job not found.", response = MensagemRetorno.class)})
	ResponseEntity<JobResource> update(@ApiParam(value = "Job Identifier", required = true) String id, @ApiParam(value = "Job Representation", required = true) JobResource jobResource);

	/**
	 * Patches (update partially) an Job
	 * @param id job identifier
	 * @param jsonMergePatch resource of job to be updated
	 * @return respose entity of {@link JobResource} updated
	 */
	@ApiOperation(value = "Patches an Job", nickname="patch", notes = "Patches (updates partially) an Job returning itself updated", response = JobResource.class)
	@ApiResponses(value = {
			@ApiResponse(code = 200, message = "Job successfully updated.", response = JobResource.class),
			@ApiResponse(code = 400, message = "General request restrictions and/or Bussiness rules validations.", response = MensagemRetorno.class),
			@ApiResponse(code = 404, message = "Job not found.", response = MensagemRetorno.class)})
	ResponseEntity<JobResource> patch(@ApiParam(value="Job Identifier", required=true) String id, @ApiParam(value="Job Representation", required=true) JsonMergePatch jsonMergePatch);

	/**
	 * Deletes an Job by its Id
	 * @param id job identifier
	 * @return an {@link MensagemRetorno} cotaining the result of operation
	 */
	@ApiOperation(value = "Deletes an Job by Id", nickname="deleteById", notes = "Deletes an Job by Id returning an operation's confirmation")
	@ApiResponses(value = {
				@ApiResponse(code = 200, message = "Job successfully deleted."),
				@ApiResponse(code = 400, message = "General request restrictions and/or Bussiness rules validations.", response = MensagemRetorno.class),
				@ApiResponse(code = 404, message = "Job not found.", response = MensagemRetorno.class)})
	ResponseEntity<MensagemRetorno> delete(@ApiParam(value = "Job Identifier", required = true) String id);
}