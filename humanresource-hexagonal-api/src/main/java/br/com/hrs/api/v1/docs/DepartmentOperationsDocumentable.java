package br.com.hrs.api.v1.docs;

import br.com.hrs.api.v1.resource.DepartmentResource;
import br.gov.go.sefaz.javaee.commons.resource.v1.MensagemRetorno;
import io.swagger.annotations.*;
import org.springframework.http.ResponseEntity;

import javax.json.JsonMergePatch;

@Api(value ="/v1/departments", tags={"Department - Operations"})
@ApiImplicitParams(
		{@ApiImplicitParam(name="Authorization", dataType = "String", paramType = "header")})
public interface DepartmentOperationsDocumentable {

	/**
	 * Creates an Department
	 * @param departmentResource representation of department
	 * @return respose entity of {@link DepartmentResource} created
	 */
	@ApiOperation(value = "Creates an Department", nickname="create", notes = "Creates an Department returning itself saved", response = DepartmentResource.class)
	@ApiResponses(value = {
				@ApiResponse(code = 201, message = "Department successfully created.", response = DepartmentResource.class),
				@ApiResponse(code = 400, message = "General request restrictions and/or Bussiness rules validations.", response = MensagemRetorno.class)})
	ResponseEntity<DepartmentResource> create(@ApiParam(value = "Department Representation", required = true) DepartmentResource departmentResource);

	/**
	 * Updates an Department
	 * @param id department identifier
	 * @param departmentResource representation of department to be updated
	 * @return respose entity of {@link DepartmentResource} updated
	 */
	@ApiOperation(value = "Updates an Department", nickname="update", notes = "Updates an Department returning itself updated", response = DepartmentResource.class)
	@ApiResponses(value = {
				@ApiResponse(code = 200, message = "Department successfully updated.", response = DepartmentResource.class),
				@ApiResponse(code = 400, message = "General request restrictions and/or Bussiness rules validations.", response = MensagemRetorno.class),
				@ApiResponse(code = 404, message = "Department not found.", response = MensagemRetorno.class)})
	ResponseEntity<DepartmentResource> update(@ApiParam(value = "Department Identifier", required = true) Integer id, @ApiParam(value = "Department Representation", required = true) DepartmentResource departmentResource);

	/**
	 * Patches (update partially) an Department
	 * @param id department identifier
	 * @param jsonMergePatch resource of department to be updated
	 * @return respose entity of {@link DepartmentResource} updated
	 */
	@ApiOperation(value = "Patches an Department", nickname="patch", notes = "Patches (updates partially) an Department returning itself updated", response = DepartmentResource.class)
	@ApiResponses(value = {
			@ApiResponse(code = 200, message = "Department successfully updated.", response = DepartmentResource.class),
			@ApiResponse(code = 400, message = "General request restrictions and/or Bussiness rules validations.", response = MensagemRetorno.class),
			@ApiResponse(code = 404, message = "Department not found.", response = MensagemRetorno.class)})
	ResponseEntity<DepartmentResource> patch(@ApiParam(value="Department Identifier", required=true) Integer id, @ApiParam(value="Department Representation", required=true) JsonMergePatch jsonMergePatch);
	
	/**
	 * Deletes an Department by its Id
	 * @param id department identifier
	 * @return an {@link MensagemRetorno} cotaining the result of operation
	 */
	@ApiOperation(value = "Deletes an Department by Id", nickname="deleteById", notes = "Deletes an Department by Id returning an operation's confirmation")
	@ApiResponses(value = {
				@ApiResponse(code = 200, message = "Department successfully deleted."),
				@ApiResponse(code = 400, message = "General request restrictions and/or Bussiness rules validations.", response = MensagemRetorno.class),
				@ApiResponse(code = 404, message = "Department not found.", response = MensagemRetorno.class)})
	ResponseEntity<MensagemRetorno> delete(@ApiParam(value = "Department Identifier", required = true) Integer id);
}